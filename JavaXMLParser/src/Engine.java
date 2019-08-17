public class Engine {

    public static void addStudents(XMLStudentParser parser){

        Student student = new Student("cs-1","Abid","+461111111");
        parser.insertNode(student);

        student = new Student("cs-2","Khan","+4622222222");
        parser.insertNode(student);

        student = new Student("cs-3","Peter","+4633333333");
        parser.insertNode(student);

        parser.saveXMLFile();
    }

    public static void updateStudent(XMLStudentParser parser){

        Student student = new Student("cs-3","Parker","+4633445566");

        parser.updateNode("cs-3", student);

        parser.printXMLFile();

        parser.saveXMLFile();

    }

    public static void removeStudent(XMLStudentParser parser){

        parser.removeNode("cs-3");

        parser.printXMLFile();

        parser.saveXMLFile();
    }


    public static void main(String[] args) {

        String fileName = "Source/studentFile.xml";

        XMLStudentParser parser = new XMLStudentParser(fileName);

        addStudents(parser);

        System.out.println("Check "+ fileName);

        //updateStudent(parser);
        //removeStudent(parser);
    }
}
