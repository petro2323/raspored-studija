package dto;

import java.time.LocalTime;

public class LectureDTO {
	private String title;
	private String room_number;
	private String day_name;
	private LocalTime time_of_lecture;
	private String first_name;
	private String last_name;

	public LectureDTO(String title, String room_number, String day_name, LocalTime time_of_lecture, String first_name,
			String last_name) {
		super();
		this.title = title;
		this.room_number = room_number;
		this.day_name = day_name;
		this.time_of_lecture = time_of_lecture;
		this.first_name = first_name;
		this.last_name = last_name;
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

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

}
