package com.gmu.grocerysplitter.api;

import java.util.List;
import java.util.UUID;

import com.gmu.grocerysplitter.model.Member;

import com.gmu.grocerysplitter.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService)
    {
        this.memberService = memberService;
    }

    @PostMapping
    public void addMember(@RequestBody Member member)
    {
        memberService.addMember(member);
    }

    @GetMapping
    public List<Member> getAllMembers()
    {
        return memberService.getAllMembers();
    }

    @GetMapping(path = "/{userEmail}")
    public Member getMemberByEmail(@PathVariable("userEmail") String userEmail)
    {
        return memberService.getMemberByEmail(userEmail).orElse(null);
    }

    @PutMapping(path = "/{userEmail}")
    public void updateMember(@PathVariable("userEmail") String userEmail, @RequestBody Member memberToUpdate)
    {
        memberService.updateMember(userEmail, memberToUpdate);
    }
}
