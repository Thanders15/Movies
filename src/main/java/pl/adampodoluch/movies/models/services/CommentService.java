package pl.adampodoluch.movies.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.adampodoluch.movies.models.entities.MovieEntity;
import pl.adampodoluch.movies.models.entities.UserEntity;
import pl.adampodoluch.movies.models.repositories.CommentRepository;
import pl.adampodoluch.movies.models.entities.CommentEntity;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserSession userSession;

    public void addComment(int movieId, String comment){
        MovieEntity movieEntity = new MovieEntity();
        UserEntity userEntity = new UserEntity();
        movieEntity.setId(movieId);
        userEntity.setId(userSession.getUserId());

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setComment(comment);
        commentEntity.setMovie(movieEntity);
        commentEntity.setUser(userEntity);

        commentRepository.save(commentEntity);
    }
}
