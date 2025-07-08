package com.clip.adapter.primary.web.swagger.user

import com.clip.adapter.primary.web.user.dto.SignUpDTO
import com.clip.adapter.primary.web.user.dto.Token
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Tag(name = "회원관리", description = "회원가입, 로그인")
interface UserAccountDocs {

    @Operation(
        summary = "회원가입 API", description = """
                    소셜로그인 후 필수 약관에 모두 동의하면 소셜id(불변값)와 플랫폼(KAKAO or APPLE) 정보, 약관 동의 내역을 저장하고,
                    Access Token과 Refresh Token을 발급하여 반환합니다.
                    """
    )
    @ApiResponse(
        responseCode = "200",
        description = "회원 가입 성공",
        content = [Content(
            mediaType = "application/json",
            schema = Schema(implementation = Token::class)
        )]
    )
    fun registerUser(request:  SignUpDTO): Token
}