package com.gmatieso.mwanzo.membership.service;

import com.gmatieso.mwanzo.membership.dtos.GroupRequest;
import com.gmatieso.mwanzo.membership.dtos.GroupResponse;
import com.gmatieso.mwanzo.membership.dtos.GroupResponseBasic;
import com.gmatieso.mwanzo.membership.entity.Group;
import com.gmatieso.mwanzo.membership.entity.Member;
import com.gmatieso.mwanzo.membership.mappers.GroupMapper;
import com.gmatieso.mwanzo.membership.repository.GroupRepository;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public class GroupServiceImpl implements GroupService {

    private final MemberService memberService;
    private final GroupMapper groupMapper;
    private final GroupRepository groupRepository;

    public GroupServiceImpl(MemberService memberService, GroupMapper groupMapper, GroupRepository groupRepository) {
        this.memberService = memberService;
        this.groupMapper = groupMapper;
        this.groupRepository = groupRepository;
    }

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

        List<Long> memberId = request.membersId();

        List<Member> member = memberService.getMembersByIdOrThrow(memberId);

        Group group = new Group();
        group.setName(request.name());
        group.setRegistrationNumber(request.registrationNumber());
        group.setEmail(request.email());
        group.setLocation(request.email());
        group.setDateRegistered(request.dateRegistered());
        group.setPostalAddress(request.postalAddress());
        group.setGroupCategory(request.groupCategory());
        group.setSector(request.sector());
        group.setMembers(member);

       Group savedGroup = groupRepository.save(group);
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
