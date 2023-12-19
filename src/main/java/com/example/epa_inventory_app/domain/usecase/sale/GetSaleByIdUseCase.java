package com.example.epa_inventory_app.domain.usecase.sale;

import com.example.epa_inventory_app.domain.model.sale.Sale;
import com.example.epa_inventory_app.domain.model.sale.gateway.SaleGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetSaleByIdUseCase implements Function<String, Mono<Sale>> {

    private final SaleGateway saleGateway;

    @Override
    public Mono<Sale> apply(String id) {
        System.out.println("INFO: Request to get an Sale by id: "+id);
        return saleGateway.getSaleById(id);
    }
}
