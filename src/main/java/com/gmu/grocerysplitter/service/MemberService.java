package com.gmu.grocerysplitter.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gmu.grocerysplitter.dao.ReceiptDao;
import com.gmu.grocerysplitter.model.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final ReceiptDao memberDao;

    @Autowired
    public MemberService(@Qualifier("postgres") ReceiptDao memberDao)
    {
        this.memberDao = memberDao;
    }

    public int addMember(Member member)
    {
        return memberDao.addNewMember(member);
    }

    public List<Member> getAllMembers()
    {
        return memberDao.selectAllUsers();
    }
    
    public Optional<Member> getMemberByEmail(String userEmail)
    {
        return memberDao.selectMemberByEmail(userEmail);
    }

	public int updateMember(String userEmail, Member memberToUpdate) {
       return memberDao.updateMember(userEmail, memberToUpdate);
    }

}
