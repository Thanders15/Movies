package pl.adampodoluch.movies.models.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.adampodoluch.movies.models.entities.MovieEntity;

@Repository
public interface MovieRepository extends CrudRepository<MovieEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT CASE WHEN COUNT(`id`) > 0 THEN 'true' ELSE 'false' END FROM `movie` WHERE title = ?1")
    boolean existsByTitle(String title);

}


// ?1, odnośi się do tego nawiasu String title, w przypadku więcej rzeczy w nawiasie bylo by ?1, ?2