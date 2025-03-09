package com.clip.api.user.controller.dto;

import com.clip.user.entity.Platform;
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
}
