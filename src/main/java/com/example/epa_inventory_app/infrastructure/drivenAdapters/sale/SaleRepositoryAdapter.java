package com.example.epa_inventory_app.infrastructure.drivenAdapters.sale;

import com.example.epa_inventory_app.domain.model.inventory.Inventory;
import com.example.epa_inventory_app.domain.model.sale.Sale;
import com.example.epa_inventory_app.domain.model.sale.gateway.SaleGateway;
import com.example.epa_inventory_app.infrastructure.drivenAdapters.inventory.data.InventoryData;
import com.example.epa_inventory_app.infrastructure.drivenAdapters.sale.data.SaleData;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class SaleRepositoryAdapter implements SaleGateway {

    private final SaleRepository repository;
    private ObjectMapper mapper;

    @Override
    public Flux<Sale> getAllSales(Pageable pageable) {
        return repository
                .findAllBy(pageable)
                .switchIfEmpty(Flux.empty())
                .map(saleData -> mapper.map(saleData, Sale.class));
    }

    @Override
    public Mono<Sale> getSaleById(String id) {
        return repository
                .findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Unable to find inventory with id: " + id)))
                .map(saleData -> mapper.map(saleData, Sale.class));
    }

    @Override
    public Mono<Sale> saveRetailSale(Sale sale) {
        return repository
                .save(mapper.map(sale, SaleData.class))
                .map(saleData -> mapper.map(saleData, Sale.class));
    }

    @Override
    public Mono<Sale> saveWholeSale(Sale sale) {
        return repository
                .save(mapper.map(sale, SaleData.class))
                .map(saleData -> mapper.map(saleData, Sale.class));
    }
}
