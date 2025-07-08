package com.clip.application.user.port.out

import com.clip.domain.user.User

interface UserManagementPort {
    fun saveUser(
        userId: String,
        deviceType: String,
        platform: String,
        relationshipStatus: String,
        userStatus: String
    ): User
}