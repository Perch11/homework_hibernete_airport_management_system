package com.homework_xml_json.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "students")
public class ListStudent {

    private List<StudentWithoutMarks> students = new ArrayList<>();

    @XmlElement(name = "student")
    public List<StudentWithoutMarks> getStudents() {
        return students;
    }

    public void setStudents(List<StudentWithoutMarks> students) {
        this.students = students;
    }
}
