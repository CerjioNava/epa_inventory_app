package com.example.epa_inventory_app.domain.usecase.inventory;

import com.example.epa_inventory_app.domain.model.inventory.Inventory;
import com.example.epa_inventory_app.domain.model.inventory.gateway.InventoryGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public class SaveInventoryListUseCase implements Function<List<Inventory>, Flux<Inventory>> {

    private final InventoryGateway inventoryGateway;

    @Override
    public Flux<Inventory> apply(List<Inventory> inventory) {
        System.out.println("INFO: Request to save an Inventory: "+inventory);
        return inventoryGateway.saveInventoryList(inventory);
    }
}
