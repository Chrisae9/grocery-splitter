package com.gmu.grocerysplitter.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gmu.grocerysplitter.model.Bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository("billDao")
public class BillDataAccessService implements BillDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BillDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	@Override
	public int insertBill(UUID id, Bill Bill) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Bill> selectAllBills() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Bill> selectBillById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteBillById(UUID id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBillById(UUID id, Bill Bill) {
		// TODO Auto-generated method stub
		return 0;
	}
}
