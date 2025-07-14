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

class User private constructor(
    val id: Long? = null,
    status: UserStatus,
    private var personalInfo: PersonalInfo,
    private var technicalInfo: TechnicalInfo,
    private val acceptanceInfo: AcceptanceInfo
) {
    var status: UserStatus = status
        private set

    val username: String? get() = personalInfo.username
    val phoneNumber: String? get() = personalInfo.phoneNumber
    val nickname: String? get() = personalInfo.nickname
    val birth: LocalDate? get() = personalInfo.birth
    val city: City? get() = personalInfo.city
    val county: County? get() = personalInfo.county
    val gender: Gender? get() = personalInfo.gender
    val platform: Platform get() = technicalInfo.platform
    val socialId: String get() = technicalInfo.socialId
    val deviceType: DeviceType get() = technicalInfo.deviceType
    val firebaseToken: String? get() = technicalInfo.firebaseToken
    val language: String? get() = personalInfo.language
    val isPhoneNumberVerified: Boolean get() = technicalInfo.isPhoneNumberVerified
    val dietaryOption: String? get() = personalInfo.dietaryOption
    val relationshipStatus: RelationshipStatus? get() = personalInfo.relationshipStatus
    val isSameRelationshipConsidered: Boolean? get() = personalInfo.isSameRelationshipConsidered
    val job: Job? get() = personalInfo.job
    val isAllowNotify: Boolean get() = technicalInfo.isAllowNotify
    val osVersion: String get() = technicalInfo.osVersion
    val servicePermission: Boolean get() = acceptanceInfo.servicePermission
    val privatePermission: Boolean get() = acceptanceInfo.privatePermission
    val marketingPermission: Boolean get() = acceptanceInfo.marketingPermission

    constructor(
        id: Long? = null,
        status: UserStatus,
        username: String?,
        phoneNumber: String?,
        nickname: String?,
        birth: LocalDate?,
        city: City?,
        county: County?,
        gender: Gender?,
        platform: Platform,
        socialId: String,
        deviceType: DeviceType,
        osVersion: String,
        isPhoneNumberVerified: Boolean,
        job: Job?,
        firebaseToken: String?,
        language: String?,
        dietaryOption: String?,
        relationshipStatus: RelationshipStatus?,
        isSameRelationshipConsidered: Boolean?,
        isAllowNotify: Boolean,
        servicePermission: Boolean,
        privatePermission: Boolean,
        marketingPermission: Boolean,
    ) : this(
        id = id,
        status = status,
        acceptanceInfo = AcceptanceInfo(
            servicePermission = servicePermission,
            privatePermission = privatePermission,
            marketingPermission = marketingPermission
        ),
        personalInfo = PersonalInfo(
            username = username,
            phoneNumber = phoneNumber,
            nickname = nickname,
            birth = birth,
            city = city,
            county = county,
            gender = gender,
            job = job,
            language = language,
            dietaryOption = dietaryOption,
            relationshipStatus = relationshipStatus,
            isSameRelationshipConsidered = isSameRelationshipConsidered
        ),
        technicalInfo = TechnicalInfo(
            platform = platform,
            socialId = socialId,
            deviceType = deviceType,
            osVersion = osVersion,
            firebaseToken = firebaseToken,
            isPhoneNumberVerified = isPhoneNumberVerified,
            isAllowNotify = isAllowNotify
        )
    )

    fun updateInfo(
        username: String? = null,
        phoneNumber: String? = null,
        nickname: String? = null,
        birth: LocalDate? = null,
        city: City? = null,
        county: County? = null,
        gender: Gender? = null,
        job: Job? = null,
        language: String? = null,
        dietaryOption: String? = null,
        relationshipStatus: RelationshipStatus? = null,
        isSameRelationshipConsidered: Boolean? = null,
        platform: Platform? = null,
        socialId: String? = null,
        deviceType: DeviceType? = null,
        firebaseToken: String? = null,
        isPhoneNumberVerified: Boolean? = null,
        isAllowNotify: Boolean? = null
    ) {
        personalInfo = personalInfo.copy(
            username = username ?: personalInfo.username,
            phoneNumber = phoneNumber ?: personalInfo.phoneNumber,
            nickname = nickname ?: personalInfo.nickname,
            birth = birth ?: personalInfo.birth,
            city = city ?: personalInfo.city,
            county = county ?: personalInfo.county,
            gender = gender ?: personalInfo.gender,
            job = job ?: personalInfo.job,
            language = language ?: personalInfo.language,
            dietaryOption = dietaryOption ?: personalInfo.dietaryOption,
            relationshipStatus = relationshipStatus ?: personalInfo.relationshipStatus,
            isSameRelationshipConsidered = isSameRelationshipConsidered ?: personalInfo.isSameRelationshipConsidered
        )

        technicalInfo = technicalInfo.copy(
            platform = platform ?: technicalInfo.platform,
            socialId = socialId ?: technicalInfo.socialId,
            deviceType = deviceType ?: technicalInfo.deviceType,
            osVersion = technicalInfo.osVersion,
            firebaseToken = firebaseToken ?: technicalInfo.firebaseToken,
            isPhoneNumberVerified = isPhoneNumberVerified ?: technicalInfo.isPhoneNumberVerified,
            isAllowNotify = isAllowNotify ?: technicalInfo.isAllowNotify
        )
    }

    fun activate() {
        require(status == UserStatus.REGISTERED) { "등록된 사용자만 활성화할 수 있습니다." }
        status = UserStatus.ACTIVE
    }

    companion object {
        fun register(
            servicePermission: Boolean,
            privatePermission: Boolean,
            marketingPermission: Boolean,
            platform: Platform,
            socialId: String,
            deviceType: DeviceType,
            osVersion: String,
            isAllowNotify: Boolean,
            firebaseToken: String?
        ): User {
            return User(
                status = UserStatus.REGISTERED,
                personalInfo = PersonalInfo.createEmpty(),
                technicalInfo = TechnicalInfo(
                    platform = platform,
                    socialId = socialId,
                    deviceType = deviceType,
                    osVersion = osVersion,
                    firebaseToken = firebaseToken,
                    isAllowNotify = isAllowNotify
                ),
                acceptanceInfo = AcceptanceInfo(
                    servicePermission = servicePermission,
                    privatePermission = privatePermission,
                    marketingPermission = marketingPermission
                )
            )
        }
    }
}
