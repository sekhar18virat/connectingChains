package com.example.cc.service;

import com.example.cc.entity.Inventory;
import com.example.cc.helper.CSVHelper;
import com.example.cc.repo.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileService {

    @Autowired
    InventoryRepository repository;

    public void save(MultipartFile file) {
        try {
            List<Inventory> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public List<Inventory> getAllInventoryBasedOnSupplierIdAndProductName(String supplierId, String productName) {
        return repository.findAllBySupplierIdAndProductName(supplierId, productName);
    }

    public List<Inventory> getAllInventoryBasedOnSupplierId(String supplierId) throws ParseException {
        List<Inventory> tempResults = repository.findAllBySupplierIdOnly(supplierId);
        String pattern = "dd/MM/yyyy";
        String now = new SimpleDateFormat(pattern).format(new Date());
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(now);
        List<Inventory> results = new ArrayList<>();
        for (Inventory inv : tempResults) {
            if (inv.getExp().compareTo(date1) < 0) {
                results.add(inv);
            }
        }
        return results;
    }
}
