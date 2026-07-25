package com.ari.efood.model;

import com.ari.efood.dto.QnaDto;
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
public class Qna {
    @Id
    String id;
    @Field
    String key;
    @Field
    String value;

    public QnaDto toDto() {
        return QnaDto.builder()
                .id(this.id)
                .key(this.key)
                .value(this.value)
                .build();
    }
}
