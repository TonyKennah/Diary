package hello.ext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import hello.Person;

public interface PersonRepositoryCustom {
    Page<Person> customFind(String firstName, String lastName, Pageable pageable);
}
