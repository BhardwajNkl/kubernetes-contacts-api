package dev.bhardwaj.contacts_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.bhardwaj.contacts_backend.dto.NewContactDto;
import dev.bhardwaj.contacts_backend.dto.UpdateContactDto;
import dev.bhardwaj.contacts_backend.model.Contact;
import dev.bhardwaj.contacts_backend.service.ContactService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin("*")
@Tag(name = "Contacts", description = "CRUD APIs for contact management")
@Slf4j
public class ContactController {
	private final ContactService contactService;
	
	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}
	
	@PostMapping()
	public ResponseEntity<Contact> createContact(@RequestBody NewContactDto newContactDto) {
		log.info("IN CREATE CONTACT");
		Contact createdContact = contactService.addContact(newContactDto);
		return new ResponseEntity<>(createdContact, HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<List<Contact>> getContacts() {
		log.info("IN GET CONTACTS");
		List<Contact> contacts = contactService.getContacts();
		return new ResponseEntity<>(contacts, HttpStatus.OK);
	}
	
	@GetMapping("/:{id}")
	public ResponseEntity<Contact> getContactById(@PathVariable("id") int id) {
		log.info("IN GET CONTACT BY ID");
		Contact contact = contactService.getContactById(id);
		return new ResponseEntity<>(contact, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Contact> updateContact(@RequestBody UpdateContactDto updateContactDto) {
		log.info("IN UPDATE CONTACT");
		Contact updatedContact = contactService.updateContact(updateContactDto);
		return new ResponseEntity<>(updatedContact, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteContact(@PathVariable("id") int id) {
		log.info("IN DELETE CONTACT");
		contactService.deleteContact(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
}
