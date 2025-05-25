package com.gmatieso.mwanzo.loans.entity;

import com.gmatieso.mwanzo.common.utils.Status;
import com.gmatieso.mwanzo.membership.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id", nullable = false)
    private Long id;

    @Column(name = "loan_amount")
    private BigDecimal amount;

    @Column(name = "loan_date")
    private LocalDateTime loanDate;

    @Column(name = "repayment_period")
    private LocalDateTime repaymentPeriod;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @ManyToOne()
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Guarantor guarantor;

}
