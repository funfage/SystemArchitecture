package cn.gdut.zrf;

import cn.gdut.zrf.entity.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {

    /**
     * 获取用户输入的学生信息
     *
     * @return 学生信息集合
     */
    private static List<Student> getStudentsInfo() {
        int num = 0;
        String sno;
        String sname;
        List<Student> studentList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入需要录入的学生信息的人数：");
        try {
            num = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("请输入阿拉伯数字");
        }
        System.out.println("输入的学生人数为：" + num + "人");
        for (int i = 0; i < num; i++) {
            System.out.println("请输入第" + (i + 1) + "个学生的信息：");
            System.out.print("学号：");
            sno = scanner.next();
            System.out.print("姓名：");
            sname = scanner.next();
            Student student = new Student(sno, sname);
            studentList.add(student);
        }
        System.out.println("所输入的学生信息为：");
        System.out.println(studentList);
        return studentList;
    }

    /**
     * 根据学生信息集合生成xml
     *
     * @param studentList 学生信息集合
     */
    private static void generateStuInfoToXml(List<Student> studentList) {
        // 1、创建一个SAXTransformerFactory类的对象
        SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        try {
            // 2、通过SAXTransformerFactory创建一个TransformerHandler的对象
            TransformerHandler handler = factory.newTransformerHandler();
            // 3、通过handler创建一个Transformer对象
            Transformer tr = handler.getTransformer();
            // 4、通过Transformer对象对生成的xml文件进行设置
            // 设置编码方式
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            // 设置是否换行
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            // 5、创建一个Result对象
            File file = new File("src/resources/studentsInfo.xml");
            //判断文件是否存在
            if (!file.exists()) {
                if (file.createNewFile()) {
                    throw new Exception("文件studentsInfo.xml创建失败");
                }
            }
            Result result = new StreamResult(new FileOutputStream(file));
            // 6、使RESULT与handler关联
            handler.setResult(result);
            // 打开document
            handler.startDocument();
            //创建起始的元素节点
            handler.startElement("", "", "studentList", null);
            for (Student stu : studentList
            ) {
                handler.startElement("", "", "student", null);

                handler.startElement("", "", "sno", null);
                handler.characters(stu.getSno().toCharArray(), 0, stu.getSno().length());
                handler.endElement("", "", "sno");

                handler.startElement("", "", "sname", null);
                handler.characters(stu.getSname().toCharArray(), 0, stu.getSname().length());
                handler.endElement("", "", "sname");

                handler.endElement("", "", "student");
            }
            handler.endElement("", "", "studentList");
            // 关闭document
            handler.endDocument();
            System.out.println("studentsInfo.xml成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从xml中读取学生信息
     */
    private static void readStuInfoFromXML() {
        System.out.println("从xml中读取学生信息：");
        File file = new File("src/resources/studentsInfo.xml");
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            System.out.println("----------------------------");
            NodeList nList = doc.getElementsByTagName("student");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                System.out.println("Node name: " + node.getNodeName());
                Element ele = (Element) node;
                if (node.getNodeType() == Element.ELEMENT_NODE) {
                    System.out.println("sno " + ele.getElementsByTagName("sno").item(0).getTextContent());
                    System.out.println("sname" + ele.getElementsByTagName("sname").item(0).getTextContent());
                    System.out.println("-------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        /*List<Student> studentsInfo = getStudentsInfo();
        generateStuInfoToXml(studentsInfo);*/
        readStuInfoFromXML();
    }
}
