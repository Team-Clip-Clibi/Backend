package com.clip.application.user.service

import com.clip.adapter.primary.web.user.dto.SignUpRequest
import com.clip.application.user.port.`in`.UserRegisterUseCase
import com.clip.application.user.port.out.UserManagementPort
import com.clip.domain.user.User
import org.springframework.stereotype.Service

@Service
class UserRegisterService(
    private val userManagementPort: UserManagementPort,
): UserRegisterUseCase {
    override fun registerUser(request: SignUpRequest) {
        val user = User.register(
            servicePermission = request.servicePermission,
            privatePermission = request.privatePermission,
            marketingPermission = request.marketingPermission,
            platform = request.platform,
            socialId = request.socialId,
            deviceType = request.deviceType,
            osVersion = request.osVersion,
            isAllowNotify = request.isAllowNotify,
            firebaseToken = request.firebaseToken
        )
        userManagementPort.saveUser(user)
    }


}