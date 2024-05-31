package repository;

import jakarta.enterprise.context.ApplicationScoped;
import models.Student;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<Student>{
	
	public Student findByIndexNumber(String index_number) {
		return find("index_number", index_number).firstResult();
	}
	
}
