package pl.adampodoluch.movies.models.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vote")
public class VoteEntity {
    private @Id @GeneratedValue int id;
    private @Column(name = "vote_up") int upVoteCount;
    private @Column(name = "vote_down") int downVoteCount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;
}
