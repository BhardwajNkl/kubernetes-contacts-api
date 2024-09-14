package dev.bhardwaj.contacts_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.bhardwaj.contacts_backend.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
