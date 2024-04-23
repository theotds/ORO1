package com.carshop2;

import com.carshop2.entities.User;
import com.carshop2.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        User user = new User();
        user.setEmail("test@example.com");
        userRepository.save(user);
    }

    //Użytkownika według maila
    @Test
    public void whenFindByEmail_thenUserReturned() {
        // When
        Optional<User> foundUser = userRepository.findByEmail("test@example.com");

        // Then
        assertTrue(foundUser.isPresent(), "User should be present");
    }
}
