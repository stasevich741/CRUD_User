package com.example.amigo.dao;

import com.example.amigo.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class FakeDataDaoTest {

    private FakeDataDao repository;

    @BeforeEach
    void setUp() {
        repository = new FakeDataDao();
    }

    //    "joe", "jones", User.Gender.MALE, 21, "jones@mail.ru"
    @Test
    void should_Select_All_Users() {
        List<User> userList = repository.getAllUsers();
        assertThat(userList).hasSize(1);

        User user = userList.get(0);

        assertThat(user.getFirstName()).isEqualTo("joe");
        assertThat(user.getLastName()).isEqualTo("jones");
        assertThat(user.getGender()).isEqualTo(User.Gender.MALE);
        assertThat(user.getAge()).isEqualTo(21);
        assertThat(user.getEmail()).isEqualTo("jones@mail.ru");
        assertThat(user.getUserId()).isNotNull();
    }

    @Test
    void should_Select_User_By_Id() {
        UUID annaUserId = UUID.randomUUID();
        User anna = new User(annaUserId, "anna", "montata", User.Gender.FEMALE, 55, "anna@mail.ru");
        repository.insertUser(annaUserId, anna);
        assertThat(repository.getAllUsers()).hasSize(2);

        Optional<User> annaOptional = repository.getUserById(annaUserId);
        assertThat(annaOptional.isPresent()).isTrue();
        assertThat(annaOptional.get()).isEqualToComparingFieldByField(anna);
        assertThat(annaOptional.get()).isEqualTo(anna);

    }

    @Test
    void should_Not_Select_User_By_Random_Id() {
        Optional<User> userById = repository.getUserById(UUID.randomUUID());
        assertThat(userById.isPresent()).isFalse();
    }

    @Test
    void should_Update_User() {
        UUID joeUserUId = repository.getAllUsers().get(0).getUserId();
        User newJoe = new User(joeUserUId, "anna", "montata", User.Gender.FEMALE, 55, "anna@mail.ru");


        repository.updateUser(newJoe);

        Optional<User> userById = repository.getUserById(joeUserUId);
        assertThat(userById.isPresent()).isTrue();

        assertThat(repository.getAllUsers()).hasSize(1);
        assertThat(userById.get()).isEqualTo(newJoe);
    }

    @Test
    void should_Remove_User_By_UId() {
        UUID joeUserUId = repository.getAllUsers().get(0).getUserId();

        repository.removeUser(joeUserUId);

        assertThat(repository.getUserById(joeUserUId).isPresent()).isFalse();
        assertThat(repository.getAllUsers()).isEmpty();
    }

    @Test
    void should_Insert_User() {
        UUID userUId = UUID.randomUUID();
        User newUser = new User(userUId, "anna", "montata", User.Gender.FEMALE, 55, "anna@mail.ru");

        repository.insertUser(userUId, newUser);

        List<User> users = repository.getAllUsers();
        assertThat(users).hasSize(2);
        assertThat(repository.getUserById(userUId).get()).isEqualTo(newUser);
    }
}