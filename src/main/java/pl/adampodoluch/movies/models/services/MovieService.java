package pl.adampodoluch.movies.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.adampodoluch.movies.models.entities.UserEntity;
import pl.adampodoluch.movies.models.forms.LoginForm;
import pl.adampodoluch.movies.models.repositories.MovieRepository;
import pl.adampodoluch.movies.models.entities.AuthorEntity;
import pl.adampodoluch.movies.models.entities.MovieEntity;
import pl.adampodoluch.movies.models.forms.MovieForm;
import pl.adampodoluch.movies.models.repositories.UserRepository;

import java.util.Optional;

@Service
public class MovieService {
    public enum MovieResponse {
        CREATED, AUTHOR_NOT_EXIST, TITLE_ALREADY_EXIST, U_ARE_NOT_ADMIN}

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    AuthorService authorService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSession userSession;


    public Iterable<MovieEntity> getAll(){
        return movieRepository.findAll();
    }

    public MovieResponse addMovie(MovieForm movieForm){
        MovieEntity movieEntity = new MovieEntity();
        AuthorEntity authorEntity = authorService.findBySurname(movieForm.getAuthor());
        UserEntity userEntity = new UserEntity();

        if(authorEntity == null){
            return MovieResponse.AUTHOR_NOT_EXIST;
        }

        if(movieRepository.existsByTitle(movieForm.getTitle())){
            return MovieResponse.TITLE_ALREADY_EXIST;
        }
        // jeśli metoda jest void to może zwracać tylko pusty return



        movieEntity.setAuthor(authorEntity);
        movieEntity.setLongDescription(movieForm.getLongDescription());
        movieEntity.setShortDescription(movieForm.getShortDescription());
        movieEntity.setType(movieForm.getType());
        movieEntity.setYear(movieForm.getYear());
        movieEntity.setTitle(movieForm.getTitle());

        movieRepository.save(movieEntity);
        return MovieResponse.CREATED;
    }

    public MovieEntity getOneMovie(int id) {
        Optional<MovieEntity> optionalMovieEntity = movieRepository.findById(id);
        if (!optionalMovieEntity.isPresent()) {
            throw new IllegalStateException("Movie with that id, dont exist");
        }

        return optionalMovieEntity.get();
    }
}

