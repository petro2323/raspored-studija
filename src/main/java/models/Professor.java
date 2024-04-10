package models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Professor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Professor must have a first name!")
	private String first_name;
	
	@NotBlank(message = "Professor must have a last name!")
	private String last_name;
	
	@NotNull(message = "Professor must have a birthday!")
	private Date date_of_birth;

	@ManyToOne
	@NotNull(message = "Professor must have an academic title!")
	private AcademicTitle academic_title;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public AcademicTitle getAcademic_title() {
		return academic_title;
	}

	public void setAcademic_title(AcademicTitle academic_title) {
		this.academic_title = academic_title;
	}

}
