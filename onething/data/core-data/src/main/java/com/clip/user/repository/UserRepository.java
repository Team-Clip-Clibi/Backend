package com.clip.user.repository;

import com.clip.user.entity.Platform;
import com.clip.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    @Query("""
        select u from User u
        where u.socialId = :socialId and u.platform = :platform
    """)
    Optional<User> findUser(@Param("socialId") String socialId, @Param("platform") Platform platform);

    @Transactional
    @Modifying
    @Query("update User u set u.phoneNumber = :phoneNumber where u.id = :userId")
    void updatePhoneNumber(@Param("userId") long userId, @Param("phoneNumber") String phoneNumber);

    @Transactional
    @Modifying
    @Query("update User u set u.username = :userName where u.id = :userId")
    void updateUserName(@Param("userId") long userId, @Param("userName") String userName);

    @Transactional
    @Modifying
    @Query("update User u set u.nickname = :nickname where u.id = :userId")
    void updateNickname(@Param("userId") long userId, @Param("nickname") String nickname);
}
