package com.gmu.grocerysplitter.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gmu.grocerysplitter.model.Receipt;

public interface ReceiptDao {

    int insertReceipt(UUID id, Receipt receipt);

    default int insertReceipt(Receipt receipt) {
        UUID id = UUID.randomUUID();
        return insertReceipt(id, receipt);
    }

    List<Receipt> selectAllReceipts();

    Optional<Receipt> selectReceiptById(UUID id);

    int deleteReceiptById(UUID id);

    int updateReceiptById(UUID id, Receipt receipt);
}
