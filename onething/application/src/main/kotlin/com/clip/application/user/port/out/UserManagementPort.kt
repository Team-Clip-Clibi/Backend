package com.clip.application.user.port.out

import com.clip.domain.user.User

interface UserManagementPort {
    fun saveUser(
        user: User
    )
}