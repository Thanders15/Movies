package pl.adampodoluch.movies.models.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.adampodoluch.movies.models.entities.CommentEntity;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Integer> {
}
