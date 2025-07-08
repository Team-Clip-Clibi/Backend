package com.clip.adapter.secondary.persistence.user.repository

import com.clip.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
}