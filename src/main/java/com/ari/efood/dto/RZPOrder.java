package com.ari.efood.dto;

import com.razorpay.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RZPOrder implements Serializable {
    String id;
    Integer amount;
    String currency;
    Integer amount_paid;
    Integer amount_due;
    String status;
    String receipt;
    Date created_at;
    Integer attempts;
    String entity;

    public static RZPOrder serialize(Order order) {
        return RZPOrder.builder()
                .id(order.get("id"))
                .amount(order.get("amount"))
                .currency(order.get("currency"))
                .amount_paid(order.get("amount_paid"))
                .amount_due(order.get("amount_due"))
                .status(order.get("status"))
                .receipt(order.get("receipt"))
                .created_at(order.get("created_at"))
                .attempts(order.get("attempts"))
                .entity(order.get("entity"))
                .build();
    }
}
