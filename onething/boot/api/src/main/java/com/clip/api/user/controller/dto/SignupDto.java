package com.clip.api.user.controller.dto;

import com.clip.user.entity.Platform;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SignupDto {
    @Getter
    @NoArgsConstructor
    public static class Request {
        private boolean servicePermission;
        private boolean privatePermission;
        private boolean marketingPermission;
        private String socialId;
        private Platform platform;
    }
}
