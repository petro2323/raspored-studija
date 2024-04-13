package service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import models.*;

import java.time.LocalTime;
import java.util.List;

import dto.*;

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
	public List<LectureDTO> getLecturesFromSemester(String semester, String room_number) {

		String roomQuery = (room_number == null) ? "" : "'" + room_number + "'";
		String semesterQuery = (semester == null) ? "" : "'" + semester + "'";

		String condition = "";

		if (!roomQuery.isEmpty() && !semesterQuery.isEmpty()) {
			condition = "AND cl.room_number = '" + room_number + "' AND se.roman_number = '" + semester + "'";
		} else if (!semesterQuery.isEmpty()) {
			condition = "AND se.roman_number = '" + semester + "'";
		} else if (!roomQuery.isEmpty()) {
			condition = "AND cl.room_number = '" + room_number + "'";
		}

		return em.createQuery("SELECT new LectureDTO(s.title, cl.room_number, d.day_name, l.time_of_lecture, CONCAT(pro.first_name, ' ', pro.last_name) AS professor, CONCAT(asi.first_name, ' ', asi.last_name) AS associate) "
				+ "FROM LectureHours l " + "INNER JOIN Subject s ON s.id = l.subject.id "
				+ "INNER JOIN Classroom cl ON cl.id = l.classroom.id "
				+ "INNER JOIN DaysOfTheWeek d ON d.id = l.lecture_day.id "
				+ "INNER JOIN Professor pro ON pro.id = s.professor.id "
				+ "LEFT JOIN Associate asi ON asi.id = s.associate.id "
				+ "INNER JOIN Semester se ON se.id = s.semester.id WHERE 1=1 " + condition, LectureDTO.class).getResultList();
	}

	@Transactional
	public List<ProfessorDTO> getProfessorsByAcademicTitle(String title_name) {
		return em
				.createQuery(
						"SELECT new ProfessorDTO(pro.first_name, pro.last_name,\r\n" + "pro.date_of_birth)\r\n"
								+ "FROM Professor pro\r\n" + "INNER JOIN AcademicTitle ac ON\r\n"
								+ "ac.id = pro.academic_title.id\r\n" + "WHERE ac.title_name = :title_name",
						ProfessorDTO.class)
				.setParameter("title_name", title_name).getResultList();
	}

	@Transactional
	public List<StudentDTO> getStudentsBySubject(String subject) {
		return em.createQuery(
				"SELECT new StudentDTO(s.first_name, s.last_name,\r\n" + "s.index_number) FROM Student s\r\n"
						+ "INNER JOIN StudentSubject ss ON\r\n" + "s.id = ss.student.id\r\n"
						+ "INNER JOIN Subject su ON\r\n" + "su.id = ss.subject.id\r\n" + "WHERE su.title = :subject",
				StudentDTO.class).setParameter("subject", subject).getResultList();
	}

	@Transactional
	public List<AcademicTitleDTO> getAllAcademicTitles() {
		return em.createQuery("SELECT new AcademicTitleDTO(a.title_name) FROM AcademicTitle a", AcademicTitleDTO.class)
				.getResultList();
	}

	@Transactional
	public List<SubjectDTO> getAllSubjects() {
		return em.createQuery("SELECT new SubjectDTO(s.title, s.ects) FROM Subject s", SubjectDTO.class)
				.getResultList();
	}

	@Transactional
	public List<ShiftDTO> getSubjectsByShift(String condition, int time) {
		String realCondition = "";
		LocalTime realTime = LocalTime.of(time, 0);

		if (condition.equalsIgnoreCase("before")) {
			realCondition = "<";
		} else if (condition.equalsIgnoreCase("after")) {
			realCondition = ">";
		}

		return em.createQuery("SELECT new ShiftDTO(s.title AS subject, s.ects,\r\n"
				+ "CONCAT(asi.first_name,' ',asi.last_name) AS associate,\r\n"
				+ "CONCAT(pro.first_name,' ',pro.last_name) AS professor,\r\n"
				+ "se.roman_number AS semester, l.time_of_lecture) FROM\r\n" + "Subject s\r\n"
				+ "LEFT JOIN Associate asi ON\r\n" + "asi.id = s.associate.id\r\n" + "INNER JOIN Professor pro ON\r\n"
				+ "pro.id = s.professor.id\r\n" + "INNER JOIN Semester se ON\r\n" + "se.id = s.semester.id\r\n"
				+ "INNER JOIN LectureHours l ON\r\n" + "s.id = l.subject.id\r\n" + "WHERE l.time_of_lecture "
				+ realCondition + ":time", ShiftDTO.class).setParameter("time", realTime).getResultList();
	}
}
