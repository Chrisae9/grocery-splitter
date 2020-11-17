package com.gmu.grocerysplitter.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gmu.grocerysplitter.model.Bill;

public interface BillDao {

    int insertBill(UUID id, Bill Bill);

    default int insertBill(Bill Bill) {
        UUID id = UUID.randomUUID();
        return insertBill(id, Bill);
    }

    List<Bill> selectAllBills();

    Optional<Bill> selectBillById(UUID id);

    int deleteBillById(UUID id);

    int updateBillById(UUID id, Bill Bill);
}
