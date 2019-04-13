package ma.kriauto.api.repository;

import java.util.List;

import ma.kriauto.api.model.Contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	@Query("SELECT c FROM Contact c")
	public List<Contact> fetchAllContact();

}
