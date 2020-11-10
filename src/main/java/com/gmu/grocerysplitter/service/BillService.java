package com.gmu.grocerysplitter.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gmu.grocerysplitter.dao.BillDao;
import com.gmu.grocerysplitter.model.Bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BillService {

    private final BillDao billDao;

    @Autowired
    public BillService(@Qualifier("billfakeDao") BillDao billDao) {
        this.billDao = billDao;
    }

    public int addBill(Bill bill) {
        return billDao.insertBill(bill);
    }

    public List<Bill> getAllBills() {
        return billDao.selectAllBills();
    }

    public Optional<Bill> getBillById(UUID id){
        return billDao.selectBillById(id);
    }

    public int deleteBill(UUID id){
        return billDao.deleteBillById(id);
    }

    public int updateBill(UUID id, Bill bill){
        return billDao.updateBillById(id, bill);
    }


}
