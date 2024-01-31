package com.ari.efood.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {
    @Min(value = 100, message = "Amount must be at least 100")
    private Long amount;
    @Email(message = "Please enter a valid email")
    private String customerEmail;
}
