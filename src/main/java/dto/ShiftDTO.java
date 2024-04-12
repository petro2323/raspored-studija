package dto;

import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShiftDTO {
	private String subject;
	private int ects;
	private String associate;
	private String professor;
	private String semester;
	private LocalTime time_of_lecture;

	public ShiftDTO(String subject, int ects, String associate, String professor, String semester,
			LocalTime time_of_lecture) {
		super();
		this.subject = subject;
		this.ects = ects;
		this.associate = associate;
		this.professor = professor;
		this.semester = semester;
		this.time_of_lecture = time_of_lecture;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getEcts() {
		return ects;
	}

	public void setEcts(int ects) {
		this.ects = ects;
	}

	public String getAssociate() {
		return associate;
	}

	public void setAssociate(String associate) {
		this.associate = associate;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public LocalTime getTime_of_lecture() {
		return time_of_lecture;
	}

	public void setTime_of_lecture(LocalTime time_of_lecture) {
		this.time_of_lecture = time_of_lecture;
	}

}
