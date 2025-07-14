package com.clip.adapter.secondary.persistence.user.adapter

import com.clip.adapter.secondary.persistence.user.UserMapper
import com.clip.adapter.secondary.persistence.user.repository.TermsAcceptanceRepository
import com.clip.application.user.port.out.UserManagementPort
import com.clip.domain.user.User
import com.clip.adapter.secondary.persistence.user.repository.UserRepository
import org.springframework.stereotype.Repository
import jakarta.transaction.Transactional

@Repository
class UserJpaAdapter (
    private val userRepository: UserRepository,
    private val termsAcceptanceRepository: TermsAcceptanceRepository
): UserManagementPort {

    @Transactional
    override fun saveUser(user: User) {
        val userEntity = UserMapper.toUserEntity(user)
        val savedUserEntity = userRepository.save(userEntity)

        val termsAcceptance = UserMapper.toTermsAcceptanceEntity(user, savedUserEntity)
        termsAcceptanceRepository.save(termsAcceptance)
    }
}