package io.github.erichan11.pokemonscanner.service;

import io.github.erichan11.pokemonscanner.dao.UserCardsRepository;
import io.github.erichan11.pokemonscanner.dao.UserRepository;
import io.github.erichan11.pokemonscanner.entity.User;
import io.github.erichan11.pokemonscanner.entity.UserCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserCardsServiceImpl {
    private UserCardsRepository userCardsRepository;

    @Autowired
    public UserCardsServiceImpl(UserCardsRepository userCardsRepository) {
        this.userCardsRepository = userCardsRepository;
    }

    public List<UserCard> findAllUserCards(int id) {
        return userCardsRepository.findByUserId(id);
    }

    public UserCard findCardById(int id) {
        Optional<UserCard> result = userCardsRepository.findById(id); //findById for JPA repository always returns Optional wrapper

        UserCard foundCard = null;
        if (result.isPresent()) {
            foundCard = result.get();
        } else {
            throw new RuntimeException("Could not find user card with id - " + id);
        }
        return foundCard;
    }

    @Transactional
    public UserCard save(UserCard card) {
        return userCardsRepository.save(card);
    }

    @Transactional
    public void delete(int id) {
        userCardsRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return userCardsRepository.existsById(id);
    }
}
