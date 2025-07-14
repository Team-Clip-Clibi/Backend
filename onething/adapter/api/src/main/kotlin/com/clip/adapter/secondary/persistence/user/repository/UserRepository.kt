package com.clip.adapter.secondary.persistence.user.repository

import com.clip.adapter.secondary.persistence.user.entity.UserEntity
import com.clip.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
}