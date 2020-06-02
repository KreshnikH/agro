package com.devek.agro.controller;

import com.devek.agro.exception.ResourceNotFoundException;
import com.devek.agro.model.User;
import com.devek.agro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping()
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // get users
    @GetMapping()
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    // get user by id
    @GetMapping("users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
        throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + userId));
            return ResponseEntity.ok().body(user);
    }

    // save user
    @PostMapping("users")
    public User createUser(@RequestBody User user) {
        System.out.println(user);
        return this.userRepository.save(user);
    }

    // update user
    @PutMapping("users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
                                           @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + userId));
        user.setEmail(userDetails.getEmail());
        user.setLastname(userDetails.getLastname());
        user.setFirstname(userDetails.getFirstname());
        final User updatedEmployee = userRepository.save(user);
        return ResponseEntity.ok(updatedEmployee);
    }

    // delete user
    @DeleteMapping("users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException{
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + userId));
        this.userRepository.delete(user);

        Map<String, Boolean> response  = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }



}
