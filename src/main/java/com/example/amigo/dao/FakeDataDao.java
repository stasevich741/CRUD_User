package com.example.amigo.dao;

import com.example.amigo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FakeDataDao implements UserDao {

    private Map<UUID, User> database;

    public FakeDataDao() {
        database = new HashMap<>();
        UUID joeUserId = UUID.randomUUID();
        database.put(joeUserId,
                new User(joeUserId, "joe", "jones", User.Gender.MALE, 21, "jones@mail.ru"));
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Optional<User> getUserById(UUID userId) {
        return Optional.ofNullable(database.get(userId));
    }

    @Override
    public int updateUser(User user) {
        database.put(user.getUserId(), user);
        return 1;
    }

    @Override
    public int removeUser(UUID userId) {
        database.remove(userId);
        return 1;
    }

    @Override
    public int insertUser(UUID userUid, User user) {
        user.setUserId(userUid);
        database.put(userUid, user);
        return 1;
    }
}
