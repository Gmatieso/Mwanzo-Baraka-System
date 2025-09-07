package com.gmatieso.mwanzo.user.user.enums;

import org.springframework.security.core.GrantedAuthority;

@Deprecated
public enum UserRole implements GrantedAuthority {
    ADMIN,
    TREASURER,
    AUDITOR,
    LOAN_OFFICER,
    INDIVIDUAL_MEMBER,
    GROUP_REPRESENTATIVE,
    GROUP_MEMBER,
    CREDIT_COMMITTE;


    @Override
    public String getAuthority() {
        return name();
    }
}

