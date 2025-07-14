package com.clip.adapter.secondary.persistence.user.entity

import com.clip.adapter.secondary.persistence.common.BaseEntity
import com.clip.domain.user.enums.*
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "user")
class UserEntity(
    @Column(length = 30)
    val username: String?,

    @Column(unique = true)
    val phoneNumber: String?,

    @Column(unique = true)
    val nickname: String?,

    val birth: LocalDate?,

    @Enumerated(EnumType.STRING)
    val city: City?,

    @Enumerated(EnumType.STRING)
    val county: County?,

    @Enumerated(EnumType.STRING)
    val gender: Gender?,

    @Enumerated(EnumType.STRING)
    val platform: Platform,

    val socialId: String,

    @Enumerated(EnumType.STRING)
    val deviceType: DeviceType,

    val osVersion: String,

    val isPhoneNumberVerified: Boolean,

    firebaseToken: String?,

    language: String?,

    dietaryOption: String?,

    relationshipStatus: RelationshipStatus?,

    isSameRelationshipConsidered: Boolean?,

    isAllowNotify: Boolean,

    job: Job?,

    userStatus: UserStatus,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
) : BaseEntity() {

    var firebaseToken: String? = firebaseToken
        private set

    var language: String? = language
        private set

    var dietaryOption: String? = dietaryOption
        private set

    @Enumerated(EnumType.STRING)
    var relationshipStatus: RelationshipStatus? = relationshipStatus
        private set

    var isSameRelationshipConsidered: Boolean? = isSameRelationshipConsidered
        private set

    var isAllowNotify: Boolean = isAllowNotify
        private set

    var job: Job? = job
        private set

    @Enumerated(EnumType.STRING)
    var userStatus: UserStatus = userStatus
        private set
}
