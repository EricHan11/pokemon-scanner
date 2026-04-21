package io.github.erichan11.pokemonscanner.restcontroller;

import io.github.erichan11.pokemonscanner.dao.UserCardsRepository;
import io.github.erichan11.pokemonscanner.entity.Condition;
import io.github.erichan11.pokemonscanner.entity.User;
import io.github.erichan11.pokemonscanner.entity.UserCard;
import io.github.erichan11.pokemonscanner.service.UserCardsServiceImpl;
import io.github.erichan11.pokemonscanner.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//GET    /api/users/{userId}/cards
//POST   /api/users/{userId}/cards
//DELETE /api/users/{userId}/cards/{cardId}
@RestController
@RequestMapping("/users/{userId}/cards")
public class UserCardsRestController {
    private UserCardsServiceImpl userCardsService;
    private UserServiceImpl userService;

    public UserCardsRestController(UserCardsServiceImpl userCardsService, UserServiceImpl userService) {
        this.userCardsService = userCardsService;
        this.userService = userService;
    }

    // This runs **before every request** and resolves the userId
    @ModelAttribute("user")
    public User validateUser(@PathVariable int userId) {
        return userService.findById(userId); // throw exception if not found
    }

    @GetMapping
    public List<UserCard> getAllUserCards(@ModelAttribute("user") User user) {
        return userCardsService.findAllUserCards(user.getId());
    }

    @PostMapping
    public UserCard addCard(@ModelAttribute("user") User user, @RequestBody UserCard card) {
        card.setUser(user);
        if (card.getCardCondition() == null) card.setCardCondition(Condition.NM);
        if (card.getIsGraded() == null) card.setIsGraded(false);
        return userCardsService.save(card);
    }

    //This should only be able to delete cards of the current user
    @DeleteMapping("/{cardId}")
    public ResponseEntity<String> deleteCard(@ModelAttribute("user") User user, @PathVariable int cardId) {
        UserCard card = userCardsService.findCardById(cardId); //error checks if there's an existing card with cardId too
        if (!Objects.equals(card.getUser().getId(), user.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Card id " + cardId + " does not belong to User with id " + user.getId() + ". Cannot delete.");
        }
        userCardsService.delete(cardId);
        return ResponseEntity.ok("Deleted card with id: " + cardId);
    }

}
