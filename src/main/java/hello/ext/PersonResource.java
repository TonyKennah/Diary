package hello.ext;

import org.springframework.hateoas.Link;

import hello.Person;

public class PersonResource extends org.springframework.hateoas.Resource<Person>{

    public PersonResource(Person content, Iterable<Link> links) {
        super(content, links);
    }
}