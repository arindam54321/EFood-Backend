package com.ari.efood.model;


import com.ari.efood.dto.OrderDto;
import com.ari.efood.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Order {
    @Id
    private String id;
    @Field
    private String email;
    @Field
    private String restaurantName;
    @Field
    private String data;
    @Field
    private Integer totalPayment;
    @Field
    private OrderStatus status;
    @Field
    private Long epochTime;

    public OrderDto toDto() {
        return OrderDto.builder()
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
