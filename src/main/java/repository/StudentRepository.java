package repository;

import jakarta.enterprise.context.ApplicationScoped;
import models.Student;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<Student>{
	
	public Student findById(Long id) {
		return find("id", id).firstResult();
	}
	
}
