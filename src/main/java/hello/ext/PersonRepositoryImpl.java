package hello.ext;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import hello.Person;
import hello.PersonRepository;

public class PersonRepositoryImpl implements PersonRepositoryCustom{

	@Autowired
	PersonRepository people;
	
    @Override
    public Page<Person> customFind(String firstName, String lastName, Pageable pageable) {
    	System.out.println("wow");
    	people.sharedCustomMethod((long) 1001);
    	Page<Person> peeps = people.findAll(pageable);
    	System.out.println("Size : "+ peeps.getSize());
    	List<Person> p = peeps.getContent();
    	Page<Person> pages = new PageImpl<Person>(p, pageable, p.size());
    	
    	return pages;
    }
}