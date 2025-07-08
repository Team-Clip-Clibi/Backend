package com.clip.domain.user

import com.clip.domain.user.enums.City
import com.clip.domain.user.enums.County
import com.clip.domain.user.enums.DeviceType
import com.clip.domain.user.enums.Gender
import com.clip.domain.user.enums.Job
import com.clip.domain.user.enums.Platform
import com.clip.domain.user.enums.RelationshipStatus
import java.time.LocalDate

class UserDetail(
    val servicePermission: Boolean,
    val privatePermission: Boolean,
    val marketingPermission: Boolean,
    var username: String? = null,
    var phoneNumber: String? = null,
    var nickname: String? = null,
    var birth: LocalDate? = null,
    var city: City? = null,
    var county: County? = null,
    var gender: Gender? = null,
    var platform: Platform,
    var socialId: String,
    var deviceType: DeviceType,
    var osVersion: String,
    var firebaseToken: String? = null,
    var isPhoneNumVerified: Boolean = false,
    var dietaryOption: String? = null,
    var relationshipStatus: RelationshipStatus? = null,
    var isSameRelationshipConsidered: Boolean? = null,
    var job: Job? = null,
    var isAllowNotify: Boolean,
) {
//    companion object {
//        fun register(
//            platform: Platform,
//            socialId: String,
//            deviceType: DeviceType,
//            osVersion: String,
//            firebaseToken: String,
//            isAllowNotify: Boolean,
//        ) : UserDetail {
//            return UserDetail(
//                platform = platform,
//                socialId = socialId,
//                deviceType = deviceType,
//                firebaseToken = firebaseToken,
//                osVersion = osVersion,
//                isAllowNotify = isAllowNotify,
//            )
//        }
//    }



}
