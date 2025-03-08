package com.clip.api.user.controller;

import com.clip.api.docs.user.UserAccountDocs;
import com.clip.api.user.controller.dto.UpdateNameDto;
import com.clip.api.user.controller.dto.UpdateNicknameDto;
import com.clip.api.user.controller.dto.UpdatePhoneNumberDto;
import com.clip.api.user.controller.dto.SignupDto;
import com.clip.api.user.service.UserAccountService;
import com.clip.global.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserAccountController implements UserAccountDocs {
    private final UserAccountService userAccountService;

    @PostMapping("/users")
    public ResponseEntity<TokenProvider.Token> createUserAccount(@RequestBody SignupDto.Request request) {
        return ResponseEntity.ok(userAccountService.signup(request));
    }

    @PatchMapping("/users/phone")
    public ResponseEntity<Void> updatePhoneNumber(@RequestBody UpdatePhoneNumberDto updatePhoneNumberDto,
                                                  @AuthenticationPrincipal UserDetails userDetails) {
        userAccountService.updatePhoneNumber(Long.parseLong(userDetails.getUsername()), updatePhoneNumberDto.getPhoneNumber());
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/users/name")
    public ResponseEntity<Void> updateName(@RequestBody UpdateNameDto updateNameDto,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        userAccountService.updateName(Long.parseLong(userDetails.getUsername()), updateNameDto.getUserName());
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/users/nickname")
    public ResponseEntity<Void> updateNickname(@RequestBody UpdateNicknameDto updateNicknameDto,
                                               @AuthenticationPrincipal UserDetails userDetails) {
        userAccountService.updateNickname(Long.parseLong(userDetails.getUsername()), updateNicknameDto.getNickname());
        return ResponseEntity.ok().build();
    }
}
