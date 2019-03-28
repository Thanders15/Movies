package pl.adampodoluch.movies.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table (name = "user")
@Data
public class UserEntity {

    private @Id @GeneratedValue int id;
    private String login;
    private @JsonIgnore String password;
    private String email;
    private boolean admin;
    private @Column(name = "is_deleted") boolean isDeleted;
}
