package hello.ext;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.core.EmbeddedWrapper;
import org.springframework.hateoas.core.EmbeddedWrappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hello.Person;
import hello.PersonRepository;

@BasePathAwareController
@RequestMapping("person/search")
public class PersonCustomSearchController implements ResourceProcessor<RepositorySearchesResource> {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonResourceAssembler personResourceAssembler;

    @Autowired
    private PagedResourcesAssembler<Person> pagedResourcesAssembler;

    @RequestMapping(value="customFind", method=RequestMethod.GET)
    public ResponseEntity<PagedResources> customFind(@RequestParam String firstName, @RequestParam String lastName, @PageableDefault Pageable pageable) {
        Page personPage = personRepository.customFind(firstName, lastName, pageable);
        PagedResources adminPagedResources = pagedResourcesAssembler.toResource(personPage, personResourceAssembler);

        if (personPage.getContent()==null || personPage.getContent().isEmpty()){
            EmbeddedWrappers wrappers = new EmbeddedWrappers(false);
            EmbeddedWrapper wrapper = wrappers.emptyCollectionOf(Person.class);
            List<EmbeddedWrapper> embedded = Collections.singletonList(wrapper);
            adminPagedResources = new PagedResources(embedded, adminPagedResources.getMetadata(), adminPagedResources.getLinks());
        }

        return new ResponseEntity<PagedResources>(adminPagedResources, HttpStatus.OK);
    }

    @Override
    public RepositorySearchesResource process(RepositorySearchesResource repositorySearchesResource) {
        final String search = repositorySearchesResource.getId().getHref();
        final Link customLink = new Link(search + "/customFind{?firstName,lastName,page,size,sort}").withRel("customFind");
        repositorySearchesResource.add(customLink);
        return repositorySearchesResource;
    }

}
