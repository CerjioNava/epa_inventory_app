package com.example.epa_inventory_app.domain.usecase.sale;

import com.example.epa_inventory_app.domain.model.sale.Sale;
import com.example.epa_inventory_app.domain.model.sale.gateway.SaleGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class SaveWholeSaleUseCase implements Function<Sale, Mono<Sale>> {

    private final SaleGateway saleGateway;

    @Override
    public Mono<Sale> apply(Sale sale) {
        System.out.println("INFO: Request to save a WholeSale: "+sale);
        return saleGateway.saveWholeSale(sale);
    }
}
