package com.gmatieso.mwanzo.loans.entity;

import com.gmatieso.mwanzo.common.utils.ConfirmedEnum;
import com.gmatieso.mwanzo.membership.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Guarantor")
public class Guarantor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guarantor_id", nullable = false)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(name = "confirmed")
    private ConfirmedEnum confirmedEnum;

    @ManyToOne()
    @JoinColumn(name = "loan_id")
    private Loan loan;
}
