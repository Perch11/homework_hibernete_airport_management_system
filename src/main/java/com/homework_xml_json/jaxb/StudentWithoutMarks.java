package com.homework_xml_json.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
@XmlRootElement(name = "student")
public class StudentWithoutMarks {
    private int id;
    private String  fName;
    private String  lName;
    private String  nName;
    private int  marks;

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @XmlElement
    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }
    @XmlElement
    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }
    @XmlElement
    public String getNName() {
        return nName;
    }

    public void setNName(String nName) {
        this.nName = nName;
    }
    @XmlTransient
    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public StudentWithoutMarks() {

    }

    public StudentWithoutMarks(int id, String fName, String lName, String nName, int marks) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.nName = nName;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", nName='" + nName + '\'' +
                ", marks=" + marks +
                '}';
    }
}
