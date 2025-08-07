package com.gmatieso.mwanzo.membership.service;

import com.gmatieso.mwanzo.membership.dtos.GroupRequest;
import com.gmatieso.mwanzo.membership.dtos.GroupResponse;
import com.gmatieso.mwanzo.membership.dtos.GroupResponseBasic;
import com.gmatieso.mwanzo.membership.entity.Group;
import com.gmatieso.mwanzo.membership.entity.Member;
import com.gmatieso.mwanzo.membership.mappers.GroupMapper;
import com.gmatieso.mwanzo.membership.repository.GroupRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
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
    public Page<GroupResponseBasic> getAllGroups(Pageable pageable) {
        Page<Group>  groupPage = groupRepository.findAll(pageable);
        return groupPage.map(groupMapper::toResponseBasic);
    }

    @Override
    public GroupResponse getGroupById(Long id) {
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
        group.setRegistrationFee(request.registrationFee());
        group.setPhone(request.phone());
        group.setMembers(member);

       Group savedGroup = groupRepository.save(group);
        return groupMapper.toResponseBasic(savedGroup);

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
