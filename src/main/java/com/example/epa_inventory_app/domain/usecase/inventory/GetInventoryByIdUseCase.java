package com.example.epa_inventory_app.domain.usecase.inventory;

import com.example.epa_inventory_app.domain.model.inventory.Inventory;
import com.example.epa_inventory_app.domain.model.inventory.gateway.InventoryGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetInventoryByIdUseCase implements Function<String, Mono<Inventory>> {

    private final InventoryGateway inventoryGateway;

    @Override
    public Mono<Inventory> apply(String id) {
        System.out.println("INFO: Request to get an Inventory by id: "+id);
        return inventoryGateway.getInventoryById(id);
    }
}
