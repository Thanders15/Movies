package pl.adampodoluch.movies.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.adampodoluch.movies.models.services.MovieService;

@Controller
@RequestMapping("/api")
public class MovieControllerApi {

    final MovieService movieService;


    @Autowired
    public MovieControllerApi(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity movie(){
        return ResponseEntity.ok(movieService.getAll());
    }
}
