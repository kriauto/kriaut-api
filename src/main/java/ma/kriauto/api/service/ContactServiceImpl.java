package ma.kriauto.api.service;

import java.util.List;

import ma.kriauto.api.model.Contact;
import ma.kriauto.api.repository.ContactRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
    private ContactRepository contactRepository;

	@Override
	public List<Contact> fetchAllContact() {
		return contactRepository.fetchAllContact();
	}

}
