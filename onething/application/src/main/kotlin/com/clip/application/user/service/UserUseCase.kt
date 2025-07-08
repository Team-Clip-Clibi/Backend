package com.clip.application.user.service

import com.clip.application.user.port.`in`.UserRegisterUseCase
import com.clip.application.user.port.out.UserManagementPort
import com.clip.domain.user.User
import org.springframework.stereotype.Service

@Service
class UserUseCase(
    private val userManagementPort: UserManagementPort,
): UserRegisterUseCase {
    override fun registerUser(user: User): User {
        TODO("Not yet implemented")
    }


}