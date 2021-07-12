package com.example.demo.service;

import com.example.demo.dao.InventoryDAO;
import com.example.demo.model.Inventory;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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


    public void readInventoriesFromExcel( XSSFWorkbook workbook ){
        XSSFSheet sheet = workbook.getSheetAt( 0 );
        List<Inventory> inventories = new ArrayList<>();

        for ( int currentRow = 1; currentRow < sheet.getPhysicalNumberOfRows(); currentRow++ ) {
            // fetch row
            XSSFRow row = sheet.getRow(currentRow);

            Inventory inventory = new Inventory();

            if ( row.getCell( 0 ) != null ) {
                row.getCell(0 ).setCellType( CellType.STRING );
                String inventoryName = row.getCell( 0 ).getStringCellValue().trim();
                inventory.setTitle( inventoryName );
            }

            if ( row.getCell( 1 ) != null ) {
                row.getCell( 1 ).setCellType( CellType.NUMERIC );
                float inventoryPrice = (float)row.getCell(1).getNumericCellValue();
                inventory.setPrice( inventoryPrice );
            }

            if( row.getCell(2) != null ){
                row.getCell(2).setCellType( CellType.NUMERIC );
                float inventoryQuantity = (float)row.getCell(2).getNumericCellValue();
                inventory.setQuantity(inventoryQuantity);
            }
            inventoryDAO.save(inventory);

        }


    }

    @Transactional
    public void save( Inventory inventory ){
        inventoryDAO.save( inventory );
    }

}
