package pl.adampodoluch.movies.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.adampodoluch.movies.models.entities.VoteEntity;
import pl.adampodoluch.movies.models.repositories.VoteRepository;

import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    VoteRepository voteRepository;


    public VoteEntity getVotesForMovie(int movieId){
        Optional<VoteEntity> voteEntityOptional = voteRepository.findVoteByMovieId(movieId);
        return voteEntityOptional.isPresent() ? voteEntityOptional.get() : new VoteEntity();
    }
}
