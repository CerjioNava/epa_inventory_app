package com.example.epa_inventory_app.infrastructure.rabbitmq.handler;

import com.example.epa_inventory_app.domain.model.inventory.Inventory;
import com.example.epa_inventory_app.domain.model.sale.Sale;
import com.example.epa_inventory_app.infrastructure.drivenAdapters.inventory.data.InventoryData;
import com.example.epa_inventory_app.infrastructure.drivenAdapters.sale.data.SaleData;
import com.example.epa_inventory_app.infrastructure.rabbitmq.dto.RabbitError;
import com.example.epa_inventory_app.infrastructure.rabbitmq.dto.RabbitLog;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.Sender;

import java.util.Date;

@Component
public class RabbitMqPublisher {

    @Autowired
    private Sender sender;

    @Autowired
    private Gson gson;

    public RabbitMqPublisher() {
    }

    // INVENTORY ERROR PUBLISHER
    public void publishInventoryError(InventoryData inventoryData, String action) {
        System.out.println("ERROR: Publishing Inventory Error: "+inventoryData);

        RabbitError log = new RabbitError(
                "ERROR: "+action+" inventory",
                new Date(),
                inventoryData
        );

        sender
                .send(Mono.just(new OutboundMessage(
                        System.getenv("EXCHANGE_INVENTORY"),
                        System.getenv("ROUTING_KEY_INVENTORY_ERROR"),
                        gson.toJson(log).getBytes())
                )).subscribe();
    }

    // INVENTORY LOG PUBLISHER
    public void publishInventoryLogs(InventoryData inventoryData, String action) {
        System.out.println("INFO: Publishing Inventory Log: "+inventoryData);

        RabbitLog log = new RabbitLog(
                "INFO: "+action+" inventory",
                new Date(),
                inventoryData
        );

        sender
                .send(Mono.just(new OutboundMessage(
                        System.getenv("EXCHANGE_INVENTORY"),
                        System.getenv("ROUTING_KEY_INVENTORY_LOGS"),
                        gson.toJson(log).getBytes())
                )).subscribe();
    }

    // SALE ERROR PUBLISHER
    public void publishSaleError(SaleData saleData, String action) {
        System.out.println("ERROR: Publishing Sale Error: "+saleData);

        RabbitError log = new RabbitError(
                "ERROR: "+action+" sale",
                new Date(),
                saleData
        );

        sender
                .send(Mono.just(new OutboundMessage(
                        System.getenv("EXCHANGE_SALE"),
                        System.getenv("ROUTING_KEY_SALE_ERROR"),
                        gson.toJson(log).getBytes())
                )).subscribe();
    }

    // SALE LOG PUBLISHER
    public void publishSaleLogs(SaleData saleData, String action) {
        System.out.println("INFO: Publishing Sale Log: "+saleData);

        RabbitLog log = new RabbitLog(
                "INFO: "+action+" sale",
                new Date(),
                saleData
        );

        sender
                .send(Mono.just(new OutboundMessage(
                        System.getenv("EXCHANGE_SALE"),
                        System.getenv("ROUTING_KEY_SALE_LOGS"),
                        gson.toJson(log).getBytes())
                )).subscribe();
    }
}
