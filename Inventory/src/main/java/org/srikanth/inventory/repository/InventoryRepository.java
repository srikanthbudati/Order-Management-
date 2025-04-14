package org.srikanth.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.srikanth.inventory.model.Inventory;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    //@Override
    Optional<Inventory> findByProductId(String productId);
}
