package com.gmu.grocerysplitter.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gmu.grocerysplitter.model.Member;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


<<<<<<< HEAD
@Repository("postgres")
public class MembersDataAccessService implements MemberDoa {
=======
@Repository("memberDao")
public class MembersDataAccessService implements MemberDao {
>>>>>>> d59fa8ea988fe345c226ea813411178441138650

    public static List<Member> membersDB = new ArrayList<>();
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MembersDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public int addNewMember(UUID id, Member member) {
        UUID randomUUID = UUID.randomUUID();
        final String sql = "INSERT INTO Member (id, userEmail, userPassword, firstName, lastName) VALUES (?, ?, ?, ?, ?);";
       
        int insert = jdbcTemplate.update(sql, randomUUID.toString(), member.getUserEmail(),
                                         member.getUserPassword(), member.getFirstname(), member.getLastname());
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
        return 0;
    }

    @Override
    public Optional<Member> selectMemberByEmail(String email) {
        // TODO Auto-generated method stub
        return null;
    }
}

