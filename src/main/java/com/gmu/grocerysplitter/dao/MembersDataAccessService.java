package com.gmu.grocerysplitter.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gmu.grocerysplitter.model.Member;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("memberDao")
public class MembersDataAccessService implements MemberDao {

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


    @Override
    public Optional<Member> selectMemberByEmail(String userEmail) {
        final String sql = "SELECT * FROM Member WHERE userEmail = ?";
        Member selectedMember = jdbcTemplate.queryForObject(sql,new Object[] { userEmail }, (rs, rowNum) -> new Member(UUID.fromString(rs.getString("id")),
        rs.getString("userEmail"),rs.getString("userPassword"), rs.getString("firstName"), rs.getString("lastName"), null, null));

        return Optional.ofNullable(selectedMember);
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

    @Override
    public List<Member> selectAllUsers() {
        final String sql = "SELECT * FROM Member";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new Member(UUID.fromString(rs.getString("id")),
                rs.getString("userEmail"),rs.getString("userPassword"), rs.getString("firstName"), rs.getString("lastName"), null, null));
    }

    @Override
    public int updateMember(String userEmail, Member member) {
    
           return 0;
        
    }
    // // email will be used as username
    // public boolean isValidMember(String email) {

    //     final String sql = "SELECT count(*) FROM Member WHERE userEmail = ?";
    //     int count = jdbcTemplate.queryForObject(sql, new Object [] {email}, Integer.class);
    
    //     if(count > 0 )
    //         return true;

    //     return false;
    //     // boolean valid = false;

    // }

    // public Member getMember(String email) {

    //     if (isValidMember(email)) {
    //         Member m = null;
    //         for (int i = 0; i < membersDB.size(); i++) {
    //             m = membersDB.get(i);
    //             if (m.getUserEmail().equalsIgnoreCase(email)) {
    //                 return m;
    //             }
    //         }
    //     }
    //     return null;
    // }

    // @Override
    // public List<Member> selectAllUsers() {
    //     // TODO Auto-generated method stub
    //     return membersDB;
    // }

    // @Override
    // public Optional<Member> selectMemberById(UUID id) {
    //     return membersDB.stream()
    //             .filter(member -> member.getID().equals(id))
    //             .findFirst();
    // }

    // @Override
    // public int updateMember(UUID id, Member memberUpdate) {
    //     return 0;
    // }

    // @Override
    // public Optional<Member> selectMemberByEmail(String email) {
    //     // TODO Auto-generated method stub
    //     return null;
    // }
}

