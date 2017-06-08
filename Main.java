package com.company;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        //創建DocumentBuilderFactory實例,指定DocumentBuilder
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException pce) {
            System.err.println(pce);
            //出異常時輸出異常資訊，然後退出，下同
            System.exit(1);
        }
        Document doc = null;
        try {
            doc = db.parse("/home/frank/IdeaProjects/parseXml/src/com/company/hi.xml");
        } catch (DOMException dom) {
            System.err.println(dom.getMessage());
            System.exit(1);
        } catch (IOException ioe) {
            System.err.println(ioe);
            System.exit(1);
        } catch (Exception e){
            System.err.println(e);
            System.exit(1);
        }


        //parse XML
        Element root = doc.getDocumentElement(); //item
        //取"學生"元素列表
        NodeList students = root.getElementsByTagName("student");

        //創建CStudent的實例
        CStudent[] IStudent = new CStudent[students.getLength()];  //物件陣列
        for(int i=0;i<students.getLength();i++) {
            IStudent[i] = new CStudent();
        }

        //依次取每個"學生"元素
        for(int i=0; i<students.getLength(); i++) {
            //依次取每個"學生"元素
            Element student = (Element) students.item(i);

            //取學生的性別屬性
            IStudent[i].setSex(student.getAttribute("sex"));
            //取"id"元素
            NodeList ids = student.getElementsByTagName("id");
            if (ids.getLength() == 1) {
                Element e = (Element) ids.item(0);
                Text t = (Text) e.getFirstChild();
                IStudent[i].setId(t.getNodeValue());
            }
        }

        //print parse result
        for(int i=0; i<students.getLength(); i++) {
            System.out.println("id: " + IStudent[i].getId());
            System.out.println("sex: " + IStudent[i].getSex());
        }
    }
}
