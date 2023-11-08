package com.ari.efood.model;

import com.ari.efood.dto.LocationDto;
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
public class Location {
    @Id
    String pin;
    @Field
    String name;
    @Field
    String state;

    public LocationDto toDto() {
        return LocationDto.builder()
                .pin(this.pin)
                .name(this.name)
                .state(this.state)
                .build();
    }
}
