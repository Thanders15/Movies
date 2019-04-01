package pl.adampodoluch.movies.models.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.adampodoluch.movies.models.entities.UserEntity;
import pl.adampodoluch.movies.models.forms.LoginForm;
import pl.adampodoluch.movies.models.forms.RegisterForm;
import pl.adampodoluch.movies.models.repositories.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserSession userSession;

    @InjectMocks
    UserService userService;


    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.jupiter.api.Test
    void shouldLoginUser() {

        //given
        String testPasswordHash = "$2a$10$vOTTj1O7fjCKlAhV8CRjY./IPBdyUjMm3FlTay.3TqDPwqUY6u9PO";

        LoginForm loginForm = new LoginForm();
        loginForm.setLogin("test");
        loginForm.setPassword("test");

        UserEntity userEntity = new UserEntity();
        userEntity.setLogin("test");
        userEntity.setPassword(testPasswordHash);


        //when
        Mockito.when(userRepository.findUserByLogin("test")).thenReturn(userEntity);


        Assertions.assertTrue(userService.login(loginForm));
    }

    @Test
    void shouldNotRegisterAlreadyTakenUsername(){
        //given
        RegisterForm registerForm = new RegisterForm();
        registerForm.setLogin("test");
        registerForm.setEmail("test");
        registerForm.setPassword("test");

        //when
        Mockito.when(userRepository.isLoginExist("test")).thenReturn(true);

        //then
        Assertions.assertFalse(userService.registerUser(registerForm));
    }
}