package com.scaler.exambot.configurations;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {



    @Bean(name = "jasyptStringEncryptor")
    public StandardPBEStringEncryptor stringEncryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        String encryptionKey = System.getenv("JASYPT_KEY");
        encryptor.setPassword(encryptionKey);
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        return encryptor;
    }
}
