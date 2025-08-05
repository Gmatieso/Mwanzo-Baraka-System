package com.gmatieso.mwanzo.membership.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
        name = "contribution",
        uniqueConstraints= {@UniqueConstraint(columnNames = "member_id")}
)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getGroupShareAmount() {
        return groupShareAmount;
    }

    public void setGroupShareAmount(BigDecimal groupShareAmount) {
        this.groupShareAmount = groupShareAmount;
    }

    public BigDecimal getIndividualShareAmount() {
        return individualShareAmount;
    }

    public void setIndividualShareAmount(BigDecimal individualShareAmount) {
        this.individualShareAmount = individualShareAmount;
    }

    public LocalDateTime getContributionDate() {
        return contributionDate;
    }

    public void setContributionDate(LocalDateTime contributionDate) {
        this.contributionDate = contributionDate;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
