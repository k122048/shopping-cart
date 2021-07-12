package com.example.demo.requestbody;

public class InventoryAddRequest {

    private int inventoryId;
    private float quantity;

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity( float quantity) {
        this.quantity = quantity;
    }
}
