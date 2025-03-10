package com.clip.user.service;

import com.clip.user.entity.Platform;
import com.clip.user.entity.User;
import com.clip.user.exception.NicknameAlreadyExistsException;
import com.clip.user.exception.PhoneNumberAlreadyExistsException;
import com.clip.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findOptUser(String socialId, Platform platform) {
        return userRepository.findUser(socialId, platform);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void updatePhoneNumber(long userId, String phoneNumber) {
        try {
            userRepository.updatePhoneNumber(userId, phoneNumber);
        }catch (DataIntegrityViolationException e){
            throw new PhoneNumberAlreadyExistsException();
        }
    }

    public void updateUserName(long userId, String userName) {
        userRepository.updateUserName(userId, userName);
    }

    public void updateNickname(long userId, String nickname) {
        try {
            userRepository.updateNickname(userId, nickname);
        }catch (DataIntegrityViolationException e){
            throw new NicknameAlreadyExistsException();
        }
    }
}
