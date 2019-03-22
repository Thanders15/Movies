package pl.adampodoluch.movies.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.adampodoluch.movies.models.entities.AuthorEntity;
import pl.adampodoluch.movies.models.repositories.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public Iterable<AuthorEntity> getAuthors(){
        return authorRepository.findAll();
    }

    public AuthorEntity findBySurname(String surname){
        return authorRepository.findAuthorBySurname(surname);
    }
}
