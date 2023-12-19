package com.example.epa_inventory_app.infrastructure.entryPoints.sale;

import com.example.epa_inventory_app.domain.model.sale.Sale;
import com.example.epa_inventory_app.domain.usecase.sale.GetAllSalesUseCase;
import com.example.epa_inventory_app.domain.usecase.sale.GetSaleByIdUseCase;
import com.example.epa_inventory_app.domain.usecase.sale.SaveRetailSaleUseCase;
import com.example.epa_inventory_app.domain.usecase.sale.SaveWholeSaleUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Optional;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class SaleRouter {

    @Bean
    public RouterFunction<ServerResponse> getAllSale(GetAllSalesUseCase getAllSalesUseCase) {
        return route(
                GET("/api/sale"),
                request -> {
                    Optional<String> page = request.queryParam("page");
                    Optional<String> size = request.queryParam("size");
                    Pageable pageable = PageRequest.of(
                            page.map(Integer::parseInt).orElse(0),
                            size.map(Integer::parseInt).orElse(20)
                    );
                    return ServerResponse
                            .ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromPublisher(
                                    getAllSalesUseCase.apply(pageable), Sale.class))
                            .onErrorResume(throwable -> ServerResponse
                                    .status(HttpStatus.NO_CONTENT)
                                    .build());
                }
        );
    }


    @Bean
    public RouterFunction<ServerResponse> getSaleById(GetSaleByIdUseCase getSaleByIdUseCase) {
        return route(
                GET("/api/sale/{id}"),
                request -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getSaleByIdUseCase.apply(request.pathVariable("id")), Sale.class))
                        .onErrorResume(throwable -> ServerResponse
                                .status(HttpStatus.BAD_REQUEST)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(throwable.getMessage()))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> saveRetailSale(SaveRetailSaleUseCase saveRetailSaleUseCase) {
        return route(
                POST("/api/sale").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Sale.class)
                        .flatMap(sale ->
                                saveRetailSaleUseCase.apply(sale)
                                        .flatMap(result -> ServerResponse
                                                .status(HttpStatus.CREATED)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(result))
                                        .onErrorResume(throwable -> ServerResponse
                                                .status(HttpStatus.BAD_REQUEST)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(throwable.getMessage())))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> saveWholeSale(SaveWholeSaleUseCase saveWholeSaleUseCase) {
        return route(
                POST("/api/sale").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Sale.class)
                        .flatMap(sale ->
                                saveWholeSaleUseCase.apply(sale)
                                        .flatMap(result -> ServerResponse
                                                .status(HttpStatus.CREATED)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(result))
                                        .onErrorResume(throwable -> ServerResponse
                                                .status(HttpStatus.BAD_REQUEST)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(throwable.getMessage())))
        );
    }
}
