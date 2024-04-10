package models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Student must have a first name!")
	private String first_name;
	
	@NotBlank(message = "Student must have a last name!")
	private String last_name;

	@ManyToOne
	@NotNull(message = "Student must be assigned a year of study!")
	private YearOfStudy year_of_study;
	
	@NotNull(message = "Student must have a birthday!")
	private Date date_of_birth;
	
	@Column(unique = true)
	@Pattern(regexp = "^([1-9][0-9]?-[1-9][0-9])$", message = "Invalid index number! Must be in pattern XX-YY numeric, example: 26-21")
	@NotBlank(message = "Student must have an index number!")
	private String index_number;

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

	public YearOfStudy getYear_of_study() {
		return year_of_study;
	}

	public void setYear_of_study(YearOfStudy year_of_study) {
		this.year_of_study = year_of_study;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getIndex_number() {
		return index_number;
	}

	public void setIndex_number(String index_number) {
		this.index_number = index_number;
	}

}
