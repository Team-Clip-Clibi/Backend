package com.clip.adapter.secondary.persistence.user.repository

import com.clip.adapter.secondary.persistence.user.entity.TermsAcceptanceEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TermsAcceptanceRepository : JpaRepository<TermsAcceptanceEntity, Long> {
}