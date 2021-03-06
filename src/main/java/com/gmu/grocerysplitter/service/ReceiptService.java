package com.gmu.grocerysplitter.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gmu.grocerysplitter.dao.ReceiptDao;
import com.gmu.grocerysplitter.model.Receipt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {

    private final ReceiptDao receiptDao;

    @Autowired
    public ReceiptService(@Qualifier("receiptDao") ReceiptDao receiptDao) {
        this.receiptDao = receiptDao;
    }

    public int addReceipt(Receipt receipt) {
        return receiptDao.insertReceipt(receipt);
    }

    public List<Receipt> getAllReceipts() {
        return receiptDao.selectAllReceipts();
    }

    public Optional<Receipt> getReceiptById(UUID id){
        return receiptDao.selectReceiptById(id);
    }

    public int deleteReceipt(UUID id){
        return receiptDao.deleteReceiptById(id);
    }

    public int updateReceipt(UUID id, Receipt receipt){
        return receiptDao.updateReceiptById(id, receipt);
    }


}
