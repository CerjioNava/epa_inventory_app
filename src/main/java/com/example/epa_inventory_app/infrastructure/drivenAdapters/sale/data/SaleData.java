package com.example.epa_inventory_app.infrastructure.drivenAdapters.sale.data;

import com.example.epa_inventory_app.infrastructure.drivenAdapters.saleDetail.data.SaleDetailData;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "Sale")
public class SaleData {

    @Id
    private String id;
    private String customerId;
    private Date date;
    private String SaleType;
    private BigDecimal totalAmount;
    private List<SaleDetailData> saleDetails;

}
