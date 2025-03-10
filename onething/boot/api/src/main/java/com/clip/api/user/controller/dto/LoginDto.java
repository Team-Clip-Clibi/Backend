package com.clip.api.user.controller.dto;

import com.clip.user.entity.Platform;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginDto {
    private String socialId;
    private Platform platform;

    @Builder
    public LoginDto(String socialId, Platform platform) {
        this.socialId = socialId;
        this.platform = platform;
    }
}
