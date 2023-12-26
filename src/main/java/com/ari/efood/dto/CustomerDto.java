package com.ari.efood.dto;

import com.ari.efood.model.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    @Null(message = "ID should be auto-generated")
    private String id;

    @NotNull(message = "Email should not be NULL")
    @Email(message = "Enter a valid email")
    private String email;

    @NotNull(message = "First name should not be NULL")
    @Length(min = 3, message = "First name should be at least 3 letters long")
    private String firstName;

    @NotNull(message = "Last name should not be NULL")
    @Length(min = 3, message = "Last name should be at least 3 letters long")
    private String lastName;

    @NotNull(message = "Mobile number should not be NULL")
    @Pattern(regexp = "[1-9][0-9]{9}", message = "Mobile number should be 10 digits numeric")
    private String mobile;

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
