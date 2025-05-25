package com.gmatieso.mwanzo.membership.entity;

import com.gmatieso.mwanzo.common.utils.MemberStatus;
import com.gmatieso.mwanzo.common.utils.MemberType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "Member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Column(name = "members_name", nullable = false)
    private String name;


    @Enumerated(EnumType.STRING)
    @Column(name = "member_type", nullable = false)
    private MemberType memberType;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @Column(name = "registration_fees",nullable = false)
    private BigDecimal registrationFees;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MemberStatus status = MemberStatus.ACTIVE;

    @Column(name = "exit_notice_date")
    private LocalDate exitNoticeDate;

    @Column(name = "exit_date")
    private LocalDate exitDate;


}
