package com.example.epa_inventory_app.domain.usecase.inventory;

import com.example.epa_inventory_app.domain.model.inventory.Inventory;
import com.example.epa_inventory_app.domain.model.inventory.gateway.InventoryGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class SaveInventoryUseCase implements Function<Inventory, Mono<Inventory>> {

    private final InventoryGateway inventoryGateway;

    @Override
    public Mono<Inventory> apply(Inventory inventory) {
        System.out.println("INFO: Request to save an Inventory: "+inventory);
        return inventoryGateway.saveInventory(inventory);
    }
}
