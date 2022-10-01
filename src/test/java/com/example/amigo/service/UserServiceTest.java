package com.example.amigo.service;

import com.example.amigo.dao.FakeDataDao;
import com.example.amigo.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


class UserServiceTest {

    @Mock
    private FakeDataDao fakeDataDao;
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(fakeDataDao);
    }

    @Test
    void should_Get_All_Users() {
        UUID annaUserId = UUID.randomUUID();
        User anna = new User(annaUserId, "anna", "montana", User.Gender.FEMALE, 55, "anna@mail.ru");
        List<User> users = List.of(anna);

        given(fakeDataDao.getAllUsers()).willReturn(users);

        List<User> allUsers = userService.getAllUsers();
        assertThat(allUsers).hasSize(1);

        User user = allUsers.get(0);

        assertUserFields(user);
    }

    @Test
    void should_Get_User() {
        UUID annaUserId = UUID.randomUUID();
        User anna = new User(annaUserId, "anna", "montana", User.Gender.FEMALE, 55, "anna@mail.ru");

        given(fakeDataDao.getUserById(annaUserId)).willReturn(Optional.of(anna));

        Optional<User> userOptional = userService.getUser(annaUserId);

        assertThat(userOptional.isPresent()).isTrue();
        User user = userOptional.get();

        assertUserFields(user);

    }


    @Test
    void should_Update_User() {
        UUID annaUserId = UUID.randomUUID();
        User anna = new User(annaUserId, "anna", "montana", User.Gender.FEMALE, 55, "anna@mail.ru");

        given(fakeDataDao.getUserById(annaUserId)).willReturn(Optional.of(anna));
        given(fakeDataDao.updateUser(anna)).willReturn(1);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        int updateResultUser = userService.updateUser(anna);

        verify(fakeDataDao).getUserById(annaUserId);
        verify(fakeDataDao).updateUser(captor.capture());

        User value = captor.getValue();
        assertUserFields(value);

        assertThat(updateResultUser).isEqualTo(1);
    }

    @Test
    void should_Remove_User() {
    }

    @Test
    void should_Insert_User() {
    }

    private void assertUserFields(User user) {
        assertThat(user.getFirstName()).isEqualTo("anna");
        assertThat(user.getLastName()).isEqualTo("montana");
        assertThat(user.getGender()).isEqualTo(User.Gender.FEMALE);
        assertThat(user.getAge()).isEqualTo(55);
        assertThat(user.getEmail()).isEqualTo("anna@mail.ru");
        assertThat(user.getUserId()).isNotNull();
    }

}