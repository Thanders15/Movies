package pl.adampodoluch.movies.controllers.api;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.adampodoluch.movies.models.forms.RegisterForm;
import pl.adampodoluch.movies.models.services.UserService;
import pl.adampodoluch.movies.models.services.UserSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class UserControllerApiTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    UserService userService;

    @InjectMocks
    UserControllerApi userControllerApi;

    @Test
     void shouldAddUser() throws Exception {

        //then

        RegisterForm registerForm = new RegisterForm();
        registerForm.setPassword("abc");
        registerForm.setEmail("assaddad");
        registerForm.setLogin("aaabc");

        //when
        Mockito.when(userService.registerUser(registerForm)).thenReturn(true);

        //then

        mockMvc.perform(post("/api/user")
                .content(getGson().toJson(registerForm))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldDeleteUser() throws Exception {
        //when
        int idToDelete = 5;

        //when
        doAnswer(s -> null).when(userService).findUserById(idToDelete);

        //given

        mockMvc.perform(delete("/api/user/" + idToDelete)
                .header("api-key", "adas8askfaidADYGDYAGD98753nwDNSAAUFHWQ9FDsaf"))
                .andExpect(status().isOk());
    }
    public Gson getGson () {
        return new Gson();
    }
}