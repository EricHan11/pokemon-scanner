package io.github.erichan11.pokemonscanner.service;

import io.github.erichan11.pokemonscanner.dao.UserRepository;
import io.github.erichan11.pokemonscanner.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int id) {
        Optional<User> result = userRepository.findById(id); //findById for JPA repository always returns Optional wrapper

        User foundUser = null;
        if (result.isPresent()) {
            foundUser = result.get();
        } else {
            throw new RuntimeException("Could not find user with id - " + id);
        }
        return foundUser;
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return userRepository.existsById(id);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
