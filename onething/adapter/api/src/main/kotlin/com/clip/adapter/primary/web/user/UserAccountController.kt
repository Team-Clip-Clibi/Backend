package com.clip.adapter.primary.web.user

import com.clip.adapter.primary.web.swagger.user.UserAccountDocs
import com.clip.adapter.primary.web.user.dto.SignUpDTO
import com.clip.adapter.primary.web.user.dto.Token
import com.clip.application.user.port.`in`.UserRegisterUseCase
import jakarta.validation.Valid
import mu.KotlinLogging
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserAccountController (
    private val userRegisterUseCase: UserRegisterUseCase,
): UserAccountDocs {

    val logger = KotlinLogging.logger {}

    @PostMapping("/signup")
    override fun registerUser(@Valid @RequestBody request: SignUpDTO): Token {

        logger.info(request.toString())
        return Token("asdf","asdf")
    }


}