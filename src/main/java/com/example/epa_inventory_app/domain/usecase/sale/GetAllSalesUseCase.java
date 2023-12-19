package com.example.epa_inventory_app.domain.usecase.sale;

import com.example.epa_inventory_app.domain.model.sale.Sale;
import com.example.epa_inventory_app.domain.model.sale.gateway.SaleGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetAllSalesUseCase implements Function<Pageable, Flux<Sale>> {

    private final SaleGateway saleGateway;

    @Override
    public Flux<Sale> apply(Pageable pageable) {
        System.out.println("INFO: Request to get all sales by pagination " +
                "(Page: "+pageable.getPageNumber()+", Size: "+pageable.getPageSize()+")");
        return saleGateway.getAllSales(pageable);
    }
}
