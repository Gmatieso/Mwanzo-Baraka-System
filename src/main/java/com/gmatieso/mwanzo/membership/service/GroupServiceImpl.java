package com.gmatieso.mwanzo.membership.service;

import com.gmatieso.mwanzo.membership.dtos.GroupRequest;
import com.gmatieso.mwanzo.membership.dtos.GroupResponse;
import com.gmatieso.mwanzo.membership.dtos.GroupResponseBasic;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public class GroupServiceImpl implements GroupService {
    @Override
    public Page<GroupResponse> getAllGroups(Pageable pageable) {
        return null;
    }

    @Override
    public GroupResponse getContributionById(Long id) {
        return null;
    }

    @Override
    public GroupResponseBasic createGroup(GroupRequest request) {
        return null;
    }

    @Override
    public GroupResponseBasic updateGroups(Long id, GroupRequest request) {
        return null;
    }

    @Override
    public Void deleteGroup(Long id) {
        return null;
    }
}
