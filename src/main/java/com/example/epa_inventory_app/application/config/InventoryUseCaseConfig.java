package com.example.epa_inventory_app.application.config;

import com.example.epa_inventory_app.domain.model.inventory.gateway.InventoryGateway;
import com.example.epa_inventory_app.domain.usecase.inventory.*;
import com.example.epa_inventory_app.domain.usecase.inventory.GetAllInventoryUseCase;
import com.example.epa_inventory_app.domain.usecase.inventory.GetInventoryByIdUseCase;
import com.example.epa_inventory_app.domain.usecase.inventory.SaveInventoryUseCase;
import com.example.epa_inventory_app.domain.usecase.inventory.UpdateInventoryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryUseCaseConfig {

    @Bean
    public GetAllInventoryUseCase getAllInventorysUseCase(InventoryGateway inventoryGateway) {
        return new GetAllInventoryUseCase(inventoryGateway);
    }

    @Bean
    public GetInventoryByIdUseCase getInventoryByIdUseCase(InventoryGateway inventoryGateway) {
        return new GetInventoryByIdUseCase(inventoryGateway);
    }

    @Bean
    public SaveInventoryUseCase saveInventoryUseCase(InventoryGateway inventoryGateway) {
        return new SaveInventoryUseCase(inventoryGateway);
    }

    @Bean
    public UpdateInventoryUseCase updateInventoryUseCase(InventoryGateway inventoryGateway) {
        return new UpdateInventoryUseCase(inventoryGateway);
    }

    @Bean
    public DeleteInventoryUseCase deleteInventoryUseCase(InventoryGateway inventoryGateway) {
        return new DeleteInventoryUseCase(inventoryGateway);
    }
}
