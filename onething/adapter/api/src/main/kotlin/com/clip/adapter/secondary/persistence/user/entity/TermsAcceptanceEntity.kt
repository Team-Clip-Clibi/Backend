package com.clip.adapter.secondary.persistence.user.entity

import com.clip.adapter.secondary.persistence.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "terms_acceptance")
class TermsAcceptanceEntity(
    val servicePermission: Boolean,

    val privatePermission: Boolean,

    val marketingPermission: Boolean,

    @MapsId
    @OneToOne(targetEntity = UserEntity::class, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    val user: UserEntity,

    @Id
    val id: Long = 0L
) : BaseEntity() {

}