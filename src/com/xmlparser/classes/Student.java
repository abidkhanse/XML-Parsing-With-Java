package com.xmlparser.classes;

import java.util.ArrayList;

public class Student {

    private String id;
    private String name;
    private String phone;
    private String department;
    private ArrayList<String> courses;

    public Student(String id, String name, String phone, String department, ArrayList<String> courses){

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.department = department;
        this.courses = courses;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDepartment(){
        return department;
    }

    public ArrayList<String> getCourses(){
        return courses;
    }
}