package dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeDTO {
	private long id;
	private String first_name;
	private String last_name;
	private Date date_of_birth;
	private String academic_title;

	public EmployeeDTO(long id, String first_name, String last_name, Date date_of_birth, String academic_title) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.date_of_birth = date_of_birth;
		this.academic_title = academic_title;
	}

	public EmployeeDTO(long id, String first_name, String last_name, Date date_of_birth) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.date_of_birth = date_of_birth;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getAcademic_title() {
		return academic_title;
	}

	public void setAcademic_title(String academic_title) {
		this.academic_title = academic_title;
	}

	@Override
	public String toString() {
		String title_name = (academic_title == null) ? "" : "\nacademic_title: " + academic_title;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(date_of_birth);
		String employee = (title_name == "") ? "associate_id: " : "professor_id: ";
		
		return employee + id + "\nfirst_name: " + first_name + "\nlast_name: " + last_name + "\ndate_of_birth: "
				+ date + title_name;
	}
}
