package pl.adampodoluch.movies.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.adampodoluch.movies.models.repositories.UserRepository;
import pl.adampodoluch.movies.models.entities.UserEntity;
import pl.adampodoluch.movies.models.forms.LoginForm;
import pl.adampodoluch.movies.models.forms.RegisterForm;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSession userSession;

    public boolean login(LoginForm loginForm){
        Optional<UserEntity> user = userRepository.findUser(loginForm.getLogin(), loginForm.getPassword());{
            if(user.isPresent()){
                userSession.setLogin(true);
                userSession.setUserId(user.get().getId());
                return true;
            }
            return false;
        }
//Optionale chronią nas przed nulami, mają metodę isPresent, jeśli chcemy wyciągnąć to get
    }

    public boolean registerUser(RegisterForm registerForm){

        if(userRepository.isLoginExist(registerForm.getLogin())){
            return false;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(registerForm.getLogin());
        userEntity.setPassword(registerForm.getPassword());
        userEntity.setEmail(registerForm.getEmail());

        userRepository.save(userEntity);
        return true;
    }

    public Optional<UserEntity> getLoginUser(){
        return userRepository.findById(userSession.getUserId());
    }



}
