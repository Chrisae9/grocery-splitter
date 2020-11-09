package com.gmu.grocerysplitter.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gmu.grocerysplitter.model.Receipt;
import com.gmu.grocerysplitter.model.Item;

import org.springframework.stereotype.Repository;

@Repository("postgres")
public class ReceiptDataAccessService implements ReceiptDao {

    @Override
    public int insertReceipt(UUID id, Receipt receipt) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Receipt> selectAllReceipts() {
        // TODO Auto-generated method stub
        return List.of(new Receipt(UUID.randomUUID(), "FROM POSTGRES DB", new ArrayList<Item>()));
    }

    @Override
    public Optional<Receipt> selectReceiptById(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int deleteReceiptById(UUID id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updateReceiptById(UUID id, Receipt receipt) {
        // TODO Auto-generated method stub
        return 0;
    }

}
