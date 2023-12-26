package com.ari.efood.model;

import com.ari.efood.dto.RestaurantDto;
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
public class Restaurant {
    @Id
    private String id;
    @Field
    private String name;
    @Field
    private String location;

    public RestaurantDto toDto() {
        return RestaurantDto.builder()
                .id(this.id)
                .name(this.name)
                .location(this.location)
                .build();
    }
}
