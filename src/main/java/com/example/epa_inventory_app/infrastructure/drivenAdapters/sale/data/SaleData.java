package com.example.epa_inventory_app.infrastructure.drivenAdapters.sale.data;

import com.example.epa_inventory_app.infrastructure.drivenAdapters.saleDetail.data.SaleDetailData;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "Sale")
public class SaleData {

    private String id;
    private List<SaleDetailData> saleDetails;
    private BigDecimal totalAmount;
    private String customerId;
    private Boolean isRetail;

}
