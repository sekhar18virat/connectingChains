package com.example.cc.repo;

import com.example.cc.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryRepository extends JpaRepository {
    @Query(value = "select * from inventory where supplierId = ?,name = ?, stock > 0", nativeQuery = true)
    List<Inventory> findAllBySupplierIdAndProductName(String supplierId, String productName);

    @Query(value = "select * from inventory where supplierId = ?", nativeQuery = true)
    List<Inventory> findAllBySupplierIdOnly(String supplierId);

}
