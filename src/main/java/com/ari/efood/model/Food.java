package com.ari.efood.model;

import com.ari.efood.dto.FoodDto;
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
public class Food {
    @Id
    private String id;
    @Field
    private com.ari.efood.enums.Food type;
    @Field
    private String title;
    @Field
    private String description;
    @Field
    private String restaurant;
    @Field
    private String pin;
    @Field
    private Integer price;

    public FoodDto toDto() {
        return FoodDto.builder()
                .id(this.id)
                .type(this.type)
                .title(this.title)
                .description(this.description)
                .restaurant(this.restaurant)
                .pin(this.pin)
                .price(this.price)
                .build();
    }
}
