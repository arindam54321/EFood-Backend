package com.ari.efood.dto;

import com.ari.efood.model.Qna;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QnaDto {
    String id;
    @NotNull
    String key;
    @NotNull
    String value;

    public Qna toEntity() {
        return Qna.builder()
                .id(this.id)
                .key(this.key)
                .value(this.value)
                .build();
    }
}
