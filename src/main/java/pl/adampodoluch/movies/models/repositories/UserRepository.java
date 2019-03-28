package pl.adampodoluch.movies.models.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.adampodoluch.movies.models.entities.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM `user` WHERE `login` = ?1")
    UserEntity findUserByLogin(String login);

    @Query(nativeQuery = true, value = "SELECT CASE WHEN COUNT(`id`) > 0 THEN 'true' ELSE 'false' END FROM `user` WHERE `login` = ?1")
    boolean isLoginExist(String login);

    @Query(nativeQuery = true, value = "UPDATE (`user`) SET is_deleted = 1 WHERE `id` = ?1")
    @Modifying
    void setIsDeleted (int userId);



}
