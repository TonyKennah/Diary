package hello;

import java.io.Serializable;
import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class MyRepoImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements MyRepo<T, ID> {

	private final EntityManager entityManager;

	public MyRepoImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);

		// Keep the EntityManager around to used from the newly introduced
		// methods.
		this.entityManager = entityManager;
	}

	public void sharedCustomMethod(ID id) {
		System.out.println("CUSTOM!!!! wow");

	}
}