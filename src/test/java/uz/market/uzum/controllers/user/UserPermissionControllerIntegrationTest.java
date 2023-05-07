package uz.market.uzum.controllers.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uz.market.uzum.UzumMarketApplication;
import uz.market.uzum.domains.user.UserPermission;
import uz.market.uzum.dtos.roles.UserPermissionCreateDTO;
import uz.market.uzum.services.roles.UserPermissionService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = UzumMarketApplication.class)
@AutoConfigureMockMvc
@WithMockUser
public class UserPermissionControllerIntegrationTest {

    private final static String NAME_TEST_PERMISSION = "Test permission";
    private final static String CODE_TEST_PERMISSION = "TEST_PERMISSION";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @MockBean
    private UserPermissionService permissionService;


    @Disabled
    @Test
    @WithMockUser(roles = {"SUPER_ADMIN", "ADMIN"})
    public void createPermission_withValidInput_shouldReturnCreated() throws Exception {
        when(permissionService.save(any())).thenReturn(UserPermission.builder().id(1).code(CODE_TEST_PERMISSION).name(NAME_TEST_PERMISSION).build());
        UserPermissionCreateDTO createDTO = UserPermissionCreateDTO.builder().name(NAME_TEST_PERMISSION).code(CODE_TEST_PERMISSION).build();


        MvcResult mvcResult = mockMvc.perform(post("/api/v1/permission/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.code").value(createDTO.getCode()))
                .andExpect(jsonPath("$.name").value(createDTO.getName()))
                .andReturn();

        String contentString = mvcResult.getResponse().getContentAsString();

        assertThat(contentString).containsIgnoringCase(CODE_TEST_PERMISSION);
        verify(permissionService, atLeastOnce()).save(createDTO);

    }


    @Test
    @WithMockUser(roles = {"SUPER_ADMIN", "ADMIN"})
    public void getPermission() throws Exception {
        List<UserPermission> all = List.of(UserPermission.builder().id(1).code(CODE_TEST_PERMISSION).name(NAME_TEST_PERMISSION).build());

        when(permissionService.getAll()).thenReturn(all);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/permission/all")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
        verify(permissionService, atLeastOnce()).getAll();
    }

    @Test
    @WithMockUser(roles = {"SUPER_ADMIN", "ADMIN"})
    public void getUserSuccess() throws Exception {
        UserPermission userPermission = UserPermission.builder().id(1).code(CODE_TEST_PERMISSION).name(NAME_TEST_PERMISSION).build();
        when(permissionService.getPermissionById(any())).thenReturn(userPermission);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/permission/get/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.code").value(CODE_TEST_PERMISSION))
                .andExpect(jsonPath("$.name").value(NAME_TEST_PERMISSION));
        verify(permissionService, atLeastOnce()).getPermissionById(any());
    }

    @Test
    @WithMockUser(roles = {"SUPER_ADMIN", "ADMIN"})
    public void deleteUserSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/permission/deleteUser/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent()).andReturn();
        verify(permissionService, atLeastOnce()).delete(any());
    }

}
