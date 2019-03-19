package com.combatmanager.database.model;

public class Matriculation {
	private Integer code;
	private Integer student_code;
	private String matriculation_date;
	private Integer due_date;
	private String closing_date;

	public Matriculation() {
		
	}
	
	public Matriculation(Integer code, Integer student_code, String matriculation_date, Integer due_date,
			String closing_date) {
		super();
		this.code = code;
		this.student_code = student_code;
		this.matriculation_date = matriculation_date;
		this.due_date = due_date;
		this.closing_date = closing_date;
	}

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

	public String getClosing_date() {
		return closing_date;
	}

	public void setClosing_date(String closing_date) {
		this.closing_date = closing_date;
	}

	@Override
	public String toString() {
		return "Matriculation [code=" + code + ", student_code=" + student_code + ", matriculation_date="
				+ matriculation_date + ", due_date=" + due_date + ", closing_date=" + closing_date + "]";
	}
}
