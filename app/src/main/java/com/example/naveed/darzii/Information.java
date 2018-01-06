package com.example.naveed.darzii;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Naveed on 12/19/2017.
 */

public class Information {

    public Information(String name, String address, String collarSize, String chestSize, String armLength, String shirtLength) {
        this.name = name;
        this.address = address;
        this.collarSize = collarSize;
        this.chestSize = chestSize;
        this.armLength = armLength;
        this.shirtLength = shirtLength;
    }

    @SerializedName("name")
    @Expose

    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("collar_size")
    @Expose
    private String collarSize;
    @SerializedName("chest_size")
    @Expose
    private String chestSize;
    @SerializedName("arm_length")
    @Expose
    private String armLength;
    @SerializedName("shirt_length")
    @Expose
    private String shirtLength;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCollarSize() {
        return collarSize;
    }

    public void setCollarSize(String collarSize) {
        this.collarSize = collarSize;
    }

    public String getChestSize() {
        return chestSize;
    }

    public void setChestSize(String chestSize) {
        this.chestSize = chestSize;
    }

    public String getArmLength() {
        return armLength;
    }

    public void setArmLength(String armLength) {
        this.armLength = armLength;
    }

    public String getShirtLength() {
        return shirtLength;
    }

    public void setShirtLength(String shirtLength) {
        this.shirtLength = shirtLength;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
