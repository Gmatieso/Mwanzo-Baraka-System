package com.gmatieso.mwanzo.membership.controller;

import com.gmatieso.mwanzo.common.config.ApiConfig;
import com.gmatieso.mwanzo.membership.dtos.MemberRequest;
import com.gmatieso.mwanzo.membership.service.MemberService;
import jakarta.validation.Valid;
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
        return memberService.createMember(memberRequest);

    }

    @GetMapping
    public ResponseEntity<?> getMembers(Pageable pageable){
       return memberService.getMembers(pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getMember( @PathVariable Long id){
        return memberService.getMember(id);
    }
}
