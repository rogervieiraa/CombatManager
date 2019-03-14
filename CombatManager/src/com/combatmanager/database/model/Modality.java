package com.combatmanager.database.model;

public class Modality {
	private String modality;

	
	public Modality () {
		
	}
	
	public Modality(String modality) {
		super();
		this.modality = modality;
	}

	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	@Override
	public String toString() {
		return "Modality [modality=" + modality + "]";
	}
}
