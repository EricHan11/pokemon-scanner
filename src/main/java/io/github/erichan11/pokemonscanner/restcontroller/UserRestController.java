package io.github.erichan11.pokemonscanner.restcontroller;


import io.github.erichan11.pokemonscanner.entity.User;
import io.github.erichan11.pokemonscanner.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserRestController {
    private UserServiceImpl userService;

    public UserRestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    //@RequestBody - when you need data from HTTP request body, like when you need JSON data for input
    //It will convert JSON data to User object. Output will again be JSON with .save
    @PostMapping
    public User addUser(@RequestBody User user) {
        //in case they pass an id in JSON, set id to 0. This is to force save of new item
        //instead of updating
        user.setId(0);

        String wantedUsername = user.getUsername();
        //check for duplicate username
        if (userService.existsByUsername(wantedUsername)) {
            throw new RuntimeException("User with username " + wantedUsername + " already exists. Pick a new one.");
        }
        //if entity ID is null or 0, treat as new insert. If ID matches existing ID, update instead
        return userService.save(user);
    }

    //I want the endpoints /user/cards to maybe display all the cards for a user. So
    //do I need only this rest controller or another one for my UserCard class? Is it clean to call
    //UserCardService methods and such in this controller? Or can I create a new rest controller that also
    //uses /user mapping
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable int userId) {
        User user = userService.findById(userId);
        if (user == null) {
            throw new RuntimeException("User with id " + userId + " not found.");
        }

        return user;
    }

    //PUT needs all properties of User class specified in JSON, or else non-specified will be deleted on update
    @PutMapping()
    public User updateUser(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
        boolean exists = userService.existsById(userId); // just check existence

        if (!exists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User id " + userId + " not found. Cannot delete.");
        }

        userService.delete(userId); // delete without fetching the full object
        return ResponseEntity.ok("Deleted user with id: " + userId);
    }


}
