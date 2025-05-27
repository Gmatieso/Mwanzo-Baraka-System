package com.gmatieso.mwanzo.loans.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "Repayment")
public class Repayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repayment_id", nullable = false)
    private Long id;

    @Column(name = "repayment_amount")
    private BigDecimal amount;

    @Column(name = "interest_paid")
    private BigDecimal interestPaid;

    @Column(name = "principal_paid")
    private BigDecimal principalPaid;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id")
    private Loan loan;
}
