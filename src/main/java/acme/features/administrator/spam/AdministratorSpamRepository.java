package acme.features.administrator.spam;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spam.Spam;
import acme.framework.entities.Administrator;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSpamRepository extends AbstractRepository {

	@Query("select s from Spam s") 
	Collection<Spam> searchOne();
	
	@Query("select a from Administrator a where a.id=?1")
	Administrator findAdminById(int id);
}

	