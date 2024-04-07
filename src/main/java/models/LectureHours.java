package models;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class LectureHours {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Subject subject;

	@ManyToOne
	private Classroom classroom;

	@ManyToOne
	private DaysOfTheWeek lecture_day;

	private LocalTime time_of_lecture;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public DaysOfTheWeek getLecture_day() {
		return lecture_day;
	}

	public void setLecture_day(DaysOfTheWeek lecture_day) {
		this.lecture_day = lecture_day;
	}

	public LocalTime getTime_of_lecture() {
		return time_of_lecture;
	}

	public void setTime_of_lecture(LocalTime time_of_lecture) {
		this.time_of_lecture = time_of_lecture;
	}

}
