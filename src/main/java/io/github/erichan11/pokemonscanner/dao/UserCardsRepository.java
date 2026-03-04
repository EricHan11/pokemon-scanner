package io.github.erichan11.pokemonscanner.dao;

import io.github.erichan11.pokemonscanner.entity.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCardsRepository extends JpaRepository<UserCard, Integer> {
    //Spring automatically implements this method because Spring Data JPA parses the method name
    //findByUserId. It breaks it into findBy (query keyword) and UserId (property path. All properties come from
    //the Entity class assigned to extends JpaRepository<>).
    //All query methods must reference the field names in UserCard for this instance.
    //Then because we have a @ManyToOne(fetch = FetchType.LAZY)
    //                       @JoinColumn(name = "user_id", nullable = false)
    //                       private User user;
    //in UserCard class, it will treat UserId as user.id because UserCard has a user field, and
    //the User class as an id field, so Spring interprets it as user.id. The SQL query that runs will be
    //SELECT *
    //FROM user_cards
    //WHERE user_id = ? //the passed in userId when this method is called
    //
    List<UserCard> findByUserId(Integer userId);
}
