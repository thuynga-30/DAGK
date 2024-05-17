package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLTableViewer extends JFrame {

    private JTable table;

    public XMLTableViewer(File xmlFile) {
        setTitle("XML Data Viewer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Tạo model cho bảng
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Fullname", "Year of Birth", "Address", "Phone Number"}
        );

        try {
            // Đọc dữ liệu từ tệp XML
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            Document doc = dbFactory.newDocumentBuilder().parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Lấy danh sách các nút <User> từ tệp XML
            NodeList userList = doc.getElementsByTagName("User");
            for (int i = 0; i < userList.getLength(); i++) {
                Element userElement = (Element) userList.item(i);
                String name = userElement.getElementsByTagName("Fullname").item(0).getTextContent();
                String yearOfBirth = userElement.getElementsByTagName("YearofBirth").item(0).getTextContent();
                String address = userElement.getElementsByTagName("Address").item(0).getTextContent();
                String phoneNumber = userElement.getElementsByTagName("Phonenumber").item(0).getTextContent();
                model.addRow(new Object[]{name, yearOfBirth, address, phoneNumber});
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading XML data", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Tạo bảng với model đã tạo
        table = new JTable(model);

        // Thêm bảng vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);

        // Thêm JScrollPane vào frame
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }
}

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            File xmlFile = new File("user.xml");
            new XMLTableViewer(xmlFile).setVisible(true);
        });
    }*/

