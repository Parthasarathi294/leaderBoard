import org.example.controller.UserController;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUserService() {
        User user = new User();
        user.setUserId("1");
        user.setUsername("JohnDoe");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals(0, createdUser.getScore());
        assertTrue(createdUser.getBadges().isEmpty());
    }

    @Test
    void testUpdateUserScoreService_UserExists() {
        User user = new User();
        user.setUserId("1");
        user.setUsername("JohnDoe");
        user.setScore(0);

        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        Optional<User> updatedUser = userService.updateUserScore("1", 45);

        assertTrue(updatedUser.isPresent());
        assertEquals(45, updatedUser.get().getScore());
        assertTrue(updatedUser.get().getBadges().contains("Code Champ"));
    }

    @Test
    void testUpdateUserScoreService_UserNotFound() {
        when(userRepository.findById(anyString())).thenReturn(Optional.empty());

        Optional<User> updatedUser = userService.updateUserScore("999", 45);

        assertTrue(updatedUser.isEmpty());
    }


}
