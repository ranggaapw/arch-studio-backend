package com.mitradayakreasi.backend.modules.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendEmailNotification(Message message) {
        logger.info("=============================================");
        logger.info("MENGIRIM EMAIL NOTIFIKASI (MOCK)...");
        logger.info("Dari   : {} ({})", message.getName(), message.getEmail());
        logger.info("Subjek : {}", message.getSubject());
        logger.info("Isi    : {}", message.getContent());
        logger.info("=============================================");
    }
}
