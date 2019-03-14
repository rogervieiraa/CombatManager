package com.combatmanager.database.model;

public class MatriculationModality {
	private Integer matriculation_code;
	private String modality;
	private String graduation;
	private String plan;
	private String begin_date;
	private String end_date;

	public MatriculationModality () {
		
	}
	
	public MatriculationModality(Integer matriculation_code, String modality, String graduation, String plan,
			String begin_date, String end_date) {
		super();
		this.matriculation_code = matriculation_code;
		this.modality = modality;
		this.graduation = graduation;
		this.plan = plan;
		this.begin_date = begin_date;
		this.end_date = end_date;
	}

	public Integer getMatriculation_code() {
		return matriculation_code;
	}

	public void setMatriculation_code(Integer matriculation_code) {
		this.matriculation_code = matriculation_code;
	}

	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public String getGraduation() {
		return graduation;
	}

	public void setGraduation(String graduation) {
		this.graduation = graduation;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	@Override
	public String toString() {
		return "MatriculationModality [matriculation_code=" + matriculation_code + ", modality=" + modality
				+ ", graduation=" + graduation + ", plan=" + plan + ", begin_date=" + begin_date + ", end_date="
				+ end_date + "]";
	}

}
