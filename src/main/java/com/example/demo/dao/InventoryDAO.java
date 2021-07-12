package com.example.demo.dao;

import com.example.demo.model.Inventory;

import java.util.List;

public interface InventoryDAO {

    public List<Inventory> findAll();
    public Inventory findById( int id );
    public void save( Inventory inventory );
}
