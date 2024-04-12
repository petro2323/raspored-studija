package dto;

public class SubjectDTO {
	private String title;
	private int ects;

	public SubjectDTO(String title, int ects) {
		super();
		this.title = title;
		this.ects = ects;
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

}
