package pl.adampodoluch.movies.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.adampodoluch.movies.models.entities.UserEntity;
import pl.adampodoluch.movies.models.forms.RegisterForm;
import pl.adampodoluch.movies.models.services.UserService;

@RequestMapping("/api")
@RestController
public class UserControllerApi {

    final UserService userService;

    @Autowired
    public UserControllerApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user", produces = "application/json")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok(userService.getAll());
    }
    @GetMapping(value = "/user/{id}", produces = "application/json")
    public ResponseEntity getOneUser(@PathVariable("id") int id){

        UserEntity user = userService.findUserById(id);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @DeleteMapping(value = "/user/{id}", produces = "application/json")
    public ResponseEntity setIsDeleteByUserId(@PathVariable ("id") int id){
        userService.softDeleteUserById(id);

        return ResponseEntity.ok("deleted " + id);
    }
    @PostMapping(value = "/user", consumes = "application/json")
    public ResponseEntity saveUser(@RequestBody RegisterForm registerForm){
        if(!userService.registerUser(registerForm)){
            ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("username is taken");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
//ResponseEntity to obiekt potrafiący przyjąć odpowiedź HTTP