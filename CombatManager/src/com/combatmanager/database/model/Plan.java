package com.combatmanager.database.model;

public class Plan {
	private String modality;
	private String plan;
	private float month_value;

	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public float getMonth_value() {
		return month_value;
	}

	public void setMonth_value(float month_value) {
		this.month_value = month_value;
	}

	@Override
	public String toString() {
		return "Plan [modality=" + modality + ", plan=" + plan + ", month_value=" + month_value + "]";
	}
}
