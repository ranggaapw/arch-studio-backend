package com.mitradayakreasi.backend.modules.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    private final MessageRepository messageRepository;
    private final EmailService emailService;

    public MessageService(MessageRepository messageRepository, EmailService emailService) {
        this.messageRepository = messageRepository;
        this.emailService = emailService;
    }

    @Transactional
    public Message createMessage(Message message) {
        Message savedMessage = messageRepository.save(message);
        try {
            emailService.sendEmailNotification(savedMessage);
        } catch (Exception e) {
            logger.error("Gagal mengirim email notifikasi: {}", e.getMessage(), e);
        }
        return savedMessage;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
