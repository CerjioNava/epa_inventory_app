package com.example.epa_inventory_app.infrastructure.drivenAdapters.inventory;

import com.example.epa_inventory_app.domain.model.inventory.Inventory;
import com.example.epa_inventory_app.domain.model.inventory.gateway.InventoryGateway;
import com.example.epa_inventory_app.infrastructure.drivenAdapters.inventory.data.InventoryData;
import com.example.epa_inventory_app.infrastructure.rabbitmq.handler.RabbitMqPublisher;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InventoryRepositoryAdapter implements InventoryGateway {

    private final InventoryRepository repository;
    private final ObjectMapper mapper;
    private final RabbitMqPublisher publisher;

    @Override
    public Flux<Inventory> getAllInventory(Pageable pageable) {
        return repository
                .findAllBy(pageable)
                .switchIfEmpty(Flux.empty())
                .map(inventoryData -> mapper.map(inventoryData, Inventory.class));
    }

    @Override
    public Mono<Inventory> getInventoryById(String id) {
        return repository
                .findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Unable to find inventory with id: " + id)))
                .map(inventoryData -> mapper.map(inventoryData, Inventory.class));
    }

    @Override
    public Mono<Inventory> saveInventory(Inventory inventory) {
        return repository
                .save(mapper.map(inventory, InventoryData.class))
                .map(inventoryData -> {
                    publisher.publishInventoryLogs(inventoryData, "CREATE");
                    return mapper.map(inventoryData, Inventory.class);
                })
                .onErrorResume(throwable -> {
                    publisher.publishInventoryError(mapper.map(inventory, InventoryData.class), "CREATE");
                    return Mono.error(throwable);
                });
    }

    @Override
    public Mono<Inventory> updateInventory(String id, Inventory inventory) {
        return repository
                .findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Unable to find and update inventory with id: " + id)))
                .flatMap(inventoryData -> {
                    inventory.setId(inventoryData.getId());
                    return repository.save(mapper.map(inventory, InventoryData.class));
                })
                .map(inventoryData -> {
                    publisher.publishInventoryLogs(inventoryData, "UPDATE");
                    return mapper.map(inventoryData, Inventory.class);
                })
                .onErrorResume(throwable -> {
                    publisher.publishInventoryError(mapper.map(inventory, InventoryData.class), "UPDATE");
                    return Mono.error(throwable);
                });
    }

    @Override
    public Mono<Void> deleteInventoryById(String id) {
        return repository
                .findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Unable to find and delete inventory with id: " + id)))
                .flatMap(inventoryData -> {
                    publisher.publishInventoryLogs(inventoryData, "DELETE");
                    return repository.deleteById(inventoryData.getId());
                })
                .onErrorResume(throwable -> {
                    InventoryData data = new InventoryData();
                    data.setId(id);
                    publisher.publishInventoryError(data, "DELETE");
                    return Mono.error(throwable);
                });
    }
}
