package com.ari.efood.dto;

import com.ari.efood.model.WebAudit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebAuditDto {
    private String id;
    private String websiteId;
    private String eventType;
    private String referrer;
    private String currentUrl;
    private String visitorId;
    private String timestamp;
    private String timezone;
    private String language;
    private Integer screenWidth;
    private Integer screenHeight;
    private String page;
    private String userAgent;

    public WebAudit toEntity() {
        return WebAudit.builder()
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
