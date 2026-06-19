package com.mitradayakreasi.backend.modules.contact;

import com.mitradayakreasi.backend.model.WebResponse;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "http://localhost:5173")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public WebResponse<Contact> create(@RequestBody CreateContactRequest request) {
        Contact contact = contactService.create(request);
        return new WebResponse<>(200, "OK", contact);
    }

    @GetMapping
    public WebResponse<List<Contact>> getAll() {
        List<Contact> contacts = contactService.getAll();
        return new WebResponse<>(200, "OK", contacts);
    }
}