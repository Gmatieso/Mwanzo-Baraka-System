package com.gmatieso.mwanzo.membership.controller;

import com.gmatieso.mwanzo.common.config.ApiConfig;
import com.gmatieso.mwanzo.common.response.ApiResponseEntity;
import com.gmatieso.mwanzo.membership.dtos.MemberRequest;
import com.gmatieso.mwanzo.membership.dtos.MemberResponseBasic;
import com.gmatieso.mwanzo.membership.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping(MemberController.PATH)
public class MemberController {
    public static final String PATH = ApiConfig.BASE_API_PATH + "membership";

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody @Valid MemberRequest memberRequest) {
        MemberResponseBasic response =  memberService.createMember(memberRequest);
        return ApiResponseEntity.success("Members created successfully", response);
    }

    @GetMapping
    public ResponseEntity<?> getMembers(Pageable pageable){
       Page<MemberResponseBasic> responsePage =  memberService.getMembers(pageable);
       return  ApiResponseEntity.success("Members retrieved successfully", responsePage);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getMember( @PathVariable Long id){
        MemberResponseBasic response = memberService.getMember(id);
        return ApiResponseEntity.success("Member retrieved successfully", response);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> UpdateMember(@PathVariable Long id, @RequestBody MemberRequest memberRequest){
        MemberResponseBasic response =   memberService.updateMember(id, memberRequest);
        return  ApiResponseEntity.success("Member updated successfully", response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id){
                memberService.deleteMember(id);
        return  ApiResponseEntity.success("Member deleted successfully",null);
    }
}
