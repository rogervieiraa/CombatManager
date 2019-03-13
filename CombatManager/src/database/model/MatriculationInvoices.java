package database.model;

public class MatriculationInvoices {
	private Integer matriculation_code;
	private String due_date;
	private float value;
	private String pay_date;
	private String cancel_date;
	
	
	public Integer getMatriculation_code() {
		return matriculation_code;
	}
	public void setMatriculation_code(Integer matriculation_code) {
		this.matriculation_code = matriculation_code;
	}
	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	public String getCancel_date() {
		return cancel_date;
	}
	public void setCancel_date(String cancel_date) {
		this.cancel_date = cancel_date;
	}
	
	
	@Override
	public String toString() {
		return "MatriculationInvoices [matriculation_code=" + matriculation_code + ", due_date=" + due_date + ", value="
				+ value + ", pay_date=" + pay_date + ", cancel_date=" + cancel_date + "]";
	}
	
	
}
