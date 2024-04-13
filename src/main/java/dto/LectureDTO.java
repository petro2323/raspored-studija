package dto;

import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LectureDTO {
	private String title;
	private String room_number;
	private String day_name;
	private LocalTime time_of_lecture;
	private String professor;
	private String associate;

	public LectureDTO(String title, String room_number, String day_name, LocalTime time_of_lecture, String professor,
			String associate) {
		super();
		this.title = title;
		this.room_number = room_number;
		this.day_name = day_name;
		this.time_of_lecture = time_of_lecture;
		this.professor = professor;
		this.associate = associate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRoom_number() {
		return room_number;
	}

	public void setRoom_number(String room_number) {
		this.room_number = room_number;
	}

	public String getDay_name() {
		return day_name;
	}

	public void setDay_name(String day_name) {
		this.day_name = day_name;
	}

	public LocalTime getTime_of_lecture() {
		return time_of_lecture;
	}

	public void setTime_of_lecture(LocalTime time_of_lecture) {
		this.time_of_lecture = time_of_lecture;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getAssociate() {
		return associate;
	}

	public void setAssociate(String associate) {
		this.associate = associate;
	}

}
