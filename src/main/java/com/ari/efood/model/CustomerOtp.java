package com.ari.efood.model;

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
public class CustomerOtp {
    @Id
    private String id;
    @Field
    private String email;
    @Field
    private Integer otp;
    @Field
    private Long validTill;
}
