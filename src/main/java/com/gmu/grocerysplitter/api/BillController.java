package com.gmu.grocerysplitter.api;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.gmu.grocerysplitter.model.Item;
import com.gmu.grocerysplitter.model.Bill;
import com.gmu.grocerysplitter.service.BillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/bill")
@RestController
public class BillController {
	
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping
    public void addBill(@Valid @NotNull @RequestBody Bill bill) {
        billService.addBill(bill);
    }

    @GetMapping
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }

    @GetMapping(path = "/{id}")
    public Bill getBillById(@PathVariable("id") UUID id) {
        // return 404 instead of null
        return billService.getBillById(id).orElse(null);
    }

    @GetMapping(path = "/{id}/contributors")
    public List<String> getContributors(@PathVariable("id") UUID id) {
        return billService.getBillById(id).orElse(null).getContributors();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteBillById(@PathVariable("id") UUID id) {
        billService.deleteBill(id);
    }

    @PutMapping(path = "/{id}")
    public void updateBill(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Bill Bill) {
        billService.updateBill(id, Bill);
    }

}
