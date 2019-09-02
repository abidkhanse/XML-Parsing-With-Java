package com.xmlparser.com.xmlparser.classes;

public class Student {

    String id;
    String name;
    String phone;
    String department;

    public Student(String id, String name, String phone, String department){

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.department = department;
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

    public String getDepartment() {
        return department;
    }
}