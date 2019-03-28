package pl.adampodoluch.movies.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.adampodoluch.movies.models.repositories.UserRepository;
import pl.adampodoluch.movies.models.entities.UserEntity;
import pl.adampodoluch.movies.models.forms.LoginForm;
import pl.adampodoluch.movies.models.forms.RegisterForm;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSession userSession;

    public boolean login(LoginForm loginForm){
        UserEntity user = userRepository.findUserByLogin(loginForm.getLogin());{
            if(user != null && getBCrypt().matches(loginForm.getPassword(), user.getPassword())){
                userSession.setLogin(true);
                userSession.setUserId(user.getId());
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
        userEntity.setPassword(getBCrypt().encode(registerForm.getPassword()));
        userEntity.setEmail(registerForm.getEmail());

        userRepository.save(userEntity);
        return true;
    }

    public Optional<UserEntity> getLoginUser(){
        return userRepository.findById(userSession.getUserId());
    }
    public Iterable<UserEntity> getAll(){
        return userRepository.findAll();
    }
   public UserEntity findUserById(int id){
        return userRepository.findById(id).get();
   }

    @Bean
    public BCryptPasswordEncoder getBCrypt(){
        return new BCryptPasswordEncoder();
    }

    @Transactional
    public void softDeleteUserById(int userId){
        userRepository.setIsDeleted(userId);


    }





}
