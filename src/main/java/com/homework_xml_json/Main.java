package com.homework_xml_json;

import com.homework_xml_json.jaxb.JAXBStudent;
import com.homework_xml_json.jaxb.Student;
import com.homework_xml_json.jaxb.StudentWithoutMarks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JAXBStudent jaxbStudent = new JAXBStudent();
        Student student = new Student(8,"Perch","Harutyunyan","Perch",96);
        //File path = new File("\\src\\main\\resources\\xml\\student.xml");
        //jaxbStudent.marshall(student);
       // System.out.println(jaxbStudent.unmarshall());
        List<StudentWithoutMarks> list = new ArrayList<>();
        list.add(new StudentWithoutMarks(8,"Perch","Harutyunyan","Perch",96));
        list.add(new StudentWithoutMarks(9,"Garik","Harutyunyan","Gar",100));
        list.add(new StudentWithoutMarks(10,"Ani","Harutyunyan","An",86));
        jaxbStudent.marshallList(list);
    }
}
