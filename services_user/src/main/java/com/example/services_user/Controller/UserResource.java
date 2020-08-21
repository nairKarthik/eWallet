package com.example.services_user.Controller;

import com.example.services_user.AppException.UserNotFoundException;
import com.example.services_user.Model.User;
import com.example.services_user.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
public class UserResource {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<User> findUserById(@PathVariable int id) {
        LOGGER.info("/users/{id} called with id "+ id);
        try {
            return userRepository.findById(id);
        } catch (UserNotFoundException e) {
            System.out.println("User not found");
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/test")
    public String sayHello() {
        return "Working";
    }
}
