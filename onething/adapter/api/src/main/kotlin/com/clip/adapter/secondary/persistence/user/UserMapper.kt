package com.clip.adapter.secondary.persistence.user

import com.clip.adapter.secondary.persistence.user.entity.TermsAcceptanceEntity
import com.clip.adapter.secondary.persistence.user.entity.UserEntity
import com.clip.domain.user.User

object UserMapper {
    fun toUserEntity(
        user: User
    ): UserEntity {
        return UserEntity(
            username = user.username,
            phoneNumber = user.phoneNumber,
            nickname = user.nickname,
            birth = user.birth,
            city = user.city,
            county = user.county,
            gender = user.gender,
            platform = user.platform,
            socialId = user.socialId,
            deviceType = user.deviceType,
            osVersion = user.osVersion,
            isPhoneNumberVerified = user.isPhoneNumberVerified,
            job = user.job,
            firebaseToken = user.firebaseToken,
            language = user.language,
            dietaryOption = user.dietaryOption,
            relationshipStatus = user.relationshipStatus,
            isSameRelationshipConsidered = user.isSameRelationshipConsidered,
            isAllowNotify = user.isAllowNotify,
            userStatus = user.status
        )
    }

    fun toTermsAcceptanceEntity(
        user: User,
        userEntity: UserEntity
    ): TermsAcceptanceEntity {
        return TermsAcceptanceEntity(
            servicePermission = user.servicePermission,
            privatePermission = user.privatePermission,
            marketingPermission = user.marketingPermission,
            user = userEntity
        )
    }

    fun toDomainEntity(userEntity: UserEntity, termsAcceptanceEntity: TermsAcceptanceEntity): User {
        return User(
            id = userEntity.id,
            status = userEntity.userStatus,
            username = userEntity.username,
            phoneNumber = userEntity.phoneNumber,
            nickname = userEntity.nickname,
            birth = userEntity.birth,
            city = userEntity.city,
            county = userEntity.county,
            gender = userEntity.gender,
            platform = userEntity.platform,
            socialId = userEntity.socialId,
            deviceType = userEntity.deviceType,
            osVersion = userEntity.osVersion,
            isPhoneNumberVerified = userEntity.isPhoneNumberVerified,
            job = userEntity.job,
            firebaseToken = userEntity.firebaseToken,
            language = userEntity.language,
            dietaryOption = userEntity.dietaryOption,
            relationshipStatus = userEntity.relationshipStatus,
            isSameRelationshipConsidered = userEntity.isSameRelationshipConsidered,
            isAllowNotify = userEntity.isAllowNotify,
            servicePermission = termsAcceptanceEntity.servicePermission,
            privatePermission = termsAcceptanceEntity.privatePermission,
            marketingPermission = termsAcceptanceEntity.marketingPermission
        )
    }
}