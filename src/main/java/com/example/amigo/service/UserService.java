package com.example.amigo.service;

import com.example.amigo.dao.FakeDataDao;
import com.example.amigo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private FakeDataDao repository;

    public UserService(FakeDataDao fakeDataDao) {
        this.repository = fakeDataDao;
    }

    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }

    public Optional<User> getUser(UUID userId) {
        return repository.getUserById(userId);
    }

    public int updateUser(User user) {
        Optional<User> optionalUser = getUser(user.getUserId());
        if (optionalUser.isPresent()) {
            repository.updateUser(user);
            return 1;
        }
        return -1;
    }

    public int removeUser(UUID userId) {
        Optional<User> optionalUser = getUser(userId);
        if (optionalUser.isPresent()) {
            repository.removeUser(userId);
            return 1;
        }
        return -1;
    }

    public int insertUser(User user) {
        return repository.insertUser(UUID.randomUUID(), user);
    }

}
