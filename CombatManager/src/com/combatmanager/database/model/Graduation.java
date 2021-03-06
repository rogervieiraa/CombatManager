package com.combatmanager.database.model;

public class Graduation {
	private String modality;
	private String graduation;

	public Graduation () {
		
	}
	
	public Graduation(String modality, String graduation) {
		super();
		this.modality = modality;
		this.graduation = graduation;
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

	@Override
	public String toString() {
		return "Graduation [modality=" + modality + ", graduation=" + graduation + "]";
	}

}
