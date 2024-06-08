package models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Subject must have a title name!")
	private String title;

	@NotNull(message = "Subject must have an ECTS number!")
	private int ects;

	@ManyToOne
	@NotNull(message = "Subject must have a professor!")
	private Professor professor;

	@ManyToOne
	private Associate associate;

	@ManyToOne
	@NotNull(message = "Subject must have a semester!")
	private Semester semester;

	@ManyToMany(mappedBy = "subjects", fetch = FetchType.LAZY)
	private Set<Student> students = new HashSet<Student>();

	@OneToOne(cascade = CascadeType.ALL)
	private IPLog iplog;

	public Long getId() {
		return id;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getEcts() {
		return ects;
	}

	public void setEcts(int ects) {
		this.ects = ects;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Associate getAssociate() {
		return associate;
	}

	public void setAssociate(Associate associate) {
		this.associate = associate;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

}
