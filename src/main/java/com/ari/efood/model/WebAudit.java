package com.ari.efood.model;


import com.ari.efood.dto.WebAuditDto;
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
    private String referrer;
    @Field
    private String currentUrl;
    @Field
    private String visitorId;
    @Field
    private String timestamp;
    @Field
    private String timezone;
    @Field
    private String language;
    @Field
    private Integer screenWidth;
    @Field
    private Integer screenHeight;
    @Field
    private String page;
    @Field
    private String userAgent;

    public WebAuditDto toDto() {
        return WebAuditDto.builder()
                .id(this.id)
                .websiteId(this.websiteId)
                .eventType(this.eventType)
                .referrer(this.referrer)
                .currentUrl(this.currentUrl)
                .visitorId(this.visitorId)
                .timestamp(this.timestamp)
                .timezone(this.timezone)
                .language(this.language)
                .screenWidth(this.screenWidth)
                .screenHeight(this.screenHeight)
                .page(this.page)
                .userAgent(this.userAgent)
                .build();
    }
}
