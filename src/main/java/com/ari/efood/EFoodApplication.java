package com.ari.efood;

import com.ari.efood.service.EmailService;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class EFoodApplication {

    @Autowired
    EmailService emailService;
    @Autowired
    ThreadPoolTaskExecutor executor;
    @Value(value = "${spring.mail.username}")
    private String email;

    public static void main(String[] args) {
        SpringApplication.run(EFoodApplication.class, args);
    }

    @PostConstruct
    public void sendEmailNotification() {
        String hostname = "unknown";
        String hostAddress = "unknown";

        long currentTimeMillis = System.currentTimeMillis();
        Instant instant = Instant.ofEpochMilli(currentTimeMillis);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        String date = localDateTime.format(dateFormatter);
        String time = localDateTime.format(timeFormatter);

        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            hostname = inetAddress.getHostName();
            hostAddress = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            log.error("Unable to fetch hostname");
        }

        String subject = "E-FOOD Application Started";
        String body = """
                Dear Admin,
                <br><br>
                E-FOOD application was started.
                <br>
                <strong>Hostname:</strong> <span style="color:%5$s">%1$s</span>
                <br>
                <strong>HostAddress:</strong> <span style="color:%5$s">%2$s</span>
                <br>
                <strong>Date:</strong> <span style="color:%5$s">%3$s</span>
                <br>
                <strong>Time:</strong> <span style="color:%5$s">%4$s</span>
                <br>
                <h6>Don't reply to this email</h6>
                """.formatted(hostname, hostAddress, date, time, "green");

        CompletableFuture.runAsync(() -> {
            try {
                emailService.sendEmail(subject, body, List.of(this.email), null, null);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }
}
