package com.example.amigo.controller;

import com.example.amigo.entity.User;
import com.example.amigo.error.ErrorMessage;
import com.example.amigo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Validated
@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers(@QueryParam("gender") String gender) {
        return service.getAllUsers(Optional.ofNullable(gender));
    }

    @GetMapping(path = "{userUid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserByUId(@PathVariable("userUid") UUID userUid) {
        return service.getUser(userUid).<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorMessage("user " + userUid + "was not found.")));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
// integer -because return response code
    public ResponseEntity<Integer> insertNewUser(@Valid @RequestBody User user) {
        int result = service.insertUser(user);
        return getIntegerResponseEntity(result);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> updateUser(@RequestBody User user) {
        int result = service.updateUser(user);

        return getIntegerResponseEntity(result);
    }

    @DeleteMapping(path = "{userUid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> deleteUser(@PathVariable("userUid") UUID userUid) {
        int result = service.removeUser(userUid);
        return getIntegerResponseEntity(result);
    }

    private ResponseEntity<Integer> getIntegerResponseEntity(int result) {
        if (result == 1) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}