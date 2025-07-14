package com.clip.domain.user

import com.clip.domain.user.enums.*
import java.time.LocalDate

internal data class AcceptanceInfo (
    val servicePermission: Boolean,
    val privatePermission: Boolean,
    val marketingPermission: Boolean
) {
    init {
        require(servicePermission) { "서비스 이용약관 동의는 필수입니다." }
        require(privatePermission) { "개인정보 처리방침 동의는 필수입니다." }
    }
}

internal data class PersonalInfo (
    val username: String?,
    val phoneNumber: String?,
    val nickname: String?,
    val birth: LocalDate?,
    val city: City?,
    val county: County?,
    val gender: Gender?,
    val job: Job?,
    val language: String?,
    val dietaryOption: String?,
    val relationshipStatus: RelationshipStatus?,
    val isSameRelationshipConsidered: Boolean?
) {
    companion object {
        fun createEmpty() = PersonalInfo(
            username = null,
            phoneNumber = null,
            nickname = null,
            birth = null,
            city = null,
            county = null,
            gender = null,
            job = null,
            language = null,
            dietaryOption = null,
            relationshipStatus = null,
            isSameRelationshipConsidered = null
        )
    }
}

internal data class TechnicalInfo (
    val platform: Platform,
    val socialId: String,
    val deviceType: DeviceType,
    val osVersion: String,
    val firebaseToken: String?,
    val isPhoneNumberVerified: Boolean = false,
    val isAllowNotify: Boolean
) {
    init {
        require(socialId.isNotBlank()) { "소셜 ID는 필수입니다." }
        require(osVersion.isNotBlank()) { "OS 버전은 필수입니다." }
        if (isAllowNotify) {
            require(!firebaseToken.isNullOrBlank()) { "알림을 허용하려면 파이어베이스 토큰이 필요합니다." }
        }
    }
}
