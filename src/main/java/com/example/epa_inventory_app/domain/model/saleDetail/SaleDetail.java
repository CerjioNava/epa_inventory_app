package com.example.epa_inventory_app.domain.model.saleDetail;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaleDetail {

    private String articleId;
    private Integer quantity;
    private BigDecimal amount;

}
