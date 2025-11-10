package be.skenteridis.contactmanager.service;

import be.skenteridis.contactmanager.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    private final ContactRepository repository;
    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }
}
