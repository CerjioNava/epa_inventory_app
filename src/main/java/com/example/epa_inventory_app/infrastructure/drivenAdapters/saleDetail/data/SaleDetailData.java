package com.example.epa_inventory_app.infrastructure.drivenAdapters.saleDetail.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Document(collection = "SaleDetail")
public class SaleDetailData {

    private String id;
    private String articleId;
    private Integer quantity;
    private BigDecimal amount;

}
