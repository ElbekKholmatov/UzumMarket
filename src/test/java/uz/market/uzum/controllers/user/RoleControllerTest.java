package uz.market.uzum.controllers.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import uz.market.uzum.services.roles.UserRoleService;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = RoleController.class)
@AutoConfigureMockMvc
@WithMockUser
class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private UserRoleService userRoleService;

    @Test
    @WithMockUser(roles = {"SUPER_ADMIN", "ADMIN"})
    void create() {
        // TODO: 07/05/2023   continue test
    }

    @Test
    void update() {
    }

    @Test
    void get() {
    }

    @Test
    void delete() {
    }

    @Test
    void roles() {
    }

    @Test
    void addPermission() {
    }

    @Test
    void removePermission() {
    }
}