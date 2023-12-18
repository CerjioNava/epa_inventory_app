package com.example.epa_inventory_app.domain.model.inventory;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Inventory {

    private String id;
    private String articleId;
    private Integer existence;
    private Integer majorExistence;
    private BigDecimal price;
    private BigDecimal majorPrice;

}
