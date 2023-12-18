package com.example.epa_inventory_app.domain.usecase.inventory;

import com.example.epa_inventory_app.domain.model.inventory.gateway.InventoryGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class DeleteInventoryUseCase  implements Function<String, Mono<Void>> {

    private final InventoryGateway inventoryGateway;

    @Override
    public Mono<Void> apply(String id) {
        System.out.println("INFO: Request to delete an Inventory by id: "+id);
        return inventoryGateway.deleteInventoryById(id);
    }
}
