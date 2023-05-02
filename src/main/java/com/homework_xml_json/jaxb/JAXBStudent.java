package com.homework_xml_json.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class JAXBStudent {
    public void marshall(Student student) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
            Marshaller marshall = jaxbContext.createMarshaller();
            marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshall.marshal(student, System.out);
            marshall.marshal(student, new File("src/main/resources/xml/student.xml"));

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public void marshallList(List<StudentWithoutMarks> list) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ListStudent.class);
            Marshaller marshall = jaxbContext.createMarshaller();
            marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ListStudent students = new ListStudent();
            students.setStudents(list);
            marshall.marshal(students, System.out);
            marshall.marshal(students, new File("src/main/resources/xml/studentWithoutMarks.xml"));


        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public Student unmarshall() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
            Unmarshaller unmarshall = jaxbContext.createUnmarshaller();
            Student student = (Student) unmarshall.unmarshal(new File("src/main/resources/xml/student.xml"));
            if (student != null) return student;
            return null;

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}
