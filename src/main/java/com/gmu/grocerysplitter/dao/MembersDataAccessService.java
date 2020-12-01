package com.gmu.grocerysplitter.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gmu.grocerysplitter.model.Member;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;


@Repository("fakedoa")
public class MembersDataAccessService implements MemberDoa {

    public static List<Member> membersDB = new ArrayList<>();
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MembersDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public int addNewMember(UUID id, Member member) {
        UUID randomUUID = UUID.randomUUID();
        final String sql = "INSERT INTO Member (userEmail, userPassword, firstName, lastName) VALUES (?, ?, ?, ?);";
       
        int insert = jdbcTemplate.update(sql, randomUUID.toString(), member.getUserEmail(),
                                         member.getUserPassword(), member.getFirstname(), member.getLastname());
        // membersDB.add(new Member(id, member.getFirstname(), member.getLastname(), member.getUserEmail(),
        //         member.getReciepts(), member.getBills()));
        return insert;
    }

    // email will be used as username
    public boolean isValidMember(String email) {

        final String sql = "SELECT count(*) FROM Member WHERE userEmail = ?";
        int count = jdbcTemplate.queryForObject(sql, new Object [] {email}, Integer.class);
    
        if(count > 0 )
            return true;

        return false;
        // boolean valid = false;

    }

    public Member getMember(String email) {

        if (isValidMember(email)) {
            Member m = null;
            for (int i = 0; i < membersDB.size(); i++) {
                m = membersDB.get(i);
                if (m.getUserEmail().equalsIgnoreCase(email)) {
                    return m;
                }
            }
        }
        return null;
    }

    @Override
    public List<Member> selectAllUsers() {
        // TODO Auto-generated method stub
        return membersDB;
    }

    @Override
    public Optional<Member> selectMemberById(UUID id) {
        return membersDB.stream()
                .filter(member -> member.getID().equals(id))
                .findFirst();
    }

    @Override
    public int updateMember(UUID id, Member memberUpdate) {
        return selectMemberById(id).map(
            member-> {
                int indexOfMemberToUpdate = membersDB.indexOf(member);
                if(indexOfMemberToUpdate >= 0){
                    membersDB.set(indexOfMemberToUpdate, new Member (id, memberUpdate.getUserEmail(), memberUpdate.getUserPassword(),
                                                memberUpdate.getFirstname(), memberUpdate.getLastname(), 
                                                memberUpdate.getReciepts(), memberUpdate.getBills()));
                    return 1;
                }
                return 0;
             
                }).orElse(0);
    }

    
}
