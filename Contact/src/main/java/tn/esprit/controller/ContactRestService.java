package tn.esprit.controller;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.dao.ContactRepository;
import tn.esprit.entities.Contact;

@RestController
public class ContactRestService {

	@Autowired
	private ContactRepository contactRepository;
	
	@RequestMapping(value="/contacts",method=RequestMethod.GET)
	public List<Contact> getContacts(){
		return contactRepository.findAll();
	}
	
	@RequestMapping(value="/contacts/{id}",method=RequestMethod.GET)
	public Contact getContact(@PathVariable("id")Long id){
		return contactRepository.findOne(id);
	}
	
	@RequestMapping(value="/contacts",method=RequestMethod.POST)
	public Contact save(@RequestBody Contact c){
		return contactRepository.save(c);
	}
	@RequestMapping(value="/contacts/{id}",method=RequestMethod.DELETE)
	public boolean delete(@PathVariable("id")Long id){
		contactRepository.delete(id);
		return true;
	}
	@RequestMapping(value="/contacts/{id}",method=RequestMethod.PUT)
	public Contact save(@PathVariable("id")Long id,@RequestBody Contact c){
		c.setId(id);
		return contactRepository.save(c);
	}
	
	@RequestMapping(value="/cherchercontact",method=RequestMethod.GET)
	public Page<Contact> chercher(@RequestParam(name="mc", defaultValue="") String mc,
			@RequestParam(name="page", defaultValue="0")int page,
			@RequestParam(name="size", defaultValue="5")int size){
		return contactRepository.chercher("%"+mc+"%", new PageRequest(page, size));
	}
}
