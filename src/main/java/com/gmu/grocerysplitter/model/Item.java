package com.gmu.grocerysplitter.model;

import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
	
    @NotBlank
    private final String name;
    @NotBlank
    private final double cost;
    public Item(@JsonProperty("name") String name, @JsonProperty("cost") double cost) {
        this.name = name;
		this.cost = cost;
    }

    public String getName() {
        return this.name;
    }

    public double getCost() {
    	return this.cost;
    }
}
