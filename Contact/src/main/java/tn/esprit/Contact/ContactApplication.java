package tn.esprit.Contact;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import tn.esprit.dao.ContactRepository;
import tn.esprit.entities.Contact;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages="tn.esprit.entities")
@EnableJpaRepositories("tn.esprit.dao")
@ComponentScan("tn.esprit.controller")
public class ContactApplication implements CommandLineRunner{

	@Autowired
	private ContactRepository contactRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ContactApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		contactRepository.save(new Contact("Yousfi","Oussama",df.parse("20/07/1994"),"oussama.yousfi@esprit.tn",97426342,""));
		contactRepository.save(new Contact("Yousfi","Ahmed",df.parse("09/09/1991"),"ahmed.yousfi@esprit.tn",55676850,""));
		contactRepository.save(new Contact("Yousfi","Younes",df.parse("30/05/1956"),"younes.yousfi@esprit.tn",95982273,""));
		contactRepository.findAll().forEach(c->System.out.println(c));
	}
}
