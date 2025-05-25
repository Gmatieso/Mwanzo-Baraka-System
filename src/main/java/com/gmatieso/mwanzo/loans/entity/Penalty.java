package com.gmatieso.mwanzo.loans.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Penalty")
public class Penalty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "penalty_id", nullable = false)
    private Long id;


    @Column(name = "panalty_date")
    private LocalDateTime penaltyDate;

    @Column(name = "penalty_amount")
    private BigDecimal amount;

    @ManyToOne()
    @JoinColumn(name = "loan_id")
    private Loan loan;
}
