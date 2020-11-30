package com.gmu.grocerysplitter.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gmu.grocerysplitter.model.Receipt;
import com.gmu.grocerysplitter.model.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository("postgres")
public class ReceiptDataAccessService implements ReceiptDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReceiptDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertReceipt(Receipt receipt) {
        UUID randomUUID = UUID.randomUUID();
        final String sql = "INSERT INTO Receipt (id, name) VALUES (?, ?);";
        int status = jdbcTemplate.update(sql, randomUUID.toString(), receipt.getName());
        insertAllItems(randomUUID, receipt.getItems());
        return status;
    }

    @Override
    public int insertReceipt(UUID id, Receipt receipt) {
        final String sql = "INSERT INTO Receipt (id, name) VALUES (?, ?);";
        int status = jdbcTemplate.update(sql, id.toString(), receipt.getName());
        insertAllItems(id, receipt.getItems());
        return status;
    }

    @Override
    public List<Receipt> selectAllReceipts() {
        final String sql = "SELECT * FROM Receipt";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new Receipt(UUID.fromString(rs.getString("id")),
                rs.getString("name"), selectAllItems(UUID.fromString(rs.getString("id")))));
    }

    public List<Item> selectAllItems(UUID id) {

        String sql = "SELECT * FROM Item WHERE receiptid =?";

        List<Item> items = new ArrayList<>();
        SqlRowSet srs = jdbcTemplate.queryForRowSet(sql, id.toString());

        while (srs.next()) {
            items.add(new Item(srs.getString("name"), srs.getDouble("cost")));
        }
        return items;
    }

    public void insertAllItems(UUID id, List<Item> items) {

        final String sql = "INSERT INTO Item (receiptid, name, cost) VALUES (?, ?, ?);";

        for (Item item : items) {
            jdbcTemplate.update(sql, id.toString(), item.getName(), item.getCost());
        }
    }

    @Override
    public Optional<Receipt> selectReceiptById(UUID id) {
        final String sql = "SELECT * FROM Receipt WHERE id = ?";
        Receipt selectedReceipt = jdbcTemplate.queryForObject(sql, new Object[] { id.toString() },
                (rs, rowNum) -> new Receipt(UUID.fromString(rs.getString("id")), rs.getString("name"),
                        selectAllItems(UUID.fromString(rs.getString("id")))));
        return Optional.ofNullable(selectedReceipt);
    }

    @Override
    public int deleteReceiptById(UUID id) {
        final String sql = "DELETE FROM Receipt WHERE id = ?";
        return jdbcTemplate.update(sql, id.toString());
    }

    @Override
    public int updateReceiptById(UUID id, Receipt receipt) {
        deleteReceiptById(id);
        return insertReceipt(id, receipt);
        // final String sql = "UPDATE Receipt SET name = ? WHERE id = ?";
        // return jdbcTemplate.update(sql, receipt.getName(), id.toString());
    }
}
