package com.gmu.grocerysplitter.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gmu.grocerysplitter.model.Member;

public interface MemberDoa {
    
    int addNewMember(UUID id, Member member);

    default int addNewMember (Member member)
    {
        UUID id = UUID.randomUUID();
        return addNewMember(id, member);
    }

    Optional<Member> selectMemberById(UUID id);
    
    boolean isValidMember(String email);

    List<Member> selectAllUsers();

    int updateMember(UUID id, Member member);
    
}
