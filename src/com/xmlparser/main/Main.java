package com.xmlparser.main;

import com.xmlparser.classes.Student;
import com.xmlparser.logic.XMLParser;

import java.util.ArrayList;

public class Main {

    public static void addStudents(XMLParser parser){

        ArrayList<String> courses = new ArrayList<>();
        courses.add("Intro to Computer Science");
        courses.add("Calculus");
        courses.add("Physics");

        Student student = new Student("cs-1","Abid","+461111111", "CS",courses);
        parser.insertNode(student);

        student = new Student("cs-2","Khan","+4622222222", "CS",courses);
        parser.insertNode(student);

        student = new Student("cs-3","Peter","+4633333333", "CS",courses);
        parser.insertNode(student);

        parser.saveXMLFile();
    }

    public static void updateStudent(XMLParser parser){

        ArrayList<String> courses = new ArrayList<>();
        courses.add("Intro to Software Engineering");
        courses.add("Intro to Programming");
        courses.add("Calculus");
        courses.add("Physics");

        Student student = new Student("se-3","Peter","+4633333333", "SE", courses);

        if(parser.updateNode("cs-3", student)){
            parser.saveXMLFile();
        }
        else {
            System.out.println("Record not found");
        }
    }

    public static void removeStudent(XMLParser parser){

        if(parser.removeNode("se-3")){
            parser.saveXMLFile();
        }
        else {
            System.out.println("Record not found");
        }
    }


    public static void main(String[] args) {

        String fileName = "Source/studentFile.xml";

        XMLParser parser = new XMLParser(fileName);

        //addStudents(parser);

        //parser.printXMLFile();

        //updateStudent(parser);

        removeStudent(parser);

    }
}
