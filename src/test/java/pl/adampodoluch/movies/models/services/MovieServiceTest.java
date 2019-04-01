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
import pl.adampodoluch.movies.models.entities.AuthorEntity;
import pl.adampodoluch.movies.models.forms.MovieForm;
import pl.adampodoluch.movies.models.repositories.AuthorRepository;
import pl.adampodoluch.movies.models.repositories.MovieRepository;
import pl.adampodoluch.movies.models.repositories.VoteRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class MovieServiceTest {

    @Mock
    MovieRepository movieRepository;


    @Mock
    VoteRepository voteRepository;

    @Mock
    AuthorRepository authorRepository;

    @Mock
    AuthorService authorService;

    @InjectMocks
    MovieService movieService;



    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addMovieTest() {
        MovieForm movieForm = new MovieForm();
        movieForm.setTitle("test");
        movieForm.setAuthor("Nowak");
        movieForm.setShortDescription("test");
        movieForm.setLongDescription("test");
        movieForm.setType("test");
        movieForm.setYear(1233);
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setSurname("Nowak");

        Mockito.when(movieRepository.existsByTitle("test")).thenReturn(true);
        Mockito.when(movieRepository.existsById(3)).thenReturn(true);
        Mockito.when(authorService.findBySurname("Nowak")).thenReturn(authorEntity);
        Mockito.when(movieRepository.existsByTitle("test")).thenReturn(false);


        Assertions.assertEquals(MovieService.MovieResponse.CREATED, movieService.addMovie(movieForm));




    }

    @Test
    void getOneMovieTest() {
        //given
        int notExistingId = -1;

        //when
        Mockito.when(movieRepository.findById(notExistingId)).thenReturn(Optional.ofNullable(null));

        //then
        Assertions.assertThrows(IllegalStateException.class, () -> movieService.getOneMovie(notExistingId));
    }
}