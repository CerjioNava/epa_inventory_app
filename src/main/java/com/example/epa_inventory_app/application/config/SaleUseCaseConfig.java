package com.example.epa_inventory_app.application.config;

import com.example.epa_inventory_app.domain.model.sale.gateway.SaleGateway;
import com.example.epa_inventory_app.domain.usecase.sale.GetAllSalesUseCase;
import com.example.epa_inventory_app.domain.usecase.sale.GetSaleByIdUseCase;
import com.example.epa_inventory_app.domain.usecase.sale.SaveRetailSaleUseCase;
import com.example.epa_inventory_app.domain.usecase.sale.SaveWholeSaleUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaleUseCaseConfig {

    @Bean
    public GetAllSalesUseCase getAllSalesUseCase(SaleGateway saleGateway) {
        return new GetAllSalesUseCase(saleGateway);
    }

    @Bean
    public GetSaleByIdUseCase getSaleByIdUseCase(SaleGateway saleGateway) {
        return new GetSaleByIdUseCase(saleGateway);
    }

    @Bean
    public SaveRetailSaleUseCase saveRetailSaleUseCase(SaleGateway saleGateway) {
        return new SaveRetailSaleUseCase(saleGateway);
    }

    @Bean
    public SaveWholeSaleUseCase saveWholeSaleUseCase(SaleGateway saleGateway) {
        return new SaveWholeSaleUseCase(saleGateway);
    }

}
