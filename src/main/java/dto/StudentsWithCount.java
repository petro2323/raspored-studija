package dto;

import java.util.List;

public class StudentsWithCount {
	private List<StudentDTO> students;
	private int number;

	public StudentsWithCount(List<StudentDTO> students, int number) {
		super();
		this.students = students;
		this.number = number;
	}

	public List<StudentDTO> getStudents() {
		return students;
	}

	public void setStudents(List<StudentDTO> students) {
		this.students = students;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
