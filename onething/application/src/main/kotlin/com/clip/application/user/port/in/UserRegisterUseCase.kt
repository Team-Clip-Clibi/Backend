package com.clip.application.user.port.`in`

import com.clip.domain.user.User

interface UserRegisterUseCase {
    fun registerUser(user: User): User
}