package com.clip.api.user.controller;

import com.clip.api.docs.user.UserAccountDocs;
import com.clip.api.user.controller.dto.*;
import com.clip.api.user.service.UserAccountService;
import com.clip.global.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserAccountController implements UserAccountDocs {
    private final UserAccountService userAccountService;

    @PostMapping("/signup")
    public TokenProvider.Token createUserAccount(@RequestBody SignupDto request) {
        return userAccountService.signup(request);
    }

    @PostMapping("/signin")
    public TokenProvider.Token loginUserAccount(@RequestBody LoginDto request) {
        return userAccountService.login(request);
    }

    @PatchMapping("/phone")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePhoneNumber(@RequestBody UpdatePhoneNumberDto updatePhoneNumberDto,
                                                  @AuthenticationPrincipal UserDetails userDetails) {
        userAccountService.updatePhoneNumber(Long.parseLong(userDetails.getUsername()), updatePhoneNumberDto.getPhoneNumber());
    }

    @PatchMapping("/name")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateName(@RequestBody UpdateNameDto updateNameDto,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        userAccountService.updateName(Long.parseLong(userDetails.getUsername()), updateNameDto.getUserName());
    }

    @PatchMapping("/nickname")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNickname(@RequestBody UpdateNicknameDto updateNicknameDto,
                                               @AuthenticationPrincipal UserDetails userDetails) {
        userAccountService.updateNickname(Long.parseLong(userDetails.getUsername()), updateNicknameDto.getNickname());
    }
}
