package hello;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "items", path = "items")
public interface ItemsRepository extends PagingAndSortingRepository<Items, Long>, MyRepo<Items, Long>
{
	List<Items> findByProductName(@Param("name") String productName);
	List<Items> findByUnitPrice(@Param("price") int unitPrice);
	List<Items> findDistinctItemByProductNameOrUnitPrice(@Param("name")String productName, @Param("price")int unitPrice);
}
