package uk.co.hermes.qr;

import net.ellise.QRRepository;
import net.ellise.qr.QRGenerator;
import net.ellise.qr.QRGeneratorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class QrGeneratorConfig {
    @Bean
    public QRGenerator getQrGenerator() {
        return new QRGeneratorImpl("http://localhost:8080", ".");
    }
}
