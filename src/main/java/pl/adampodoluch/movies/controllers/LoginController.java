package pl.adampodoluch.movies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.adampodoluch.movies.models.forms.LoginForm;
import pl.adampodoluch.movies.models.forms.RegisterForm;
import pl.adampodoluch.movies.models.services.UserService;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "user_login";
    }

    @PostMapping
    public String register(@ModelAttribute LoginForm loginForm, Model model){
        if(!userService.login(loginForm)) {

            model.addAttribute("info", "Login or password is incorrect");
            return "user_login";

        }

        return "redirect:/";

    }
    @GetMapping("/account")
    public String account(Model model){
        model.addAttribute("account", new RegisterForm());
        return "register_user";
    }

    @PostMapping("/account")
    public String makeAccount(@ModelAttribute("account") RegisterForm registerForm, Model model) {
        if(!userService.registerUser(registerForm)){
            model.addAttribute("register", "Yours login is busy");
            return "register_user";
        }
        return "redirect:/login";
    }
}
