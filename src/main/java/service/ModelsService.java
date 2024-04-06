package service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import models.AcademicTitle;
import models.Associate;
import models.Professor;
import models.Student;
import models.YearOfStudy;

@Dependent
public class ModelsService {
	
	@Inject
	private EntityManager em;
	
	@Transactional
	public AcademicTitle createAcademicTitle(AcademicTitle at) {
		return em.merge(at);
	}
	
	@Transactional
	public Associate createAssociate(Associate a) {
		return em.merge(a);
	}
	
	@Transactional
	public Professor createProfessor(Professor p) {
		return em.merge(p);
	}
	
	@Transactional
	public Student createStudent(Student s) {
		return em.merge(s);
	}
	
	@Transactional
	public YearOfStudy createYearOfStudy(YearOfStudy y) {
		return em.merge(y);
	}
}
