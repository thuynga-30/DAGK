package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import Controller.LoginAndRegisterServer;
import Controller.hoSoDAO;
import Model.user;

public class HomeServer extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JTextField nameTxt;
    private JTextField phoneTxt;
    private JPasswordField passwordField;
    private JPanel userPanel;
    private JPanel createPanel;
    private JPanel contactPanel;
    private JTextField textField;
    private JTextArea textArea;
    private PrintWriter out;
    private BufferedReader in;
    private Socket socket;
    private LoginAndRegisterServer server;
    private JPasswordField passwordField_1;
    private String userName;

    public static void main(String[] args) {
        new Thread(() -> {
            LoginAndRegisterServer server = new LoginAndRegisterServer();
            server.startServer();
        }).start();

        EventQueue.invokeLater(() -> {
            try {
                HomeServer frame = new HomeServer();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public HomeServer() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setBounds(100, 100, 1470, 830);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopServer();
            }
        });

        JPanel menu = new JPanel();
        menu.setLayout(null);
        menu.setBackground(new Color(59, 178, 213));
        menu.setBounds(0, 0, 312, 871);
        contentPane.add(menu);

        JButton btnContact = new JButton("CONTACT");
        btnContact.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 userPanel.setVisible(false);
                 createPanel.setVisible(false);
                 contactPanel.setVisible(true);
        		new Thread(new Runnable() {
                    public void run() {
                        try (
                        	ServerSocket serverSocket = new ServerSocket(12345)) {
                           // textArea.append("Server đang lắng nghe tại cổng 12345...\n");
                            Socket clientSocket = serverSocket.accept();
                         

                            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                            out = new PrintWriter(clientSocket.getOutputStream(), true);
                            textField.setEditable(true);
                            userName = in.readLine();
                            System.out.println(userName + " has joined the chat.");
                            String clientMessage;
                            while ((clientMessage = in.readLine()) != null) {
                                textArea.append("Doctor:" + clientMessage + "\n");
                            }
                        } catch (IOException e) {
                            textArea.append("Lỗi: " + e.getMessage() + "\n");
                        }
                    }
                }).start();
        	}
        });
       /* btnContact.addActionListener(e -> {
            userPanel.setVisible(false);
            createPanel.setVisible(false);
            contactPanel.setVisible(true);
            startServerCommunication();
        });*/
        btnContact.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnContact.setBackground(Color.WHITE);
        btnContact.setBounds(62, 581, 188, 59);
        menu.add(btnContact);

        JButton btnUser = new JButton("USER");
        btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	 
			}
        	
        });
        btnUser.addActionListener(e -> {
            userPanel.setVisible(true);
            createPanel.setVisible(false);
            contactPanel.setVisible(false);
        });
        btnUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnUser.setBackground(Color.WHITE);
        btnUser.setBounds(62, 387, 188, 59);
        menu.add(btnUser);

        JButton btnPhr_1 = new JButton("");
        btnPhr_1.setIcon(new ImageIcon(HomeServer.class.getResource("/Ảnh/snapedit_1710344305319.png")));
        btnPhr_1.setBackground(new Color(89, 191, 240));
        btnPhr_1.setBounds(62, 717, 67, 71);
        menu.add(btnPhr_1);

        JLabel close = new JLabel("");
        close.setIcon(new ImageIcon(HomeServer.class.getResource("/Ảnh/snapedit_1711641684894.png")));
        close.setBounds(265, 0, 45, 42);
        menu.add(close);

        JLabel lblNewLabel_4 = new JLabel("HEALTHTRACK");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setForeground(Color.WHITE);
        lblNewLabel_4.setFont(new Font("Showcard Gothic", Font.BOLD, 35));
        lblNewLabel_4.setBounds(0, 220, 315, 82);
        menu.add(lblNewLabel_4);

        JLabel lblNewLabel = new JLabel("EXIT");
        lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblNewLabel.setBounds(151, 745, 61, 27);
        menu.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(HomeServer.class.getResource("/Ảnh/snapedit_1710164001351.png")));
        lblNewLabel_1.setBounds(47, 20, 208, 171);
        menu.add(lblNewLabel_1);

        JButton btnCreateNewAccount = new JButton("CREATE ACCOUNT");
     
        btnCreateNewAccount.addActionListener(e -> {
            userPanel.setVisible(false);
            createPanel.setVisible(true);
            contactPanel.setVisible(false);
        });
        btnCreateNewAccount.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnCreateNewAccount.setBackground(Color.WHITE);
        btnCreateNewAccount.setBounds(62, 482, 188, 59);
        menu.add(btnCreateNewAccount);

        JPanel panel = new JPanel();
        panel.setBounds(310, 0, 1238, 890);
        contentPane.add(panel);
        panel.setLayout(new CardLayout(0, 0));
        userPanel = new JPanel();
        userPanel.setBackground(new Color(255, 255, 255));
        panel.add(userPanel, "name_435772619182000");
        userPanel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 1270, 592);
        userPanel.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Full Name", "Phone Number", "Password", "Re-entered Password", "Approved"
            }
        ));
        scrollPane.setViewportView(table);
        
        JButton btnRefresh = new JButton("Refresh");
