package com.ari.efood.dto;

import com.ari.efood.model.Location;
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
public class LocationDto {
    @NotNull(message = "PIN should be present")
    @Pattern(regexp = "[1-9][0-9]{5}", message = "PIN should be 6 digit numeric")
    private String pin;

    @NotNull(message = "Name should not be NULL")
    private String name;

    @NotNull(message = "State name should not be NULL")
    private String state;

    public Location toEntity() {
        return Location.builder()
                .pin(this.pin)
                .name(this.name)
                .state(this.state)
                .build();
    }
}
