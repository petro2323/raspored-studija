package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import models.Subject;

@ApplicationScoped
public class SubjectRepository implements PanacheRepository<Subject> {

	public Subject findById(Long id) {
		return find("id", id).firstResult();
	}
}
