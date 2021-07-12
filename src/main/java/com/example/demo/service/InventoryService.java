package com.example.demo.service;

import com.example.demo.dao.InventoryDAO;
import com.example.demo.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {

    private InventoryDAO inventoryDAO;

    @Autowired
    public InventoryService( InventoryDAO inventoryDAO ){
        this.inventoryDAO = inventoryDAO;
    }

    @Transactional
    public List<Inventory> findAll(){
        return inventoryDAO.findAll();
    }

    @Transactional
    public Inventory findInventoryById( int id ){
        return inventoryDAO.findById( id );
    }




}
