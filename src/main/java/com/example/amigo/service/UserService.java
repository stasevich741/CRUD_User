package com.example.amigo.service;

import com.example.amigo.dao.FakeDataDao;
import com.example.amigo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private FakeDataDao repository;

    public UserService(FakeDataDao fakeDataDao) {
        this.repository = fakeDataDao;
    }

    public List<User> getAllUsers(Optional<String> gender) {
        List<User> allUsers = repository.getAllUsers();
        if (gender.isEmpty()) {
            return allUsers;
        }
        try {
            User.Gender theGender = User.Gender.valueOf(gender.get().toUpperCase());
            return allUsers.stream()
                    .filter(user -> user.getGender().equals(theGender))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalStateException("Invalid gender", e);
        }
    }

    public Optional<User> getUser(UUID userId) {
        return repository.getUserById(userId);
    }

    public int updateUser(User user) {
        Optional<User> optionalUser = getUser(user.getUserId());
        if (optionalUser.isPresent()) {
            return repository.updateUser(user);
        }
        return -1;
    }

    public int removeUser(UUID userId) {
        Optional<User> optionalUser = getUser(userId);
        if (optionalUser.isPresent()) {
            return repository.removeUser(userId);
        }
        return -1;
    }

    public int insertUser(User user) {
        UUID userUid = UUID.randomUUID();
        user.setUserId(userUid);
        return repository.insertUser(userUid, user);
    }
}
