package com.clip.application.user.port.`in`

import com.clip.adapter.primary.web.user.dto.SignUpRequest

interface UserRegisterUseCase {
    fun registerUser(request: SignUpRequest)
}