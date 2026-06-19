package com.mitradayakreasi.backend.modules.contact;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact create(CreateContactRequest request) {
        Contact contact = new Contact();
        contact.setNama(request.getNama());
        contact.setEmail(request.getEmail());
        contact.setPesan(request.getPesan());
        return contactRepository.save(contact);
    }

    public List<Contact> getAll() {
        return contactRepository.findAll();
    }
}