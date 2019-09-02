package com.xmlparser.main;

import com.xmlparser.com.xmlparser.classes.Student;
import com.xmlparser.logic.XMLStudentParser;

public class Main {

    public static void addStudents(XMLStudentParser parser){

        Student student = new Student("cs-1","Abid","+461111111", "CS");
        parser.insertNode(student);

        student = new Student("cs-2","Khan","+4622222222", "CS");
        parser.insertNode(student);

        student = new Student("cs-3","Peter","+4633333333", "CS");
        parser.insertNode(student);

        parser.saveXMLFile();
    }

    public static void updateStudent(XMLStudentParser parser){

        Student student = new Student("se-3","Parker","+4633445566", "SE");

        parser.updateNode("cs-3", student);

        parser.printXMLFile();

        parser.saveXMLFile();

    }

    public static void removeStudent(XMLStudentParser parser){

        parser.removeNode("se-3");

        parser.printXMLFile();

        parser.saveXMLFile();
    }


    public static void main(String[] args) {

        String fileName = "Source/studentFile.xml";

        XMLStudentParser parser = new XMLStudentParser(fileName);

        addStudents(parser);

    }
}
