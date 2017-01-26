package hello;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import hello.ext.PersonRepositoryCustom;

@RepositoryRestResource(collectionResourceRel = "person", path = "person")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long>, MyRepo<Person, Long>, PersonRepositoryCustom{
	List<Person> findByLastName(@Param("lastName") String lastName);
	List<Person> findByFirstNameAndLastNameAllIgnoringCase(@Param("firstName")String firstName, @Param("lastName")String lastName);
	List<Person> findPersonDistinctByLastNameOrFirstName(@Param("lastName")String lastName, @Param("firstName")String firstName);
}
