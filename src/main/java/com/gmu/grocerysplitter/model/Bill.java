package com.gmu.grocerysplitter.model;

import java.util.ArrayList;
import java.util.UUID;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bill {
    private final UUID id;
    @NotBlank
    private final String billName;
    private ArrayList<Item> list = new ArrayList<Item>(); //pull in list from receipt.java

    public Bill(@JsonProperty("id") UUID id, @JsonProperty("billName") String billName) {
        this.id = id;
        this.billName = billName;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.billName;
    }
  
    //Adding item to list should be in receipt.java
    public ArrayList<Item> addItem() {
		return list;
    }  	
}
