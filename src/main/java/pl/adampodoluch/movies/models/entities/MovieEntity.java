package pl.adampodoluch.movies.models.entities;


import lombok.Data;
import org.springframework.data.domain.Sort;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "movie")
public class MovieEntity {
    private @Id @GeneratedValue int id;
    private String title;
    private int year;
    private  @Column(name = "short_description") String shortDescription;
    private  @Column(name = "long_description") String longDescription;
    private String type;


    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;

    @OneToMany(mappedBy = "movie")
    @OrderBy("creation_time DESC ")
    List<CommentEntity>  comments;
}
