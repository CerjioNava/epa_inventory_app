package com.example.epa_inventory_app.infrastructure.entryPoints.inventory;

import com.example.epa_inventory_app.domain.model.inventory.Inventory;
import com.example.epa_inventory_app.domain.usecase.inventory.*;
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
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class InventoryRouter {

    @Bean
    public RouterFunction<ServerResponse> getAllInventory(GetAllInventoryUseCase getAllInventoryUseCase) {
        return route(
                GET("/api/inventory"),
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
                                    getAllInventoryUseCase.apply(pageable), Inventory.class))
                            .onErrorResume(throwable -> ServerResponse
                                    .status(HttpStatus.NO_CONTENT)
                                    .build());
                }
        );
    }


    @Bean
    public RouterFunction<ServerResponse> getInventoryById(GetInventoryByIdUseCase getInventoryByIdUseCase) {
        return route(
                GET("/api/inventory/{id}"),
                request -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                getInventoryByIdUseCase.apply(request.pathVariable("id")), Inventory.class))
                        .onErrorResume(throwable -> ServerResponse
                                .status(HttpStatus.BAD_REQUEST)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(throwable.getMessage()))
//                request -> getInventoryByIdUseCase.apply(request.pathVariable("id"))
//                        .flatMap(inventory -> ServerResponse
//                                .ok()
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .bodyValue(inventory))
//                        .onErrorResume(throwable -> ServerResponse
//                                .status(HttpStatus.BAD_REQUEST)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .bodyValue(throwable.getMessage()))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> saveInventory(SaveInventoryUseCase saveInventoryUseCase) {
        return route(
                POST("/api/inventory").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Inventory.class)
                        .flatMap(inventory ->
                                saveInventoryUseCase.apply(inventory)
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
    public RouterFunction<ServerResponse> saveInventoryBatch(SaveInventoryUseCase saveInventoryUseCase) {
        return route(
                POST("/api/inventory/list").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToFlux(Inventory.class)
                        .flatMap(inventory  ->
                                saveInventoryUseCase.apply(inventory)
                                        .flatMap(result -> ServerResponse
                                                .status(HttpStatus.CREATED)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(result))
                                        .onErrorResume(throwable -> ServerResponse
                                                .status(HttpStatus.BAD_REQUEST)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(throwable.getMessage())))
                        .collectList()
                        .flatMap(result -> ServerResponse.ok().bodyValue(result))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> updateInventory(UpdateInventoryUseCase updateInventoryUseCase) {
        return route(
                PUT("/api/inventory/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Inventory.class)
                        .flatMap(inventory ->
                                updateInventoryUseCase.apply(request.pathVariable("id"), inventory)
                                        .flatMap(result -> ServerResponse
                                                .status(HttpStatus.OK)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(result))
                                        .onErrorResume(throwable -> ServerResponse
                                                .status(HttpStatus.BAD_REQUEST)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(throwable.getMessage())))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> deleteInventory(DeleteInventoryUseCase deleteInventoryUseCase) {
        return route(
                DELETE("/api/inventory/{id}"),
                request -> deleteInventoryUseCase.apply(request.pathVariable("id"))
                        .thenReturn(ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue("Deleted inventory with id: " + request.pathVariable("id")))
                        .flatMap(responseMono -> responseMono)
                        .onErrorResume(throwable -> ServerResponse.badRequest()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(throwable.getMessage()))
        );
    }
}
