package database.model;

public class Attendance {
	private Integer matriculation_code;
	private String entry_date;
	
	
	public Integer getMatriculation_code() {
		return matriculation_code;
	}
	public void setMatriculation_code(Integer matriculation_code) {
		this.matriculation_code = matriculation_code;
	}
	public String getEntry_date() {
		return entry_date;
	}
	public void setEntry_date(Integer day, Integer month, Integer year) {
		this.entry_date = day.toString() + '/' + month.toString() + '/' + year.toString();
	}
	public void setEntry_date(String entry_date) {
		this.entry_date = entry_date;
	}
}
