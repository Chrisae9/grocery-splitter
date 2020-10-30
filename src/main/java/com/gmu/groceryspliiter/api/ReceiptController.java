package com.gmu.groceryspliiter.api;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.gmu.groceryspliiter.model.Receipt;
import com.gmu.groceryspliiter.service.ReceiptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/receipt")
@RestController
public class ReceiptController {
    private final ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping
    public void addReceipt(@Valid @NotNull @RequestBody Receipt receipt) {
        receiptService.addReceipt(receipt);
    }

    @GetMapping
    public List<Receipt> getAllReceipts() {
        return receiptService.getAllReceipts();
    }

    @GetMapping(path = "/{id}")
    public Receipt getReceiptById(@PathVariable("id") UUID id){
        //return 404 instead of null
        return receiptService.getReceiptById(id).orElse(null);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteReceiptById(@PathVariable("id") UUID id){
        receiptService.deleteReceipt(id);
    }

    @PutMapping(path = "/{id}")
    public void updateReceipt(@PathVariable("id") UUID id,@Valid @NotNull @RequestBody Receipt receipt){
        receiptService.updateReceipt(id, receipt);
    }

}