package com.gmatieso.mwanzo.common.utils;

import com.gmatieso.mwanzo.common.exception.BadRequestException;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public enum RoleGroup {
    ALL,
    ADMIN,
    FINANCE ,
    LOAN,
    MEMBERS;

    private static final Map<RoleGroup, Set<RoleEnum>> ROLE_MAPPING = new EnumMap<>(RoleGroup.class);

    static {
        ROLE_MAPPING.put(ADMIN, EnumSet.of(RoleEnum.SUPER_ADMIN,RoleEnum.ADMIN));
        ROLE_MAPPING.put(FINANCE,EnumSet.of(RoleEnum.TREASURER, RoleEnum.AUDITOR));
        ROLE_MAPPING.put(LOAN,EnumSet.of(RoleEnum.LOAN_OFFICER,RoleEnum.CREDIT_COMMITTEE));
        ROLE_MAPPING.put(MEMBERS,EnumSet.of(RoleEnum.INDIVIDUAL_MEMBER,RoleEnum.GROUP_MEMBER,RoleEnum.GROUP_REPRESENTATIVE));
    }

    public Set<RoleEnum> getRoles(){
        return ROLE_MAPPING.get(this);
    }

    public static Map<RoleGroup, Set<RoleEnum>> getAllRoles() {
        return ROLE_MAPPING;
    }

    public static Set<RoleEnum> getRolesByGroup(RoleGroup roleGroup) {
        return roleGroup.getRoles();
    }

    public static RoleGroup fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        try {
            return RoleGroup.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid role group: " + value);
        }
    }


}
