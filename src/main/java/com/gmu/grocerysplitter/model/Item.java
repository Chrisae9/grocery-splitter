package com.gmu.grocerysplitter.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    // private final UUID id;
    @NotBlank
    private final String name;
    @NotNull
    private final Double cost;

    public Item(@JsonProperty("name") String name, @JsonProperty("cost") Double cost) {
        // this.id = id;
        this.name = name;
        this.cost = cost;
    }

    // public UUID getId() {
    // return this.id;
    // }

    public String getName() {
        return this.name;
    }

    public Double getCost() {
        return this.cost;
    }

}
