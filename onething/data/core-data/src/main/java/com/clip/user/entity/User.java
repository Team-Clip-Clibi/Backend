package com.clip.user.entity;

import com.clip.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String username;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String nickname;

    @Column
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column
    private City city;

    @Enumerated(EnumType.STRING)
    @Column
    private County county;

    @Enumerated(EnumType.STRING)
    @Column
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column
    private Platform platform;

    @Column
    private String socialId;

    @Builder
    public User(String username, String phoneNumber, String nickname, LocalDate birth, City city, County county, Gender gender, Platform platform, String socialId) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.birth = birth;
        this.city = city;
        this.county = county;
        this.gender = gender;
        this.platform = platform;
        this.socialId = socialId;
    }
}
