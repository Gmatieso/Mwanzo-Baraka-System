package com.gmatieso.mwanzo.membership.service;

import com.gmatieso.mwanzo.membership.dtos.GroupRequest;
import com.gmatieso.mwanzo.membership.dtos.GroupResponse;
import com.gmatieso.mwanzo.membership.dtos.GroupResponseBasic;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface GroupService {
    Page<GroupResponseBasic> getAllGroups(Pageable pageable);
    GroupResponse getGroupById(Long id);
    GroupResponseBasic createGroup(GroupRequest request);
    GroupResponseBasic updateGroups(Long id, GroupRequest request);
    Void deleteGroup(Long id);

}
