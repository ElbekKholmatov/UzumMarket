package uz.market.uzum.controllers.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uz.market.uzum.UzumMarketApplication;
import uz.market.uzum.domains.user.UserRole;
import uz.market.uzum.dtos.roles.UserRoleDTO;
import uz.market.uzum.services.roles.UserRoleService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = UzumMarketApplication.class)
@AutoConfigureMockMvc
@WithMockUser
class RoleControllerIntegrationTest {


    public static final String CODE_USER_ROLE = "USER";
    public static final String NAME_USER_ROLE = "User role";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private UserRoleService userRoleService;

    @Test
    @WithMockUser(roles = {"SUPER_ADMIN", "ADMIN"})
    void create() throws Exception {
        UserRoleDTO createDTO = UserRoleDTO.builder().name(NAME_USER_ROLE).code(CODE_USER_ROLE).build();
        when(userRoleService.save(any())).thenReturn(new UserRole(1, NAME_USER_ROLE, CODE_USER_ROLE));

        mockMvc.perform(post("/api/v1/role/create").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(createDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.code").value(createDTO.getCode())).andExpect(jsonPath("$.name").value(createDTO.getName()));
        verify(userRoleService, times(1)).save(any());
    }

    @WithMockUser(roles = {"SUPER_ADMIN", "ADMIN"})
    @Test
    void get() throws Exception {

        UserRole userRole = new UserRole(1, CODE_USER_ROLE, NAME_USER_ROLE, new ArrayList<>());
        when(userRoleService.get(1)).thenReturn(userRole);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/role/1").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1));
        verify(userRoleService, times(1)).get(1);

    }

    @WithMockUser(roles = {"SUPER_ADMIN", "ADMIN"})
    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/role/1").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNoContent()).andReturn();
        verify(userRoleService, times(1)).delete(any());
    }

    @WithMockUser(roles = {"SUPER_ADMIN", "ADMIN"})
    @Test
    void roles() throws Exception {
        List<UserRole> all = List.of(UserRole.builder().id(1).code(CODE_USER_ROLE).name(NAME_USER_ROLE).build(), UserRole.builder().id(2).code(CODE_USER_ROLE).name(NAME_USER_ROLE).build());

        when(userRoleService.getRoles()).thenReturn(all);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/role/roles").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(1));
        verify(userRoleService, times(1)).getRoles();
    }
}