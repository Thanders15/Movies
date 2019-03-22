package pl.adampodoluch.movies.models.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "votes")
public class VoteEntity {

    private  @Id @GeneratedValue int id;
    private  @Column(name = "movie_id") int movieId;
    private  @Column(name = "vote_up") int voteUp;
    private  @Column(name = "vote_down") int voteDown;

    @OneToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;


}
