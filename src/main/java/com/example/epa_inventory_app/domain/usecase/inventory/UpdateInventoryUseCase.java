package com.example.epa_inventory_app.domain.usecase.inventory;

import com.example.epa_inventory_app.domain.model.inventory.Inventory;
import com.example.epa_inventory_app.domain.model.inventory.gateway.InventoryGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateInventoryUseCase implements BiFunction<String, Inventory, Mono<Inventory>> {

    private final InventoryGateway inventoryGateway;

    @Override
    public Mono<Inventory> apply(String id, Inventory inventory) {
        System.out.println("INFO: Request to update an Inventory by id: "+id);
        return inventoryGateway.updateInventory(id, inventory);
    }
}
