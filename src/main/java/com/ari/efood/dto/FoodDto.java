package com.ari.efood.dto;

import com.ari.efood.model.Food;
import jakarta.validation.constraints.Min;
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
public class FoodDto {
    @Null(message = "ID should be auto-generated")
    private String id;

    private com.ari.efood.enums.Food type;

    @NotNull(message = "Food title should not be null")
    private String title;

    @NotNull(message = "Food description should not be null")
    private String description;

    @NotNull(message = "Restaurant code should not be null")
    private String restaurant;

    private String pin;

    @Min(value = 1, message = "Price must be at least 1")
    private Integer price;

    public Food toEntity() {
        return Food.builder()
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
