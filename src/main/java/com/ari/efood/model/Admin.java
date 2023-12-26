package com.ari.efood.model;

import com.ari.efood.dto.AdminDto;
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
public class Admin {
    @Id
    private String id;
    @Field
    private String username;
    @Field
    private String password;
    @Field
    private String mfa;

    public AdminDto toDto() {
        return AdminDto.builder()
                .id(this.id)
                .username(this.username)
                .password(null)
                .mfa(null)
                .build();
    }
}
