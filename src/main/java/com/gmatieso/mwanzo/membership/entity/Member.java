package com.gmatieso.mwanzo.membership.entity;

import com.gmatieso.mwanzo.common.utils.Status;
import com.gmatieso.mwanzo.common.utils.MemberType;
import com.gmatieso.mwanzo.loans.entity.Guarantor;
import com.gmatieso.mwanzo.loans.entity.Loan;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "contribution_id")
    @OneToMany( mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Contribution> contribution;

    @OneToOne(mappedBy = "member")
    private Share share;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Loan> loan;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Guarantor guarantor;


    @Enumerated(EnumType.STRING)
    @Column(name = "member_type", nullable = false)
    private MemberType memberType;

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;

    @Column(name = "registration_fees",nullable = false)
    private BigDecimal registrationFees;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.ACTIVE;

    @Column(name = "exit_notice_date")
    private LocalDateTime exitNoticeDate;

    @Column(name = "exit_date")
    private LocalDateTime exitDate;


}
