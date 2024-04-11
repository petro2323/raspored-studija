package service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import models.*;

import java.util.List;

import dto.LectureDTO;

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
	
	@Transactional
	public List<LectureDTO> getLecturesFromSemester(String semester) {
		return em.createQuery("SELECT new LectureDTO(s.title, cl.room_number,\r\n"
				+ "d.day_name, l.time_of_lecture,\r\n"
				+ "pro.first_name, pro.last_name)\r\n"
				+ "FROM LectureHours l\r\n"
				+ "INNER JOIN Subject s ON s.id = l.subject.id\r\n"
				+ "INNER JOIN Classroom cl ON cl.id = l.classroom.id\r\n"
				+ "INNER JOIN DaysOfTheWeek d ON d.id = l.lecture_day.id\r\n"
				+ "INNER JOIN Professor pro ON pro.id = s.professor.id\r\n"
				+ "INNER JOIN Semester se ON se.id = s.semester.id\r\n"
				+ "WHERE se.roman_number = :semester", LectureDTO.class).setParameter("semester", semester).getResultList();
	}
}
