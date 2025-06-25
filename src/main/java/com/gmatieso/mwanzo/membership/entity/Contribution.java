package com.gmatieso.mwanzo.membership.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "contribution")
public class Contribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "contributionDate")
    private LocalDateTime contributionDate;

    @Column(name = "contribution_amount")
    private BigDecimal amount;


    @Column(name = "group_share_amount")
    private BigDecimal groupShareAmount;

    @Column(name = "individual_share_amount")
    private BigDecimal individualShareAmount;



}
