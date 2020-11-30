package com.gmu.grocerysplitter.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {

    @NotBlank
    private final String name;
    @NotNull
    private final Double cost;

    public Item(@JsonProperty("name") String name, @JsonProperty("cost") Double cost) {

        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return this.name;
    }

    public Double getCost() {
        return this.cost;
    }

}
