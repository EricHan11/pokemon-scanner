package io.github.erichan11.pokemonscanner.dao;

import io.github.erichan11.pokemonscanner.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUsername(String username);
}
