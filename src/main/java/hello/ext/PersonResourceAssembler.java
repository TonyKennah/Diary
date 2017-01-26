package hello.ext;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import hello.Person;

@Component
public class PersonResourceAssembler extends ResourceAssemblerSupport<Person, PersonResource> {

    @Autowired
    RepositoryEntityLinks repositoryEntityLinks;

    public PersonResourceAssembler() {
        super(PersonCustomSearchController.class, PersonResource.class);
    }

    @Override
    public PersonResource toResource(Person person) {
        Link personLink = repositoryEntityLinks.linkToSingleResource(Person.class, person.getFirstName());
        Link selfLink = new Link(personLink.getHref(), Link.REL_SELF);
        return new PersonResource(person, Arrays.asList(selfLink, personLink));
    }

}