package com.ari.efood.dto;

import com.ari.efood.model.Restaurant;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {
    @NotNull(message = "ID must not be null")
    @Pattern(regexp = "[0-9A-Z]{1,10}", message = "ID must contain only capital latin letters or numbers, and length must be less than or equal 10")
    private String id;

    @NotNull(message = "Name must not be null")
    private String name;

    @NotNull(message = "Location PIN must not be null")
    @Pattern(regexp = "[1-9][0-9]{5}", message = "Location PIN should be 6 digit numeric")
    private String location;

    public Restaurant toEntity() {
        return Restaurant.builder()
                .id(this.id)
                .name(this.name)
                .location(this.location)
                .build();
    }
}
