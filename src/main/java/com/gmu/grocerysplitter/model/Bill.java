package com.gmu.grocerysplitter.model;

import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Bill {
    private final UUID id;
    @NotBlank
    private final String billName;
    private final Receipt receipt;
    private final List<String> contributors;

    public Bill(@JsonProperty("id") UUID id, @JsonProperty("billName") String billName, 
    		@JsonProperty("receipt") Receipt receipt, @JsonProperty("contributors") List<String> contributors) {
        this.id = id;
        this.billName = billName;
        this.receipt = receipt;
        this.contributors = contributors;
    }

    public UUID getId() {
        return this.id;
    }

    public String getBillName() {
        return this.billName;
    }
    
    public Receipt getReceipt() {
    	return this.receipt;
    }
    
    public List<String> getContributors() {
        return this.contributors;
    }

}
