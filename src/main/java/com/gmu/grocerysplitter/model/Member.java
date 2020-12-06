package com.gmu.grocerysplitter.model;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Member {

    private final UUID id;
    @NotBlank
    private final String userEmail;
    @NotBlank
    private final String userPassword;
    @NotBlank
    private final String fName;
    @NotBlank
    private final String lName;

    private final List<Receipt> receipts;
    private final List<Bill> bills;

    public Member(@JsonProperty("id") UUID id, @JsonProperty("userEmail") String userEmail, @JsonProperty("userPassword") String userPassword, @JsonProperty("fName") String fName, 
            @JsonProperty("lName") String lName, @JsonProperty("receipts") List<Receipt> receipts, @JsonProperty("bills") List<Bill> bills)
    {
        this.id = id;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.fName = fName;
        this.lName = lName;
        this.receipts = receipts;
        this.bills = bills;
    }
    
    
    public UUID getID () {return this.id;}

    public String getFirstname() {return this.fName;}
    public String getLastname() {return this.lName;}
    public String getUserEmail() {return this.userEmail;}
    public String getUserPassword() {return this.userPassword;}
    public List<Receipt> getReciepts () {return this.receipts;}
    public List<Bill> getBills() {return this.bills;}
    
    
}
