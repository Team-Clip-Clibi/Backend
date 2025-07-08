package com.clip.adapter.primary.web.user.dto

import com.clip.domain.user.User
import com.clip.domain.user.enums.DeviceType
import com.clip.domain.user.enums.Platform
import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.NotBlank

data class SignUpDTO(
    @field:AssertTrue(message = "서비스 이용약관에 동의해야 합니다.")
    val servicePermission: Boolean,
    @field:AssertTrue(message = "개인정보 수집 및 이용에 동의해야 합니다.")
    val privatePermission: Boolean,
    val marketingPermission: Boolean,
    val platform: Platform,
    @field:NotBlank
    val socialId: String,
    @field:NotBlank
    val osVersion: String,
    val deviceType: DeviceType,
    val isAllowNotify: Boolean,
    val firebaseToken: String?,
){
    fun toUser(): User {
        return User.register(
            servicePermission = servicePermission,
            privatePermission = privatePermission,
            marketingPermission = marketingPermission,
            platform = platform,
            socialId = socialId,
            deviceType = deviceType,
            osVersion = osVersion,
            isAllowNotify = isAllowNotify,
            firebaseToken = firebaseToken
        )
    }
}
