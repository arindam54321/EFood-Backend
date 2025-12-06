package com.ari.efood.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmailServiceBrevo {

    private final String apiKey;
    private final String fromEmail;
    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public EmailServiceBrevo(
            @Value("${brevo.api-key}") String apiKey,
            @Value("${brevo.from-email}") String fromEmail
    ) {
        this.apiKey = apiKey;
        this.fromEmail = fromEmail;
        this.httpClient = HttpClients.createDefault();
        this.objectMapper = new ObjectMapper();
    }

    public void sendEmail(
            String subject, String htmlContent, List<String> tos,
            List<String> ccs, List<String> bccs
    ) throws MessagingException, IOException {
        HttpPost post = new HttpPost("https://api.brevo.com/v3/smtp/email");
        post.setHeader("accept", "application/json");
        post.setHeader("content-type", "application/json");
        post.setHeader("api-key", apiKey);

        // Build JSON body
        var body = new java.util.HashMap<String, Object>();
        body.put("sender", Map.of("email", fromEmail));
        body.put("to", tos.stream().map(email -> Map.of("email", email)).toList());
        if (ccs != null && !ccs.isEmpty())
            body.put("cc", ccs.stream().map(email -> Map.of("email", email)).toList());
        if (bccs != null && !bccs.isEmpty())
            body.put("bcc", bccs.stream().map(email -> Map.of("email", email)).toList());

        body.put("subject", subject);
        body.put("htmlContent", htmlContent);

        String json = objectMapper.writeValueAsString(body);
        post.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));

        var resp = httpClient.execute(post);
        int status = resp.getCode();
        if (status < 200 || status >= 300) {
            String error = "Failed to send email via Brevo. Status: " + status;
            log.error(error);
            throw new MessagingException(error);
        } else {
            log.error("Email sent; to: {}, cc: {}, bcc: {}", tos, ccs, bccs);
        }
    }
}
