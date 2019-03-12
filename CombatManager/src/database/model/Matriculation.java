package database.model;

public class Matriculation {
	private Integer code;
	private Integer student_code;
	private String matriculation_date;
	private Integer due_date;
	private String closing_day;
	
	
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Integer getStudent_code() {
		return student_code;
	}
	public void setStudent_code(Integer student_code) {
		this.student_code = student_code;
	}
	public String getMatriculation_date() {
		return matriculation_date;
	}
	public void setMatriculation_date(String matriculation_date) {
		this.matriculation_date = matriculation_date;
	}
	public Integer getDue_date() {
		return due_date;
	}
	public void setDue_date(Integer due_date) {
		this.due_date = due_date;
	}
	public String getClosing_day() {
		return closing_day;
	}
	public void setClosing_day(String closing_day) {
		this.closing_day = closing_day;
	}
}
