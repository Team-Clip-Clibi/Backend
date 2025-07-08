package com.clip.adapter.secondary.persistence.user.adapter

import com.clip.application.user.port.out.UserManagementPort
import com.clip.domain.user.User
import com.clip.adapter.secondary.persistence.user.repository.UserRepository
import org.springframework.stereotype.Repository

@Repository
class UserJpaAdapter (
    private val userRepository: UserRepository
): UserManagementPort {
    override fun saveUser(
        userId: String,
        deviceType: String,
        platform: String,
        relationshipStatus: String,
        userStatus: String
    ): User {
//        userRepository.save()
        TODO("Not yet implemented")
    }
}