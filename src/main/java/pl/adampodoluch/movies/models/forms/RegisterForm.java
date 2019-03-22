package pl.adampodoluch.movies.models.forms;

import lombok.Data;

@Data
public class RegisterForm {

    private String login;
    private String password;
    private String email;
}
