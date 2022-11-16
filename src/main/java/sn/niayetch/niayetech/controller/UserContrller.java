package sn.niayetch.niayetech.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.niayetch.niayetech.entity.User;
import sn.niayetch.niayetech.exception.ResourceNotFoundException;
import sn.niayetch.niayetech.service.UserService;

import javax.annotation.security.RolesAllowed;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Map;

/**
 *  - GET /api/users
 *
 * 	- POST /api/users
 *
 * 	- GET /api/users/{id}
 *
 * 	- PUT /api/users/{id}
 *
 * 	- DELETE /api/users/{id}
 */
@RestController
@RequestMapping("/api")
public class UserContrller {
    private final UserService userService;

    public UserContrller(UserService userService) {
        this.userService = userService;
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable(value="id") Long userId) throws ResourceNotFoundException {
        User user = userService.getUser(userId);
        return ResponseEntity.ok().body(user);
    }
    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/users")
    public Map<String ,String> createUser(@RequestBody User user) throws ValidationException {
        return userService.createUser(user);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value="id") Long userId, @RequestBody User userDetails){
        User user = userService.updateUser(userId, userDetails);
        return ResponseEntity.ok().body(user);
    }
    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value="id") Long userId){
        return userService.deleteUser(userId);
    }
}
