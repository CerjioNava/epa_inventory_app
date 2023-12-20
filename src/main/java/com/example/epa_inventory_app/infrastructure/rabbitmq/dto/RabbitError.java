package com.example.epa_inventory_app.infrastructure.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RabbitError {

    private String error;
    private Date date;
    private Object object;

}
