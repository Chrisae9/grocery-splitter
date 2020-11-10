package com.gmu.grocerysplitter.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gmu.grocerysplitter.model.Bill;

import org.springframework.stereotype.Repository;

@Repository("billfakeDao")
public class FakeBillDataAccessService implements BillDao {

    private static List<Bill> DB = new ArrayList<>();

    @Override
    public int insertBill(UUID id, Bill Bill) {
        DB.add(new Bill(id, Bill.getBillName(), Bill.getReceipt(), Bill.getContributors()));
        return 1;
    }

    @Override
    public List<Bill> selectAllBills() {
        return DB;
    }

    @Override
    public Optional<Bill> selectBillById(UUID id) {
        return DB.stream().filter(Bill -> Bill.getId().equals(id)).findFirst();
    }

    @Override
    public int deleteBillById(UUID id) {
        Optional<Bill> rOptional = selectBillById(id);
        if (rOptional.isEmpty()) {
            return 0;
        }
        DB.remove(rOptional.get());
        return 1;
    }

    @Override
    public int updateBillById(UUID id, Bill Bill) {
        return selectBillById(id).map(r -> {
            int indexOfPersonToUpdate = DB.indexOf(r);
            if (indexOfPersonToUpdate >= 0) {
                DB.set(indexOfPersonToUpdate, new Bill(id, Bill.getBillName(), Bill.getReceipt(), Bill.getContributors()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }
}
