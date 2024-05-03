package service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import models.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import dto.*;

@Dependent
public class ModelsService {

	@Inject
	private EntityManager em;

	private boolean isRoman(String input) {
		return input.matches("^[IVXLCDM]+$");
	}

	private String subjectInputToDBFormat(String[] array) {
		String title = "";

		if (isRoman(array[0].toUpperCase())) {
			return null;
		} else {
			title += array[0].toUpperCase().charAt(0) + array[0].substring(1).toLowerCase();
		}

		if (array.length > 1) {
			for (int i = 1; i < array.length; i++) {
				if (isRoman(array[i].toUpperCase())) {
					title += " " + array[i].toUpperCase();
				} else {
					title += " " + array[i].toLowerCase();
				}
			}
		}

		return title;
	}

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
			condition = "AND cl.room_number = " + roomQuery + " AND se.roman_number = " + semesterQuery;
		} else if (!semesterQuery.isEmpty()) {
			condition = "AND se.roman_number = " + semesterQuery;
		} else if (!roomQuery.isEmpty()) {
			condition = "AND cl.room_number = " + roomQuery;
		}

		return em.createQuery(
				"SELECT new LectureDTO(s.title, cl.room_number, d.day_name, l.time_of_lecture, CONCAT(pro.first_name, ' ', pro.last_name) AS professor, CONCAT(asi.first_name, ' ', asi.last_name) AS associate) "
						+ "FROM LectureHours l " + "INNER JOIN Subject s ON s.id = l.subject.id "
						+ "INNER JOIN Classroom cl ON cl.id = l.classroom.id "
						+ "INNER JOIN DaysOfTheWeek d ON d.id = l.lecture_day.id "
						+ "INNER JOIN Professor pro ON pro.id = s.professor.id "
						+ "LEFT JOIN Associate asi ON asi.id = s.associate.id "
						+ "INNER JOIN Semester se ON se.id = s.semester.id WHERE 1=1 " + condition,
				LectureDTO.class).getResultList();
	}

	@Transactional
	public List<ProfessorDTO> getProfessorsByAcademicTitle(String title_name) {

		String titleNameQuery = (title_name == null) ? "" : "'" + title_name + "'";

		String condition = "";

		if (!titleNameQuery.isEmpty()) {
			condition = "AND ac.title_name = " + titleNameQuery;
		}

		return em
				.createQuery("SELECT new ProfessorDTO(pro.first_name, pro.last_name,\r\n" + "pro.date_of_birth)\r\n"
						+ "FROM Professor pro\r\n" + "INNER JOIN AcademicTitle ac ON\r\n"
						+ "ac.id = pro.academic_title.id\r\n" + "WHERE 1=1 " + condition, ProfessorDTO.class)
				.getResultList();
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
	public List<YearOfStudyDTO> getAllStudyYears() {
		return em.createQuery("SELECT new YearOfStudyDTO(type) FROM YearOfStudy", YearOfStudyDTO.class).getResultList();
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

	@Transactional
	public List<StudentDTO> getStudentsByYearOfStudy(String year_of_study) {
		return em.createQuery(
				"SELECT new StudentDTO(s.first_name, s.last_name, s.index_number)\r\n" + "FROM Student s\r\n"
						+ "INNER JOIN YearOfStudy y ON y.id = s.year_of_study.id\r\n" + "WHERE y.type = :year_of_study",
				StudentDTO.class).setParameter("year_of_study", year_of_study).getResultList();
	}

	@Transactional
	public boolean updateSubject(int ects, String associate, String professor, String semester, String newTitle,
			String oldTitle) {
		List<String> statements = new ArrayList<String>();

		if (ects > 0) {
			statements.add("ects = " + ects);
		}

		if (associate != null) {
			String[] splitAssociate = associate.split(" ");
			statements.add("associate.id = (SELECT id FROM Associate WHERE first_name = '"
					+ splitAssociate[0].toUpperCase().charAt(0) + splitAssociate[0].substring(1).toLowerCase()
					+ "' AND last_name = '" + splitAssociate[1].toUpperCase().charAt(0)
					+ splitAssociate[1].substring(1).toLowerCase() + "')");
		}

		if (professor != null) {
			String[] splitProfessor = professor.split(" ");
			statements.add("professor.id = (SELECT id FROM Professor WHERE first_name = '"
					+ splitProfessor[0].toUpperCase().charAt(0) + splitProfessor[0].substring(1).toLowerCase()
					+ "' AND last_name = '" + splitProfessor[1].toUpperCase().charAt(0)
					+ splitProfessor[1].substring(1).toLowerCase() + "')");
		}

		if (semester != null) {
			statements.add(
					"semester.id = (SELECT id FROM Semester WHERE roman_number = '" + semester.toUpperCase() + "')");
		}

		if (newTitle != null) {
			String dbTitle = subjectInputToDBFormat(newTitle.split(" "));
			if (dbTitle == null) {
				return false;
			} else {
				statements.add("title = '" + dbTitle + "'");
			}
		}

		String query = "UPDATE Subject SET ";

		for (int i = 0; i < statements.size(); i++) {
			query += statements.get(i);
			if (i < statements.size() - 1) {
				query += ", ";
			}
		}

		if (oldTitle == null) {
			return false;
		} else {
			String request = subjectInputToDBFormat(oldTitle.split(" "));
			if (request == null) {
				return false;
			} else {
				query += " WHERE id = (SELECT id FROM Subject WHERE title = '" + request + "')";
			}
		}

		return em.createQuery(query).executeUpdate() > 0;
	}

	@Transactional
	public boolean updateLecture(LocalTime time, String classroom, String day, String subject) {
		List<String> statements = new ArrayList<String>();

		if (time != null) {
			statements.add("time_of_lecture = :time");
		}

		if (classroom != null) {
			statements.add(
					"classroom.id = (SELECT id FROM Classroom WHERE room_number = '" + classroom.toUpperCase() + "')");
		}

		if (day != null) {
			char[] checkDay = day.toCharArray();
			checkDay[0] = Character.toUpperCase(checkDay[0]);

			for (int i = 1; i < checkDay.length; i++) {
				checkDay[i] = Character.toLowerCase(checkDay[i]);
			}

			statements.add(
					"lecture_day.id = (SELECT id FROM DaysOfTheWeek WHERE day_name = '" + new String(checkDay) + "')");
		}

		String query = "UPDATE LectureHours SET ";

		for (int i = 0; i < statements.size(); i++) {
			query += statements.get(i);
			if (i < statements.size() - 1) {
				query += ", ";
			}
		}

		if (subject == null) {
			return false;
		} else {
			String request = subjectInputToDBFormat(subject.split(" "));
			if (request == null) {
				return false;
			} else {
				query += " WHERE subject.id = (SELECT id FROM Subject WHERE title = '" + request + "')";
			}
		}

		if (time != null) {
			return em.createQuery(query).setParameter("time", time).executeUpdate() > 0;
		} else {
			return em.createQuery(query).executeUpdate() > 0;
		}
	}
}
