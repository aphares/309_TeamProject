package com.example.labassistant.app;

public class ClassroomIndividual {

    private String sectionNumber;
    private String name;
    private boolean checkBox;

    public ClassroomIndividual(String givenSectionNumber, String givenName, boolean givenCheckBox){
        sectionNumber = givenSectionNumber;
        name = givenName;
        checkBox = givenCheckBox;
    }

    public ClassroomIndividual(String givenSectionNumber, String givenName){
        sectionNumber = givenSectionNumber;
        name = givenName;
        checkBox = false;
    }


    public String getSectionNumber() {
        return "S" + sectionNumber;
    }

    public void setSectionNumber(String sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheckBox() {
        return checkBox;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }
}
