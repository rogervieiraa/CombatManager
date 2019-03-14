package com.combatmanager.database.model;

public class Student {
	private Integer index;
	private String name;
	private String birthday;
	private char sex;
	private String phoneNumber;
	private String cellPhoneNumber;
	private String email;
	private String note; // anotacoes / observacoes
	private String adress;
	private String homeNumber; // numero da casa
	private String extraInformation;
	private String local; // bairro
	private City city;
	private String cep;

	public Student() {
	}

	public Student(Integer index, String name, String birthday, char sex, String phoneNumber, String cellPhoneNumber,
			String email, String note, String adress, String homeNumber, String extraInformation, String local,
			City city, String cep) {

		this.index = index;
		this.name = name;
		this.birthday = birthday;
		this.sex = sex;
		this.phoneNumber = phoneNumber;
		this.cellPhoneNumber = cellPhoneNumber;
		this.email = email;
		this.note = note;
		this.adress = adress;
		this.homeNumber = homeNumber;
		this.extraInformation = extraInformation;
		this.local = local;
		this.city = city;
		this.cep = cep;

	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getExtraInformation() {
		return extraInformation;
	}

	public void setExtraInformation(String extraInformation) {
		this.extraInformation = extraInformation;
	}

	public String getBirthday() {
		return birthday;
	}

	// variation using integer
	public void setBirthday(Integer day, Integer month, Integer year) {
		this.birthday = day.toString() + '/' + month.toString() + '/' + year.toString();
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCellPhoneNumber() {
		return cellPhoneNumber;
	}

	public void setCellPhoneNumber(String cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return "Student [index=" + index + ", birthday=" + birthday + ", sex=" + sex + ", phoneNumber=" + phoneNumber
				+ ", cellPhoneNumber=" + cellPhoneNumber + ", note=" + note + ", adress=" + adress + ", homeNumber="
				+ homeNumber + ", local=" + local + ", city=" + city + ", cep=" + cep + "]";
	}

}
