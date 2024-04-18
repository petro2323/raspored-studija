package models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Semester {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	@Pattern(regexp = "^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$", message = "Invalid input! Please enter a roman number for example: I, IV, VI, X...")
	@NotBlank(message = "Roman number not found! Must enter a roman number!")
	private String roman_number;

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

	public String getRoman_number() {
		return roman_number;
	}

	public void setRoman_number(String roman_number) {
		this.roman_number = roman_number;
	}

}
