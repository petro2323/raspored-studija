package dto;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentsWithCount {
	private Set<StudentDTO> students;
	private List<StudentDTO> students_list;
	private int number;

	public StudentsWithCount(Set<StudentDTO> students, int number, List<StudentDTO> student_list) {
		super();
		
		if (students == null) {
			this.students = null;
		} else {
			this.students = students;
		}
		
		if (student_list == null) {
			this.students_list = null;
		} else {
			this.students_list = student_list;
		}
		
		this.number = number;
	}

	public Set<StudentDTO> getStudents() {
		return students;
	}

	public void setStudents(Set<StudentDTO> students) {
		this.students = students;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<StudentDTO> getStudents_list() {
		return students_list;
	}

	public void setStudents_list(List<StudentDTO> students_list) {
		this.students_list = students_list;
	}

}
