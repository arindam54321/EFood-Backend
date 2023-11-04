package com.ari.efood.dto;

import com.ari.efood.model.Customer;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    String id;
    @Email(message = "Enter a valid email")
    String email;
    String firstName;
    String lastName;
    String mobile;

    public Customer toEntity() {
        return Customer.builder()
                .id(this.id)
                .email(this.email)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .mobile(this.mobile)
                .build();
    }
}
