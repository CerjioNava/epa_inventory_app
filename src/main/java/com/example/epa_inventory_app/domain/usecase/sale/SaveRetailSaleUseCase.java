package com.example.epa_inventory_app.domain.usecase.sale;

import com.example.epa_inventory_app.domain.model.sale.Sale;
import com.example.epa_inventory_app.domain.model.sale.gateway.SaleGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.function.Function;

@RequiredArgsConstructor
public class SaveRetailSaleUseCase implements Function<Sale, Mono<Sale>> {

    private final SaleGateway saleGateway;

    @Override
    public Mono<Sale> apply(Sale sale) {
        System.out.println("INFO: Request to save a Retail Sale: "+sale);
        sale.setSaleType("RETAIL");
        sale.setDate(new Date());
        return saleGateway.saveRetailSale(sale);
    }
}
