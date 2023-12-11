package com.ari.efood.dto;

import com.ari.efood.model.Admin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    @Null(message = "ID should be auto-generated")
    String id;

    @NotNull(message = "Username should not be NULL")
    String username;

    @NotNull(message = "Password should not be NULL")
    String password;

    @NotNull(message = "MFA should not be NULL")
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