btnRefresh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fetchDataAndUpdateTable();
        	}
        });
        btnRefresh.setFont(new Font("Dialog", Font.PLAIN, 14));
        btnRefresh.setBackground(Color.WHITE);
        btnRefresh.setBounds(891, 643, 188, 59);
        userPanel.add(btnRefresh);
        fetchDataAndUpdateTable(); // Fetch and display data from the database
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Lấy chỉ số hàng được chọn
                int selectedRow = table.getSelectedRow();

                // Kiểm tra xem có hàng nào được chọn không
                if (selectedRow != -1) {
                    // Lấy dữ liệu từ hàng được chọn
                    String fullName = (String) table.getValueAt(selectedRow, 0);
                    String phoneNumber = (String) table.getValueAt(selectedRow, 1);

                    // Hiển thị cửa sổ xác nhận cập nhật
                    int choice = JOptionPane.showConfirmDialog(HomeServer.this, "Do you want to approve this user?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        // Cập nhật trạng thái phê duyệt trong cơ sở dữ liệu
                        boolean success = hoSoDAO.getInstance().updateApprovalStatus(fullName, phoneNumber, true);

                        if (success) {
                            // Cập nhật thành công, thông báo cho người dùng
                            JOptionPane.showMessageDialog(HomeServer.this, "Approval status updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                            // Cập nhật lại bảng hiển thị
                            fetchDataAndUpdateTable();
                        } else {
                            // Xảy ra lỗi khi cập nhật, thông báo cho người dùng
                            JOptionPane.showMessageDialog(HomeServer.this, "Failed to update approval status.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
    


        createPanel = new JPanel();
        createPanel.setBackground(new Color(255, 255, 255));
        panel.add(createPanel, "name_436108357530900");
        createPanel.setLayout(null); 

        JLabel fname = new JLabel("FullName");
        fname.setFont(new Font("Segoe UI", Font.BOLD, 14));
        fname.setBounds(211, 215, 609, 26);
        createPanel.add(fname);

        nameTxt = new JTextField();
        nameTxt.setColumns(10);
        nameTxt.setBounds(356, 210, 464, 42);
        createPanel.add(nameTxt);

        JLabel fnumber = new JLabel("PhoneNumber");
        fnumber.setFont(new Font("Segoe UI", Font.BOLD, 14));
        fnumber.setBounds(211, 310, 609, 26);
        createPanel.add(fnumber);

        phoneTxt = new JTextField();
phoneTxt.setColumns(10);
        phoneTxt.setBounds(356, 305, 464, 42);
        createPanel.add(phoneTxt);

        JLabel fpass = new JLabel("Password");
        fpass.setFont(new Font("Segoe UI", Font.BOLD, 14));
        fpass.setBounds(211, 413, 609, 26);
        createPanel.add(fpass);

        passwordField = new JPasswordField();
        passwordField.setBounds(356, 406, 464, 42);
        createPanel.add(passwordField);

        JButton btnCreate = new JButton("CREATE");
        btnCreate.addActionListener(new ActionListener() {
  
        	    public void actionPerformed(ActionEvent e) {
        	        String fullName = nameTxt.getText();
        	        String phoneNumber = phoneTxt.getText();
        	        String password = new String(passwordField.getPassword());
        	        String rePassword = new String(passwordField_1.getPassword());
        	        
        	        if (!password.equals(rePassword)) {
        	            // Handle password mismatch error
        	            System.out.println("Passwords do not match");
        	            return;
        	        }

        	       
        	        String hashedPassword = hashPassword(password);
        	        String hashedPasswordd = hashPassword(rePassword);
        	        

        	        // Perform database operations here
        	        try {
        	            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Java", "postgres", "123");
        	            String query = "INSERT INTO users (fullname, phonenumber, password, rerepass, is_approved) VALUES (?, ?, ?, ?, true)";
        	            PreparedStatement pstmt = con.prepareStatement(query);
        	            pstmt.setString(1, fullName);
        	            pstmt.setString(2, phoneNumber);
        	            pstmt.setString(3, hashedPassword);
        	            pstmt.setString(4, hashedPasswordd);
        	           
        	            int rowsAffected = pstmt.executeUpdate();

        	            if (rowsAffected > 0) {
        	                // Handle successful insertion
        	                System.out.println("User inserted successfully");
        	                JOptionPane.showMessageDialog(null, "Registration successful", "Success", JOptionPane.INFORMATION_MESSAGE);
        	            } else {
        	                // Handle insertion failure
        	                System.out.println("Failed to insert user");
        	                JOptionPane.showMessageDialog(null, "Registration failed", "Error", JOptionPane.ERROR_MESSAGE);
        	            }

        	            pstmt.close();
        	            con.close();
        	        } catch (SQLException ex) {
        	            // Handle any SQL errors
        	            ex.printStackTrace();
        	        }
        	        nameTxt.setText("");
        	        phoneTxt.setText("");
        	        passwordField.setText("");
        	        passwordField_1.setText("");
        	        
        	    }
        	});


        btnCreate.addActionListener(e -> {
            String fullName = nameTxt.getText();
            String phoneNumber = phoneTxt.getText();
            String password = new String(passwordField.getPassword());
            String hashedPassword = hashPassword(password);
            boolean isApproved = true;

            if (out != null) {
                out.println("signup");
                out.println(fullName);
                out.println(phoneNumber);
                out.println(hashedPassword);
                out.println(hashedPassword);
                out.println(isApproved);
            }
        });
        btnCreate.setForeground(Color.BLACK);
        btnCreate.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCreate.setBackground(new Color(0, 204, 255));
        btnCreate.setBounds(519, 552, 98, 42);
        createPanel.add(btnCreate);

        JLabel lblNewLabel_2_1 = new JLabel("SIGNUP");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setForeground(new Color(59, 178, 213));
        lblNewLabel_2_1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblNewLabel_2_1.setBounds(450, 47, 167, 49);
        createPanel.add(lblNewLabel_2_1);
        
        JLabel frepass = new JLabel("rePassword");
        frepass.setFont(new Font("Dialog", Font.BOLD, 14));
        frepass.setBounds(211, 499, 609, 26);
        createPanel.add(frepass);
        
        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(356, 483, 464, 42);
        createPanel.add(passwordField_1);

        contactPanel = new JPanel();
        panel.add(contactPanel, "name_436607498136800");
        contactPanel.setLayout(null);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(0, 0, 1239, 673);
        contactPanel.add(scrollPane_1);

        textArea = new JTextArea();
        scrollPane_1.setViewportView(textArea);

        textField = new JTextField();
        textField.setBounds(0, 712, 989, 68);
        contactPanel.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("SEND");
        btnNewButton.addActionListener(e -> {
        	String message = textField.getText();
            textArea.append("You: " + message + "\n");
            out.println(message);
            textField.setText("");
        });
        btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnNewButton.setBounds(1007, 712, 140, 68);
        contactPanel.add(btnNewButton);
    }
 
	  
/*  private void startServerCommunication() {
        new Thread(() -> {
            try {
                socket = new Socket("192.168.32.101", 1234);
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                textArea.append("Connected to server\n");

                String serverMessage;
                while ((serverMessage = in.readLine()) != null) {
textArea.append("Server: " + serverMessage + "\n");
                }
            } catch (IOException e) {
                textArea.append("Error: " + e.getMessage() + "\n");
            }
        }).start();
    }*/
  
    private void stopServer() {
        try {
            if (socket != null) {
                socket.close();
            }
            if (server != null) {
                server.stopServer();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    private void fetchDataAndUpdateTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing data in the table

        // Fetch data from the database using hoSoDAO
        ArrayList<user> userList = hoSoDAO.getInstance().selectAll();

        // Add fetched data to the table model
        for (user u : userList) {
            Object[] rowData = {
                    u.getFullName(),
                    u.getPhoneNumber(),
                    u.getPassWord(),
                    u.getPassWord(),
                    u.isIs_approved() ? "Approved" : "Not Approved" // Assuming isApproved is a boolean field
            };
            model.addRow(rowData);
        }

        // Refresh the table to reflect changes
        table.revalidate();
        table.repaint();
    }
}