package com.example.epa_inventory_app.domain.model.inventory.gateway;

import com.example.epa_inventory_app.domain.model.inventory.Inventory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.domain.Pageable;

public interface InventoryGateway {

    Flux<Inventory> getAllInventory(Pageable pageable);
    Mono<Inventory> getInventoryById(String id);
    Mono<Inventory> saveInventory(Inventory inventory);
//    Flux<Inventory> saveInventoryList(Inventory inventory);
    Mono<Inventory> updateInventory(String id, Inventory inventory);
    Mono<Void> deleteInventoryById(String id);

}
