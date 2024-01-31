package com.ari.efood.dto;

import com.ari.efood.enums.OrderStatus;
import com.ari.efood.model.Order;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    @Null(message = "ID should be auto-generated")
    private String id;

    @NotNull(message = "Email should not be NULL")
    @Email(message = "Enter a valid email")
    private String email;

    @NotNull(message = "Restaurant Name should not be NULL")
    private String restaurantName;

    @NotNull(message = "Data should not be NULL")
    private String data;

    @NotNull(message = "Total Payment should not be NULL")
    private Integer totalPayment;

    @NotNull(message = "Status should not be NULL")
    private OrderStatus status;

    @Null(message = "TimeStamp should be auto-picked")
    private Long epochTime;

    public Order toEntity() {
        return Order.builder()
                .id(this.id)
                .email(this.email)
                .restaurantName(this.restaurantName)
                .data(this.data)
                .totalPayment(this.totalPayment)
                .status(this.status)
                .epochTime(this.epochTime)
                .build();
    }
}
