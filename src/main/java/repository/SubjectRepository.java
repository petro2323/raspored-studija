package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import models.Subject;

@ApplicationScoped
public class SubjectRepository implements PanacheRepository<Subject> {

	public Subject findBySubjectTitle(String title) {
		return find("title", title).firstResult();
	}
}
