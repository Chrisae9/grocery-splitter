package com.gmu.grocerysplitter.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.gmu.grocerysplitter.model.Member;

public class MembersDataAccessService implements MembersDoa {
    
    public static List<Member> membersDB = new ArrayList<>();


    @Override
    public boolean addNewMember(UUID id, Member member)
    {
        membersDB.add(new Member (id, member.getFirstname(), member.getLastname(), member.getUserEmail(), 
                member.getReciepts(), member.getBills()));
        return true;
    }

    //email will be used as username
    public boolean isValidMember(String email){
        
        //boolean valid = false;
        for(Member m : membersDB)
        {
            if (m.getUserEmail().equalsIgnoreCase(email))
            {
                return true;
            } 
        }
        return false;
    }
    
    public Member getMember (String email)
    {
        
        
        if (isValidMember(email))
        {   
            Member m = null;
            for (int i = 0; i < membersDB.size(); i++)
            {
                m = membersDB.get(i);
                if (m.getUserEmail().equalsIgnoreCase(email))
                {
                    return m;
                }
            }
        }
        return null;
    }

    
}
