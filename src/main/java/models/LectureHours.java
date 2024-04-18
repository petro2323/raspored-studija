package models;

import java.time.LocalTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class LectureHours {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@NotNull(message = "Lecture must have a subject!")
	private Subject subject;

	@ManyToOne
	@NotNull(message = "Lecture must have a classroom!")
	private Classroom classroom;

	@ManyToOne
	@NotNull(message = "Lecture must have a day!")
	private DaysOfTheWeek lecture_day;

	@NotNull(message = "The time of the lecture must be inserted.")
	private LocalTime time_of_lecture;

	@OneToOne(cascade = CascadeType.ALL)
	private IPLog iplog;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public IPLog getIplog() {
		return iplog;
	}

	public void setIplog(IPLog iplog) {
		this.iplog = iplog;
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
