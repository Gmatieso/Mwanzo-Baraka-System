package com.gmatieso.mwanzo.membership.entity;

import com.gmatieso.mwanzo.common.utils.Status;
import com.gmatieso.mwanzo.common.utils.MemberType;
import com.gmatieso.mwanzo.loans.entity.Guarantor;
import com.gmatieso.mwanzo.loans.entity.Loan;
import com.gmatieso.mwanzo.security.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Column(name = "members_name", nullable = false)
    private String name;

    @Column(name = "contribution_id")
    @OneToMany( mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contribution> contribution;

    @OneToOne(mappedBy = "member")
    private Share share;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Loan> loan;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Guarantor> guarantor;


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

   public String getName(){
        return name;
    }

   public void  setName(String name){
        this.name = name;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
       this.memberType = memberType;
    }

    public LocalDateTime getRegistrationDate() {
       return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
       this.registrationDate = registrationDate;
    }

    public BigDecimal getRegistrationFees() {
       return registrationFees;
    }

    public void setRegistrationFees(BigDecimal registrationFees) {
       this.registrationFees = registrationFees;
    }
}
