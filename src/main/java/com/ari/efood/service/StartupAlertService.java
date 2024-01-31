package com.ari.efood.service;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class StartupAlertService {
    @Autowired
    EmailService emailService;
    @Autowired
    ThreadPoolTaskExecutor executor;
    @Value(value = "${spring.mail.username}")
    private String email;

    @PostConstruct
    public void sendEmailNotification() {
        String hostname = "unknown";
        String hostAddress = "unknown";

        long currentTimeMillis = System.currentTimeMillis();
        Instant instant = Instant.ofEpochMilli(currentTimeMillis);
        ZonedDateTime localDateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a (z)", Locale.ENGLISH);
        String date = localDateTime.format(dateFormatter);
        String time = localDateTime.format(timeFormatter);

        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            hostname = inetAddress.getHostName();
            hostAddress = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            log.error("Unable to fetch hostname");
        }

        String prefix = Objects.equals(hostAddress, "127.0.0.1") ? "LOCAL" : "CLOUD";
        String subject = "[%s] HungryHub Application Started".formatted(prefix);
        String body = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Application Start Notification</title>
                    <style>
                        body {
                            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                            background-color: #f8f8f8;
                            margin: 0;
                            padding: 0;
                        }
                        .container {
                            max-width: 600px;
                            margin: 20px auto;
                            background-color: #ffffff;
                            padding: 20px;
                            border-radius: 8px;
                            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                            text-align: center;
                        }
                        h1 {
                            color: #009688;
                        }
                        p {
                            color: #333333;
                            margin: 10px 0;
                        }
                        strong {
                            color: #009688;
                        }
                        h6 {
                            color: #666666;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>Dear Admin</h1>
                        <p>
                            <strong>Hungry Hub</strong> application has been started
                        </p>
                        <p>
                            <strong>Hostname:</strong> <span style="color: %5$s;">%1$s</span>
                        </p>
                        <p>
                            <strong>Host Address:</strong> <span style="color: %5$s;">%2$s</span>
                        </p>
                        <p>
                            <strong>Date:</strong> <span style="color: %5$s;">%3$s</span>
                        </p>
                        <p>
                            <strong>Time:</strong> <span style="color: %5$s;">%4$s</span>
                        </p>
                        <h6>Do not reply to this email</h6>
                    </div>
                </body>
                </html>
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
