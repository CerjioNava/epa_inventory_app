package com.example.epa_inventory_app.infrastructure.drivenAdapters.inventory;

import com.example.epa_inventory_app.domain.model.inventory.Inventory;
import com.example.epa_inventory_app.infrastructure.drivenAdapters.inventory.data.InventoryData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InventoryRepository extends ReactiveMongoRepository<InventoryData, String> {
    Flux<InventoryData> findAllBy(Pageable pageable);

    Flux<Inventory> saveAll(List<Inventory> inventoryList);

}
