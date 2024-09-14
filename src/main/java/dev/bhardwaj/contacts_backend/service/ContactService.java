package dev.bhardwaj.contacts_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.bhardwaj.contacts_backend.dto.NewContactDto;
import dev.bhardwaj.contacts_backend.dto.UpdateContactDto;
import dev.bhardwaj.contacts_backend.exception.NotFoundException;
import dev.bhardwaj.contacts_backend.model.Contact;
import dev.bhardwaj.contacts_backend.repository.ContactRepository;

@Service
public class ContactService {
	
	private final ContactRepository contactRepository;
	
	public ContactService(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}
	
	public Contact addContact(NewContactDto newContactDto) {
		Contact contact = new Contact();
		contact.setName(newContactDto.getName());
		contact.setMobile(newContactDto.getMobile());
		return contactRepository.save(contact);
	}
	
	public List<Contact> getContacts() {
		return contactRepository.findAll();
	}
	
	public Contact getContactById(int id) {
		return contactRepository.findById(id).orElseThrow(()->new NotFoundException("Not found!"));
	}
	
	public Contact updateContact(UpdateContactDto updateContactDto) {
		
		Contact existingContact = contactRepository.findById(updateContactDto.getId())
				.orElseThrow(()->new NotFoundException("Cannot update! No contact by given id."));
		existingContact.setName(updateContactDto.getName());
		existingContact.setMobile(updateContactDto.getMobile());
		return contactRepository.save(existingContact);
	}
	
	public void deleteContact(int id) {
		if(!contactRepository.existsById(id)) {
			throw new NotFoundException("Cannot Delete! Contact does not exist.");
		}
		contactRepository.deleteById(id);
	}
	
}
