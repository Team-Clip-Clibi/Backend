package com.clip.domain.user

import com.clip.domain.user.enums.City
import com.clip.domain.user.enums.County
import com.clip.domain.user.enums.DeviceType
import com.clip.domain.user.enums.Gender
import com.clip.domain.user.enums.Job
import com.clip.domain.user.enums.Platform
import com.clip.domain.user.enums.RelationshipStatus
import com.clip.domain.user.enums.UserStatus
import java.time.LocalDate

class User(
    val id: Long? = null,
    var status: UserStatus,
    var detail: UserDetail,
) {
    init {
        require(detail.isAllowNotify && !detail.firebaseToken.isNullOrBlank()) {
            "알림을 허용하려면 파이어베이스 토큰이 필요합니다."
        }
    }

    constructor(
        servicePermission: Boolean,
        privatePermission: Boolean,
        marketingPermission: Boolean,
        platform: Platform,
        socialId: String,
        deviceType: DeviceType,
        osVersion: String,
        isAllowNotify: Boolean,
        firebaseToken: String?,
    ) : this(
        status = UserStatus.REGISTERED,
        detail = UserDetail(
            servicePermission = servicePermission,
            privatePermission = privatePermission,
            marketingPermission = marketingPermission,
            platform = platform,
            socialId = socialId,
            deviceType = deviceType,
            osVersion = osVersion,
            isAllowNotify = isAllowNotify,
            firebaseToken = firebaseToken
        ),
    )

    companion object{
        fun register(
            servicePermission: Boolean,
            privatePermission: Boolean,
            marketingPermission: Boolean,
            platform: Platform,
            socialId: String,
            deviceType: DeviceType,
            osVersion: String,
            isAllowNotify: Boolean,
            firebaseToken: String?,
        ): User {
            return User(
                servicePermission = servicePermission,
                privatePermission = privatePermission,
                marketingPermission = marketingPermission,
                platform = platform,
                socialId = socialId,
                deviceType = deviceType,
                osVersion = osVersion,
                isAllowNotify = isAllowNotify,
                firebaseToken = firebaseToken
            )
        }
    }



    fun updateInfo(
        username: String,
        phoneNumber: String,
        nickname: String,
        birth: LocalDate,
        city: City,
        county: County,
        gender: Gender,
        platform: Platform,
        socialId: String,
        deviceType: DeviceType,
        firebaseToken: String,
        isPhoneNumVerified: Boolean,
        dietaryOption: String,
        relationshipStatus: RelationshipStatus,
        isSameRelationshipConsidered: Boolean,
        job: Job,
        isAllowNotify: Boolean,
        ){
        detail.username = username
        detail.phoneNumber = phoneNumber
        detail.nickname = nickname
        detail.birth = birth
        detail.city = city
        detail.county = county
        detail.gender = gender
        detail.platform = platform
        detail.socialId = socialId
        detail.deviceType = deviceType
        detail.firebaseToken = firebaseToken
        detail.isPhoneNumVerified = isPhoneNumVerified
        detail.dietaryOption = dietaryOption
        detail.relationshipStatus = relationshipStatus
        detail.isSameRelationshipConsidered = isSameRelationshipConsidered
        detail.job = job
        detail.isAllowNotify = isAllowNotify
    }

    fun activate() {
        this.status = UserStatus.ACTIVE
    }
}