package org.example.service;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .sorted((u1, u2) -> Integer.compare(u2.getScore(), u1.getScore()))
                .collect(Collectors.toList());
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public User createUser(User user) {
        user.setScore(0);
        user.setBadges(new HashSet<>());
        return userRepository.save(user);
    }

    public Optional<User> updateUserScore(String userId, int score) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setScore(score);
            setBadges(user);
            userRepository.save(user);
        }
        return optionalUser;
    }

    public void deleteUserById(String userId) {
        userRepository.deleteById(userId);
    }

    private void setBadges(User user) {
        user.getBadges().clear();
        if (user.getScore() >= 1 && user.getScore() < 30) {
            user.getBadges().add("Code Ninja");
        }
        if (user.getScore() >= 30 && user.getScore() < 60) {
            user.getBadges().add("Code Champ");
        }
        if (user.getScore() >= 60 && user.getScore() <= 100) {
            user.getBadges().add("Code Master");
        }
    }
}
