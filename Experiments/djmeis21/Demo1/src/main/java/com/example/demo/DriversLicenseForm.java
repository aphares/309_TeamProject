package com.example.demo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DriversLicenseForm {
	
	@NotNull
	private String state;
	
	@NotNull
	private char classLetter;
	
	@NotNull
	@Size(min=10, max=10)
	private String expDate;
	
	@NotNull
	@Size(min=10, max=10)
	private String DOB;
	
	@NotNull
	@Size(min=9, max=11)
	private String driversLicenseNum;
	
	@NotNull
	@Size(min=2, max=30)
	private String name;
	
	@NotNull
	private String address;
	
	@NotNull
	private char sex;
	
	//in inches
	@NotNull
	@Size(min=30, max=100)
	private int height;
	
	@NotNull
	private int weight;
	
	@NotNull
	private String eyeColor;
	
	@NotNull
	@Size(min=10, max=10)
	private String issueDate;
	
	public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
    
    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public String getEyeColor() {
        return this.eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
    
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public char getClassLetter() {
        return classLetter;
    }

    public void setClassLetter(char classLetter) {
        this.classLetter = classLetter;
    }
    
    public String getExpDate() {
        return this.expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getDriversLicenseNum() {
        return driversLicenseNum;
    }

    public void setDriversLicenseNum(String DriversLicenseNum) {
        this.driversLicenseNum = DriversLicenseNum;
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String dob) {
        this.DOB = dob;
    }

    public String toString() {
        return "Drivers License\nState: " + this.state + "\nClass: " + this.classLetter + 
        		"\nExpiration Date: " + this.expDate + "\nDOB: " + this.DOB + "\nDL Number: "
        		+ this.driversLicenseNum + "\nName: "+ this.name + "\nAddress: " + this.address +
        		"\nSex: " + this.sex + "\nHeight: " + this.height + "\nWeight: " + this.weight +
        		"\nEye Color: " + this.eyeColor;
    }
    

	

}
