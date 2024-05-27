package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.URL;

public class SignUp extends JFrame {

    private JPanel contentPane;
    private JTextField phone;
    private JPasswordField pass;
    private JPasswordField repass;
    private JTextField fname;
    private JLabel statusLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SignUp frame = new SignUp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public SignUp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 846, 543);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel right = new JPanel();
        right.setLayout(null);
        right.setBackground(new Color(59, 178, 213));
        right.setBounds(0, 0, 388, 506);
        contentPane.add(right);

        JLabel lblNewLabel_1 = new JLabel("HEALTHTRACK");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Showcard Gothic", Font.BOLD, 35));
        lblNewLabel_1.setBounds(36, 262, 315, 92);
        right.add(lblNewLabel_1);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(SignUp.class.getResource("/Ảnh/snapedit_1710164001351.png")));
        lblNewLabel.setBounds(83, 61, 213, 172);
        right.add(lblNewLabel);

        // Correct the resource path
        

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(387, 0, 445, 506);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("SIGNUP");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setForeground(new Color(59, 178, 213));
        lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblNewLabel_2.setBounds(141, 10, 167, 49);
        panel.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Phone Number");
        lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3.setBounds(30, 177, 115, 26);
        panel.add(lblNewLabel_3);
        phone = new JTextField();
        phone.setColumns(10);
        phone.setBounds(30, 220, 388, 42);
        panel.add(phone);

        JLabel lblNewLabel_4 = new JLabel("Password");
        lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_4.setBounds(30, 272, 115, 26);
        panel.add(lblNewLabel_4);

        pass = new JPasswordField();
        pass.setBounds(30, 308, 388, 42);
        panel.add(pass);

        JLabel lblNewLabel_4_1 = new JLabel("Re-enter Password");
        lblNewLabel_4_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_4_1.setBounds(30, 360, 140, 26);
        panel.add(lblNewLabel_4_1);

        repass = new JPasswordField();
        repass.setBounds(30, 386, 388, 42);
        panel.add(repass);

        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fullName = fname.getText();
                String phoneNumber = phone.getText();
                String password = new String(pass.getPassword());
                String rePassword = new String(repass.getPassword()); 

                if (fullName.isEmpty() || phoneNumber.isEmpty() || phoneNumber.length() != 10 || password.isEmpty() || rePassword.isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "All fields are required and phone number must be 10 digits.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!password.equals(rePassword)) {
                    JOptionPane.showMessageDialog(new JFrame(), "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    sendRequest("signup", fullName, phoneNumber, password, rePassword, false);
                }
            }
        });
        btnSignUp.setForeground(Color.BLACK);
        btnSignUp.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSignUp.setBackground(new Color(0, 204, 255));
        btnSignUp.setBounds(161, 438, 98, 42);
        panel.add(btnSignUp);

        JButton btnNewButton_1 = new JButton("Log In");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LogIn logIn = new LogIn();
                logIn.setVisible(true);
                dispose();
            }
        });
        btnNewButton_1.setForeground(new Color(220, 20, 60));
        btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnNewButton_1.setBounds(161, 527, 98, 35);
        panel.add(btnNewButton_1);

        fname = new JTextField();
        fname.setColumns(10);
        fname.setBounds(30, 125, 388, 42);
        panel.add(fname);

        JLabel lblNewLabel_3_1 = new JLabel("Full Name");
        lblNewLabel_3_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNewLabel_3_1.setBounds(30, 82, 115, 26);
        panel.add(lblNewLabel_3_1);
        statusLabel = new JLabel("");
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        statusLabel.setForeground(Color.RED);
        statusLabel.setBounds(30, 480, 388, 26);
        panel.add(statusLabel);
    }

    private void sendRequest(String action, String fullName, String phoneNumber, String password, String rePassword, boolean isApproved) {
        try (Socket socket = new Socket("localhost", 1234);
             PrintWriter output =new PrintWriter(socket.getOutputStream(), true);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

               output.println(action);
               output.println(fullName);
               output.println(phoneNumber);
               output.println(password);
               output.println(rePassword);
               output.println(Boolean.toString(isApproved));

               String response = input.readLine();
               System.out.println("Received response: " + response);
               if (response.equals("SignUp successful")) {
                   JOptionPane.showMessageDialog(null, "Sign Up Successful! Please log in.");
                   LogIn logIn = new LogIn();
                   logIn.setVisible(true);
                   dispose();
               } else {
                   statusLabel.setText(response);
               }
           } catch (IOException ex) {
               ex.printStackTrace();
               statusLabel.setText("Error: " + ex.getMessage());
           }
       }
   }