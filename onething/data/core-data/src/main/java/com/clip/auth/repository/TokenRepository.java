package com.clip.auth.repository;

import com.clip.auth.entity.Token;
import com.clip.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Transactional
    @Modifying
    @Query("update Token t set t.refreshToken = :refreshToken where t.user = :user")
    void updateRefreshToken(@Param("user") User user, @Param("refreshToken") String refreshToken);
}
