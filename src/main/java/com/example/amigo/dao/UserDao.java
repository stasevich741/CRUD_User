package com.example.amigo.dao;

import com.example.amigo.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//extends JpaRepository<User, UUID>
public interface UserDao {

    List<User> getAllUsers();

    Optional<User> getUserById(UUID userId);

    int updateUser(User user);

    int removeUser(UUID userId);

    int insertUser(UUID userUid, User user);
}
