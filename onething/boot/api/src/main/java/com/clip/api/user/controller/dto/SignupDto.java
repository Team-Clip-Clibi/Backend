package com.clip.api.user.controller.dto;

import com.clip.user.entity.Platform;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupDto {
    private boolean servicePermission;
    private boolean privatePermission;
    private boolean marketingPermission;
    private String socialId;
    private Platform platform;

    @Builder
    public SignupDto(boolean servicePermission, boolean privatePermission, boolean marketingPermission, String socialId, Platform platform) {
        this.servicePermission = servicePermission;
        this.privatePermission = privatePermission;
        this.marketingPermission = marketingPermission;
        this.socialId = socialId;
        this.platform = platform;
    }
}
