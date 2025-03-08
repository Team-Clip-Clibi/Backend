package com.clip.user.entity;

import com.clip.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TermsAcceptance extends BaseEntity {
    @Id
    private Long id;

    @Column
    private boolean servicePermission;

    @Column
    private boolean privatePermission;

    @Column
    private boolean marketingPermission;

    @MapsId
    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;
}
