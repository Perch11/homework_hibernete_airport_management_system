package com.homework_xml_json.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "student")
public class Student {
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
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }
    @XmlElement
    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
    @XmlElement
    public String getnName() {
        return nName;
    }

    public void setnName(String nName) {
        this.nName = nName;
    }
    @XmlElement
    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Student() {

    }

    public Student(int id, String fName, String lName, String nName, int marks) {
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
