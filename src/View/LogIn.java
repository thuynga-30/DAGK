package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class LogIn extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    private JLabel statusLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LogIn frame = new LogIn();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LogIn() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 846, 543);
        setTitle("Login");

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(0, 0, 388, 506);
        rightPanel.setBackground(new Color(59, 178, 213));
        contentPane.add(rightPanel);
        rightPanel.setLayout(null);

        JLabel healthTrackLabel = new JLabel("HEALTHTRACK");
        healthTrackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        healthTrackLabel.setForeground(new Color(255, 255, 255));
        healthTrackLabel.setFont(new Font("Showcard Gothic", Font.BOLD, 35));
        healthTrackLabel.setBounds(36, 262, 315, 92);
        rightPanel.add(healthTrackLabel);

        // Corrected image resource loading for right panel
        JLabel rightPanelImageLabel = new JLabel("");
        rightPanelImageLabel.setIcon(new ImageIcon(LogIn.class.getResource("/Ảnh/snapedit_1710164001351.png")));
        URL rightPanelImageUrl = getClass().getResource("/resources/snapedit_1710164001351.png");
        if (rightPanelImageUrl != null) {
            rightPanelImageLabel.setIcon(new ImageIcon(rightPanelImageUrl));
        } else {
            System.err.println("Resource not found: /resources/snapedit_1710164001351.png");
        }
        rightPanelImageLabel.setBounds(81, 100, 208, 168);
        rightPanel.add(rightPanelImageLabel);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(255, 255, 255));
        leftPanel.setBounds(383, 0, 448, 506);
        contentPane.add(leftPanel);
        leftPanel.setLayout(null);

        JLabel loginLabel = new JLabel("LOGIN");
        loginLabel.setForeground(new Color(59, 178, 213));
        loginLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setBounds(157, 56, 167, 49);
        leftPanel.add(loginLabel);

        JLabel phoneNumberLabel = new JLabel("PhoneNumber");
        phoneNumberLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        phoneNumberLabel.setBounds(31, 146, 115, 26);
        leftPanel.add(phoneNumberLabel);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        passwordLabel.setBounds(31, 263, 115, 26);
        leftPanel.add(passwordLabel);

        textField = new JTextField();
        textField.setBounds(31, 189, 388, 42);
        leftPanel.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(31, 299, 388, 42);
        leftPanel.add(passwordField);

        JButton loginButton = new JButton("Log In");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                String password = new String(passwordField.getPassword());
                sendRequest("login", username, password);
            }
        });
        loginButton.setBackground(new Color(0, 204, 255));
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setForeground(Color.BLACK);
        loginButton.setBounds(157, 363, 98, 49);
        leftPanel.add(loginButton);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SignUp signUp = new SignUp();
                signUp.setVisible(true);
                dispose();
            }
        });
        signUpButton.setForeground(new Color(220, 20, 60));
        signUpButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        signUpButton.setBounds(157, 434, 98, 35);
        leftPanel.add(signUpButton);
        

        statusLabel = new JLabel("");
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        statusLabel.setForeground(Color.RED);
        statusLabel.setBounds(10, 363, 128, 26);
        leftPanel.add(statusLabel);
    }

    private void sendRequest(String action, String username, String password) {
        try (Socket socket = new Socket("localhost", 1234);
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Sending action: " + action);
            System.out.println("Sending username: " + username);
            System.out.println("Sending password: " + password);

            output.println(action);
            output.println(username);
            output.println(password);

            String response = input.readLine();
            System.out.println("Received response: " + response);
            if (textField.getText().equals("") || new String(passwordField.getPassword()).equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter both username and password.");
            } else if (response.equals("Login successful")) {
                EventQueue.invokeLater(() -> {
                    Home sw = new Home();
                    sw.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    sw.setVisible(true);
                    dispose();
                });
            } else {
                statusLabel.setText(response);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            statusLabel.setText("Error: " + ex.getMessage());
        }
    }
}