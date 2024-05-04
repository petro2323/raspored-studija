package dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProfessorDTO {
	private String first_name;
	private String last_name;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date date_of_birth;

	public ProfessorDTO(String first_name, String last_name, Date date_of_birth) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.date_of_birth = date_of_birth;
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

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

}