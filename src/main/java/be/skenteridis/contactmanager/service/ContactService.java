package be.skenteridis.contactmanager.service;

import be.skenteridis.contactmanager.model.Contact;
import be.skenteridis.contactmanager.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    private final ContactRepository repository;
    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    public List<Contact> getContacts() {
        return repository.findAll();
    }

    public Contact getContactById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Contact addContact(Contact contact) {
        return repository.save(contact);
    }

    public List<Contact> getContactsByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public Contact updateContact(Long id, Contact contact) {
        Contact result = repository.findById(id).map(c -> {
            c.setName(contact.getName());
            c.setEmail(contact.getEmail());
            c.setPhone(contact.getPhone());
            return c;
        }).orElse(null);
        return result == null ? null : repository.save(result);
    }

    public boolean deleteContact(Long id) {
        Contact contact = repository.findById(id).orElse(null);
        if(contact == null) return false;
        repository.delete(contact);
        return true;
    }
}
