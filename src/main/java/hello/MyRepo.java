package hello;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface MyRepo<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> 
{
	void sharedCustomMethod(ID id);
}
