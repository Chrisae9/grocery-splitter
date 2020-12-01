package com.gmu.grocerysplitter.dao;

import java.util.UUID;

import com.gmu.grocerysplitter.model.Member;

public interface MembersDoa {
    
    boolean addNewMember(UUID id, Member member);

    default boolean addNewMember (Member member)
    {
        UUID id = UUID.randomUUID();
        return addNewMember(id, member);
    }

    boolean isValidMember(String email);


}
