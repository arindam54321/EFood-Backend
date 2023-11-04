package com.ari.efood.model;

import com.ari.efood.dto.CustomerDto;
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
public class Customer {
    @Id
    String id;
    @Field
    String email;
    @Field
    String firstName;
    @Field
    String lastName;
    @Field
    String mobile;

    public CustomerDto fromEntity() {
        return CustomerDto.builder()
                .id(this.id)
                .email(this.email)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .mobile(this.mobile)
                .build();
    }
}
