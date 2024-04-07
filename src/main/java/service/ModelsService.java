package service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import models.*;

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
	
	@Transactional
	public Semester createSemester(Semester s) {
		return em.merge(s);
	}
	
	@Transactional
	public Subject createSubject(Subject s) {
		return em.merge(s);
	}
	
	@Transactional
	public Classroom createClassRoom(Classroom c) {
		return em.merge(c);
	}
	
	@Transactional
	public DaysOfTheWeek createDaysOfTheWeek(DaysOfTheWeek d) {
		return em.merge(d);
	}
	
	@Transactional
	public LectureHours createLectureHours(LectureHours l) {
		return em.merge(l);
	}
	
	@Transactional
	public StudentSubject createStudentSubject(StudentSubject ss) {
		return em.merge(ss);
	}
}
