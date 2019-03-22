package pl.adampodoluch.movies.models.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.adampodoluch.movies.models.entities.UserEntity;
import pl.adampodoluch.movies.models.entities.VoteEntity;

import java.util.Optional;

@Repository
public interface VoteRepository extends CrudRepository<VoteEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT  * FROM  `votes` WHERE  `movie_id` = ?1")
    Optional<VoteEntity> findVoteByMovieId(int movieId);
}
