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
public class WebAudit {
    @Id
    private String id;
    @Field
    private String websiteId;
    @Field
    private String eventType;
    @Field
    private String visitorId;
    @Field
    private String timestamp;
    @Field
    private String timezone;
}
