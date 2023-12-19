package com.example.epa_inventory_app.domain.model.sale;

import com.example.epa_inventory_app.domain.model.saleDetail.SaleDetail;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Sale {

    private String id;
    private List<SaleDetail> saleDetails;
    private BigDecimal totalAmount;
    private String customerId;
    private Boolean isRetail;
    // private String PaymentType;

}
