package com.example.epa_inventory_app.domain.usecase.inventory;

import com.example.epa_inventory_app.domain.model.inventory.Inventory;
import com.example.epa_inventory_app.domain.model.inventory.gateway.InventoryGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import org.springframework.data.domain.Pageable;
import java.util.function.Function;

@RequiredArgsConstructor
public class GetAllInventoryUseCase implements Function<Pageable, Flux<Inventory>> {

    private final InventoryGateway inventoryGateway;

    @Override
    public Flux<Inventory> apply(Pageable pageable) {
        System.out.println("INFO: Request to get all inventory by pagination " +
                "(Page: "+pageable.getPageNumber()+", Size: "+pageable.getPageSize()+")");
        return inventoryGateway.getAllInventory(pageable);
    }
}
