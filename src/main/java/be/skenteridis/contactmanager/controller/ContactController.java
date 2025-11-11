package be.skenteridis.contactmanager.controller;

import be.skenteridis.contactmanager.model.Contact;
import be.skenteridis.contactmanager.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    private final ContactService service;
    public ContactController(ContactService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getContacts() {
        List<Contact> contacts = service.getContacts();
        return contacts.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(contacts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContactById(@PathVariable Long id) {
        Contact contact = service.getContactById(id);
        return contact == null ? ResponseEntity.status(404).body("Contact not found") : ResponseEntity.ok(contact);
    }

    @PostMapping
    public ResponseEntity<?> addContact(@Valid @RequestBody Contact contact) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addContact(contact));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(@PathVariable Long id, @Valid @RequestBody Contact contact) {
        Contact result = service.updateContact(id, contact);
        return result == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found!")
                : ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable Long id) {
        boolean isDeleted = service.deleteContact(id);
        return isDeleted ? ResponseEntity.ok("Deleted contact successfully!")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found!");
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchContact(@RequestParam String name) {
        List<Contact> contacts = service.getContactsByName(name);
        return contacts.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(contacts);
    }
}
