package View;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class LogIn extends JFrame {

	private JPanel contentPane;
	private JTextField phonenumber;
	private JPasswordField passwordField;
	String driver ="org.postgresql.Driver";
    String url = "jdbc:postgresql://localhost:5432/Java" ;
    String username= "postgres" ;	
    String password = "123";
    Connection con ;
    ResultSet rs;
    Statement st;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn frame = new LogIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogIn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 846, 543);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel right = new JPanel();
		right.setBounds(0, 0, 388, 506);
		right.setBackground(new Color(59, 178, 213));
		contentPane.add(right);
		right.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("HEALTHTRACK");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Showcard Gothic", Font.BOLD, 35));
		lblNewLabel_1.setBounds(36, 262, 315, 92);
		right.add(lblNewLabel_1);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(LogIn.class.getResource("/Ảnh/snapedit_1710164001351.png")));
		lblNewLabel_5.setBounds(81, 100, 208, 168);
		right.add(lblNewLabel_5);
		
		JPanel left = new JPanel();
		left.setBackground(new Color(255, 255, 255));
		left.setBounds(383, 0, 448, 506);
		contentPane.add(left);
		left.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("LOGIN");
		lblNewLabel_2.setForeground(new Color(59, 178, 213));
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 36));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(157, 56, 167, 49);
		left.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("PhoneNumber");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_3.setBounds(31, 146, 115, 26);
		left.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_4.setBounds(31, 263, 115, 26);
		left.add(lblNewLabel_4);
		
		phonenumber = new JTextField();
		phonenumber.setBounds(31, 189, 388, 42);
		left.add(phonenumber);
		phonenumber.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(31, 299, 388, 42);
		left.add(passwordField);
		
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String phone= phonenumber.getText().trim();
				String pass = new String(passwordField.getPassword());
				if (phone.isEmpty() || pass.isEmpty()) {
					 JOptionPane.showMessageDialog(null, "Username and Password cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			            return;			
			      }

		        boolean isLoggedIn = validateLogin(username, password);
		        if (isLoggedIn) {
		            JOptionPane.showMessageDialog(null, "Logged in successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
		            new Home().setVisible(true);
		            dispose();
		        } else {
	                JOptionPane.showMessageDialog(null, "Incorrect username or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
	            }
            }
			
		});
		btnNewButton.setBackground(new Color(0, 204, 255));
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(157, 363, 98, 49);
		left.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sign Up");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUp signUp = new SignUp();
				signUp.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setForeground(new Color(220, 20, 60));
		btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnNewButton_1.setBounds(157, 434, 98, 35);
		left.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(-128, 6, 570, 531);
		left.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(59, 178, 213));
		lblNewLabel.setIcon(new ImageIcon("/Users/mac/Downloads/46-1.png"));
	}
	private boolean validateLogin(String username, String password) {
        try (Socket socket = new Socket("192.168.1.23", 12345);
             OutputStream output = socket.getOutputStream();
             ObjectOutputStream objectOutput = new ObjectOutputStream(output);
             InputStream input = socket.getInputStream();
             ObjectInputStream objectInput = new ObjectInputStream(input)) {

            objectOutput.writeObject(new String[]{"login", username, password});
            objectOutput.flush();

            return objectInput.readBoolean();

        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
	 
}
