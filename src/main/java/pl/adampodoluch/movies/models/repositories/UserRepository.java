package pl.adampodoluch.movies.models.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.adampodoluch.movies.models.entities.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM `user` WHERE `login` = ?1 AND `password` = ?2")
    Optional<UserEntity> findUser(String login, String password);

    @Query(nativeQuery = true, value = "SELECT CASE WHEN COUNT(`id`) > 0 THEN 'true' ELSE 'false' END FROM `user` WHERE `login` = ?1")
    boolean isLoginExist(String login);


}
