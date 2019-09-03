package com.xmlparser.logic;

import com.xmlparser.classes.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class XMLStudentParser {

    static final String ROOT     = "students";

    static final String ELEMENT     = "student";
    static final String ID          = "id";
    static final String NAME        = "name";
    static final String PHONE       = "phone";
    static final String DEPARTMENT  = "department";
    static final String COURSES     = "courses";

    private Document xmlDocument;
    private String fileName;

    public XMLStudentParser(String fileName){

        this.fileName = fileName;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        File file = new File(fileName);

        try {

            DocumentBuilder documentBuilder = factory.newDocumentBuilder();

            if(file.exists()) {
                xmlDocument = documentBuilder.parse(file);
            }
            else {
                xmlDocument = documentBuilder.newDocument();
            }

        } catch (Exception e) {

            // Create "Source" folder, parallel to "src" folder
            e.printStackTrace();
        }

    }


    public void printXMLFile(){

        NodeList nodeList = xmlDocument.getDocumentElement().getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;

                printElement(element);
            }
        }
    }

    private void printElement(Element element){

        String id = element.getAttribute(ID);

        String name = getElementValueByTag(element,NAME);

        String phone = getElementValueByTag(element,PHONE);

        String department = getElementValueByTag(element, DEPARTMENT);

        System.out.println("Student ID = " + id + ", Name = " + name + ", Phone = " + phone + ", Department = " + department);

        NodeList courses = getElementsByTag(element, COURSES);

        System.out.println("Courses");
        System.out.println("=======");

        Node course;

        for (int i = 0; i < courses.getLength(); i++) {

            course = courses.item(i);

            System.out.println(course.getTextContent());

        }
    }

    public boolean removeNode(String id) {

        boolean found = false;

        Element root = xmlDocument.getDocumentElement();

        NodeList students = root.getChildNodes();

        for (int i = 0; i < students.getLength(); i++) {

            Node node = students.item(i);

            if(node.getNodeType() == Element.ELEMENT_NODE) {

                Element student = (Element) node;

                if(id.equals(student.getAttribute(ID))){

                    if(root.removeChild(student) != null){

                        xmlDocument.replaceChild(root,root);
                        found = true;
                    }
                    break;
                }
            }
        }

        return found;
    }


    public boolean updateNode(String id, Student newObj) {

        boolean found = false;

        Element root = xmlDocument.getDocumentElement();

        Element newNode = createNode(newObj);

        NodeList students = root.getChildNodes();

        for (int i = 0; i < students.getLength(); i++) {

            Node node = students.item(i);

            if (node.getNodeType() == Element.ELEMENT_NODE) {

                Element student = (Element) node;

                if(id.equals(student.getAttribute(ID))){

                    if (root.replaceChild(newNode, student) != null) {

                        xmlDocument.replaceChild(root, root);
                        found = true;
                    }
                    break;
                }
            }
        }

        return found;
    }

    private Element createNode(Student student){

        Element node = xmlDocument.createElement(ELEMENT);

        node.setAttribute(ID, student.getId());

        node.appendChild(createChildElement(NAME, student.getName()));

        node.appendChild(createChildElement(PHONE, student.getPhone()));

        node.appendChild(createChildElement(DEPARTMENT, student.getDepartment() ));

        node.appendChild(createChildElementsList(COURSES, "subject", student.getCourses()) );

        return node;

    }

    private Element createChildElementsList(String tag, String heading, ArrayList<String> list){

        Element element = xmlDocument.createElement(tag);

        int id = 1;

        Element courseElement;

        for (String course : list) {

            courseElement = xmlDocument.createElement(heading + "-" + id++);

            courseElement.appendChild(xmlDocument.createTextNode(course));

            element.appendChild(courseElement);

        }

        return element;
    }

    private Element createChildElement(String tag, String value){

        Element element = xmlDocument.createElement(tag);

        element.appendChild(xmlDocument.createTextNode(value));

        return element;
    }

    public  boolean insertNode(Student student){

        boolean inserted = false;

        Element root = xmlDocument.getDocumentElement();

        if(root == null){

            root = xmlDocument.createElement(ROOT);

            xmlDocument.appendChild(root);
        }

        Element newNode = createNode(student);

        if(root.appendChild(newNode) != null){

            inserted = true;

            xmlDocument.replaceChild(root,root);
        }

        return inserted;
    }

    public void saveXMLFile(){

        TransformerFactory factory = TransformerFactory.newInstance();

        try {

            Transformer transformer = factory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.setOutputProperty(OutputKeys.VERSION,"1.0");
            transformer.setOutputProperty(OutputKeys.STANDALONE,"no");

            DOMSource domSource = new DOMSource(xmlDocument);

            StreamResult streamResult = new StreamResult(new File(fileName));

            transformer.transform(domSource,streamResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String getElementValueByTag(Element element, String tag){

        return element.getElementsByTagName(tag).item(0).getTextContent();
    }

    private NodeList getElementsByTag(Element element, String tag){

        return element.getElementsByTagName(tag);
    }


}
