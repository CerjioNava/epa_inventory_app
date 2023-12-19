package com.example.epa_inventory_app.infrastructure.drivenAdapters.sale;

import com.example.epa_inventory_app.infrastructure.drivenAdapters.sale.data.SaleData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface SaleRepository extends ReactiveMongoRepository<SaleData, String> {

    Flux<SaleData> findAllBy(Pageable pageable);

}
