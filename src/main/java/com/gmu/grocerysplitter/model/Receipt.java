package com.gmu.grocerysplitter.model;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Receipt {
    private final UUID id;
    @NotBlank
    private final String name;
    private final List<Item> items;

    public Receipt(@JsonProperty("id") UUID id, @JsonProperty("name") String name,
            @JsonProperty("items") List<Item> items) {
        this.id = id;
        this.name = name;
        this.items = items;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<Item> getItems() {
        return this.items;
    }

}
