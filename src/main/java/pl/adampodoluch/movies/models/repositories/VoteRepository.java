package pl.adampodoluch.movies.models.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.adampodoluch.movies.models.entities.VoteEntity;

import java.util.Optional;

@Repository
public interface VoteRepository extends CrudRepository<VoteEntity, Integer> {

    @Query(nativeQuery = true, value = "UPDATE `vote` SET `vote_up` = `vote_up` + 1 WHERE `movie_id` = ?1")
    @Modifying
    void incrementUpVote(int moveId);

    @Query(nativeQuery = true, value = "UPDATE `vote` SET `vote_down` = `vote_down` + 1 WHERE `movie_id` = ?1")
    @Modifying
    void incrementDownVote(int moveId);

    @Query(nativeQuery = true, value = "SELECT * FROM `vote` WHERE `movie_id` = ?1")
    Optional<VoteEntity> findVoteByMoveId(int movieId);
}
