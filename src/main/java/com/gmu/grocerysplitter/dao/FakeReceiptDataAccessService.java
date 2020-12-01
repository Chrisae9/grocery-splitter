package com.gmu.grocerysplitter.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gmu.grocerysplitter.model.Receipt;

import org.springframework.stereotype.Repository;

@Repository("receiptfakeDao")
public class FakeReceiptDataAccessService implements ReceiptDao {

    private static List<Receipt> DB = new ArrayList<>();

    @Override
    public int insertReceipt(UUID id, Receipt receipt) {
        DB.add(new Receipt(id, receipt.getName(), receipt.getItems()));
        return 1;
    }

    @Override
    public List<Receipt> selectAllReceipts() {
        return DB;
    }

    @Override
    public Optional<Receipt> selectReceiptById(UUID id) {
        return DB.stream().filter(receipt -> receipt.getId().equals(id)).findFirst();
    }

    @Override
    public int deleteReceiptById(UUID id) {
        Optional<Receipt> rOptional = selectReceiptById(id);
        if (rOptional.isEmpty()) {
            return 0;
        }
        DB.remove(rOptional.get());
        return 1;
    }

    @Override
    public int updateReceiptById(UUID id, Receipt receipt) {
        return selectReceiptById(id).map(r -> {
            int indexOfPersonToUpdate = DB.indexOf(r);
            if (indexOfPersonToUpdate >= 0) {
                DB.set(indexOfPersonToUpdate, new Receipt(id, receipt.getName(), receipt.getItems()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }
}
