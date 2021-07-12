package com.example.demo.controller;

import com.example.demo.model.Inventory;
import com.example.demo.service.InventoryService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/inventory")
public class InventoryController {


    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public String list( Model model ){
        List<Inventory> inventories = inventoryService.findAll();
        model.addAttribute("inventories", inventories);
        return "inventory-list";
    }

    @GetMapping( "/bulk-update" )
    public String showBulkAddForm() {
        return "inventory-bulk-update";
    }

    @PostMapping( "/bulk-add" )
    public String readExcelFileAndAddEmployees( @RequestParam( value = "file" ) MultipartFile file,
                                                Model model ) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook( file.getInputStream() );
        inventoryService.readInventoriesFromExcel( workbook );
        return "redirect:/inventory";
    }
}
