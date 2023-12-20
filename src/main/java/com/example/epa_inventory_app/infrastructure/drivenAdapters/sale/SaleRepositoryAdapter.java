package com.example.epa_inventory_app.infrastructure.drivenAdapters.sale;

import com.example.epa_inventory_app.domain.model.inventory.Inventory;
import com.example.epa_inventory_app.domain.model.sale.Sale;
import com.example.epa_inventory_app.domain.model.sale.gateway.SaleGateway;
import com.example.epa_inventory_app.infrastructure.drivenAdapters.inventory.data.InventoryData;
import com.example.epa_inventory_app.infrastructure.drivenAdapters.sale.data.SaleData;
import com.example.epa_inventory_app.infrastructure.rabbitmq.handler.RabbitMqPublisher;
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
    private final ObjectMapper mapper;
    private final RabbitMqPublisher publisher;

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
                .map(saleData -> mapper.map(saleData, Sale.class))
                .onErrorResume(throwable -> {
                    Sale sale = new Sale();
                    sale.setId(id);
                    publisher.publishSaleError(mapper.map(sale, SaleData.class), "GET");
                    return Mono.error(throwable);
                });
    }

    @Override
    public Mono<Sale> saveRetailSale(Sale sale) {
        return repository
                .save(mapper.map(sale, SaleData.class))
                .map(saleData -> {
                    publisher.publishSaleLogs(saleData, "CREATE retail");
                    return mapper.map(saleData, Sale.class);
                })
                .onErrorResume(throwable -> {
                    publisher.publishSaleError(mapper.map(sale, SaleData.class), "CREATE retail");
                    return Mono.error(throwable);
                });
    }

    @Override
    public Mono<Sale> saveWholeSale(Sale sale) {
        return repository
                .save(mapper.map(sale, SaleData.class))
                .map(saleData -> {
                    publisher.publishSaleLogs(saleData, "CREATE whole");
                    return mapper.map(saleData, Sale.class);
                })
                .onErrorResume(throwable -> {
                    publisher.publishSaleError(mapper.map(sale, SaleData.class), "CREATE whole");
                    return Mono.error(throwable);
                });
    }
}
