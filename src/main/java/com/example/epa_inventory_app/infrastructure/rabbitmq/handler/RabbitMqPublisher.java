package com.example.epa_inventory_app.infrastructure.rabbitmq.handler;

import com.example.epa_inventory_app.domain.model.inventory.Inventory;
import com.example.epa_inventory_app.domain.model.sale.Sale;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.Sender;

@Component
public class RabbitMqPublisher {

    @Autowired
    private Sender sender;

    @Autowired
    private Gson gson;

    public RabbitMqPublisher() {
    }

    public void publishInventoryError(Inventory inventory){
        sender
                .send(Mono.just(new OutboundMessage(
                        System.getenv("EXCHANGE_INVENTORY"),
                        System.getenv("ROUTING_KEY_INVENTORY_ERROR"),
                        gson.toJson(inventory).getBytes())
                )).subscribe();
    }

    public void publishInventoryLogs(Inventory inventory){
        System.out.println("INFO: Publishing Inventory Log: "+inventory);
        sender
                .send(Mono.just(new OutboundMessage(
                        System.getenv("EXCHANGE_INVENTORY"),
                        System.getenv("ROUTING_KEY_INVENTORY_LOGS"),
                        gson.toJson(inventory).getBytes())
                )).subscribe();
    }

    public void publishSaleError(Sale sale){
        sender
                .send(Mono.just(new OutboundMessage(
                        System.getenv("EXCHANGE_SALE"),
                        System.getenv("ROUTING_KEY_SALE_ERROR"),
                        gson.toJson(sale).getBytes())
                )).subscribe();
    }

    public void publishSaleLogs(Sale sale){
        sender
                .send(Mono.just(new OutboundMessage(
                        System.getenv("EXCHANGE_SALE"),
                        System.getenv("ROUTING_KEY_SALE_LOGS"),
                        gson.toJson(sale).getBytes())
                )).subscribe();
    }
}
