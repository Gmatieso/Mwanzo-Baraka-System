package com.gmatieso.mwanzo.user.role.enums;

import com.gmatieso.mwanzo.common.exception.BadRequestException;

import java.util.Arrays;
import java.util.List;

public enum RoleEnum {
    SUPER_ADMIN(true),
    ADMIN(true),
    TREASURER(true),
    AUDITOR(true),
    LOAN_OFFICER(true),
    CREDIT_COMMITTEE(true),
    INDIVIDUAL_MEMBER(true),
    GROUP_REPRESENTATIVE(true),
    GROUP_MEMBER(true);

    public final boolean isInternal;

    RoleEnum(boolean isInternal) {
        this.isInternal = isInternal;
    }

    public static List<RoleEnum> getInternalRoles() {
        return Arrays.stream(RoleEnum.values())
                .filter(role -> role.isInternal)
                .toList();
    }

    public static RoleEnum getRoleByName(String roleName) {
        try{
            return RoleEnum.valueOf(roleName);
        }catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid role group: " + roleName);
        }
    }
}
