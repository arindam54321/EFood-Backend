package com.ari.efood.dto;

import com.ari.efood.model.Admin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    String id;
    String username;
    String password;
    String mfa;

    public Admin toEntity() {
        return Admin.builder()
                .id(this.id)
                .username(this.username)
                .password(this.password)
                .mfa(this.mfa)
                .build();
    }
}
