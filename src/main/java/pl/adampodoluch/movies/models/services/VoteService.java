package pl.adampodoluch.movies.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.adampodoluch.movies.models.entities.VoteEntity;
import pl.adampodoluch.movies.models.repositories.VoteRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    VoteRepository voteRepository;

    @Transactional
    public void incrementDownVote(int moveId){
        voteRepository.incrementDownVote(moveId);
    }

    @Transactional
    public void incrementUpVote(int moveId){
        voteRepository.incrementUpVote(moveId);
    }

}
