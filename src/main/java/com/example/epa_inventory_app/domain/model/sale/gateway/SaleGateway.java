package com.example.epa_inventory_app.domain.model.sale.gateway;

import com.example.epa_inventory_app.domain.model.sale.Sale;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SaleGateway {

    Flux<Sale> getAllSales(Pageable pageable);
    Mono<Sale> getSaleById(String id);
    Mono<Sale> saveRetailSale(Sale sale);
    Mono<Sale> saveWholeSale(Sale sale);

}
