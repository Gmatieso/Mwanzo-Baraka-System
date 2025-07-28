package com.gmatieso.mwanzo.membership.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false)
    private Long id;

    @Column(name = "group_name", nullable = false)
    private String name;

    @Column(name = "registrationNumber", nullable = false)
    private String registrationNumber;

    @Column(name = "group_email", nullable = false)
    private String email;

    @Column(name = "group_location", nullable = false)
    private String location;

    @Column(name = "date_registered", nullable = false)
    private LocalDateTime dateRegistered;

    @Column(name = "postal_address", nullable = false)
    private String postalAddress;

//    @Column(name = "member_count", nullable = false)
//    private  Long memberCount;

//    @Column(name = "expected_monthly_contribution", nullable = false)
//    private BigDecimal expectedMonthlyContribution;

//    @Column(name = "contribution_schedule", nullable = false)
//    private String contributionSchedule;

    @Column(name = "group_category", nullable = false)
    private String groupCategory;

    @Column(name = "group_sector", nullable = false)
    private String sector;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Member> members = new ArrayList<>();

}
