package br.umc.ongcaridade.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initialize() throws IOException {
        String credJson = System.getenv("FIREBASE_CREDENTIALS");

        InputStream credStream = new ByteArrayInputStream(
                credJson.getBytes(StandardCharsets.UTF_8)
        );

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(credStream))
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }
}