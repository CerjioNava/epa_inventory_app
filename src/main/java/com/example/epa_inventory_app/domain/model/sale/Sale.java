package com.example.epa_inventory_app.domain.model.sale;

import com.example.epa_inventory_app.domain.model.saleDetail.SaleDetail;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Sale {

    private String id;
    private String customerId;
    private Date date;
    private String SaleType;
    private BigDecimal totalAmount;
    private List<SaleDetail> saleDetails;

}
