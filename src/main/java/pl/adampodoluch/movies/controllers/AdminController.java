package pl.adampodoluch.movies.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.adampodoluch.movies.models.forms.MovieForm;
import pl.adampodoluch.movies.models.services.MovieService;
import pl.adampodoluch.movies.models.services.UserService;

@Controller
public class AdminController {
    @Autowired
    MovieService movieService;

    @Autowired
    UserService userService;

    @GetMapping("/admin/add")
    public String movie(Model model){
        model.addAttribute("movieForm", new MovieForm());
        return "movie_add";
    }

    @PostMapping("/admin/add")
    public String movie(@ModelAttribute MovieForm movieForm, Model model){
        MovieService.MovieResponse movieResponse = movieService.addMovie(movieForm);

        if(userService.getLoginUser().isPresent() && !userService.getLoginUser().get().isAdmin()){
            return "redirect:/";
        }


        if(movieResponse != MovieService.MovieResponse.CREATED ){
            model.addAttribute("movieResponse", movieResponse);
            return "movie_add";
        }

        return "redirect:/";
    }
}
