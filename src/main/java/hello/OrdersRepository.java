package hello;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "orders", path = "orders")
public interface OrdersRepository extends PagingAndSortingRepository<Orders, Long>, MyRepo<Orders, Long> {
	List<Orders> findByOrderType(@Param("name") String orderType);
	List<Orders> findByTotalPrice(@Param("price") int totalPrice);
}
