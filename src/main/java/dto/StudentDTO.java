package dto;

public class StudentDTO {
	private String first_name;
	private String last_name;
	private String index_number;

	public StudentDTO(String first_name, String last_name, String index_number) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.index_number = index_number;
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

	public String getIndex_number() {
		return index_number;
	}

	public void setIndex_number(String index_number) {
		this.index_number = index_number;
	}

}
