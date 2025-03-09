package com.clip.api.user.service;

import com.clip.api.user.controller.dto.LoginDto;
import com.clip.api.user.controller.dto.SignupDto;
import com.clip.api.user.exception.NotFoundUserException;
import com.clip.auth.entity.Token;
import com.clip.auth.service.TokenService;
import com.clip.global.config.jwt.TokenProvider;
import com.clip.user.entity.TermsAcceptance;
import com.clip.user.entity.User;
import com.clip.user.service.TermsAcceptanceService;
import com.clip.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final TokenProvider tokenProvider;
    private final UserService userService;
    private final TermsAcceptanceService termsAcceptanceService;
    private final TokenService tokenService;

    @Transactional
    public TokenProvider.Token signup(SignupDto request) {
        Optional<User> optUser = userService.findOptUser(request.getSocialId(), request.getPlatform());

        if (optUser.isPresent()) {
            TokenProvider.Token token = tokenProvider.generateToken(optUser.get().getId(), LocalDateTime.now());
            tokenService.updateRefreshToken(optUser.get(), token.refreshToken());
            return token;
        }

        User user = userService.save(User.builder()
                .socialId(request.getSocialId())
                .platform(request.getPlatform())
                .build());
        termsAcceptanceService.save(TermsAcceptance.builder()
                .servicePermission(request.isServicePermission())
                .privatePermission(request.isPrivatePermission())
                .marketingPermission(request.isMarketingPermission())
                .user(user)
                .build()
        );

        TokenProvider.Token token = tokenProvider.generateToken(user.getId(), LocalDateTime.now());
        tokenService.save(Token.builder().refreshToken(token.refreshToken()).user(user).build());
        return token;
    }

    public TokenProvider.Token login(LoginDto request) {
        Optional<User> optUser = userService.findOptUser(request.getSocialId(), request.getPlatform());

        if (optUser.isEmpty()) {
            throw new NotFoundUserException("가입되지 않은 유저입니다.");
        }

        TokenProvider.Token token = tokenProvider.generateToken(optUser.get().getId(), LocalDateTime.now());
        tokenService.updateRefreshToken(optUser.get(), token.refreshToken());
        return token;
    }

    public void updatePhoneNumber(long userId, String phoneNumber) {
        userService.updatePhoneNumber(userId, phoneNumber);
    }

    public void updateName(long userId, String userName) {
        userService.updateUserName(userId, userName);
    }

    public void updateNickname(long userId, String nickname) {
        userService.updateNickname(userId, nickname);
    }
}
