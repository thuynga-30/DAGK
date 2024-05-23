package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.DataLengthException;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField phone;
	private JPasswordField pass;
	String driver = "org.postgresql.Driver";
	String url = "jdbc:postgresql://localhost:5432/Java";
	String username = "postgres";
	String password = "123";
	Connection con;
	Statement st;
	private JTextField fname;


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

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(SignUp.class.getResource("/Ảnh/snapedit_1710164001351.png")));
		lblNewLabel_5.setBounds(81, 100, 208, 168);
		right.add(lblNewLabel_5);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(387, 0, 445, 506);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("SIGNUP");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(59, 178, 213));
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 36));
		lblNewLabel_2.setBounds(158, 39, 167, 49);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("PhoneNumber");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_3.setBounds(30, 193, 115, 26);
		panel.add(lblNewLabel_3);

		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(30, 236, 388, 42);
		panel.add(phone);

		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_4.setBounds(30, 303, 115, 26);
		panel.add(lblNewLabel_4);

		pass = new JPasswordField();
		pass.setBounds(30, 339, 388, 42);
		panel.add(pass);

		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String phonenumber = phone.getText();
				String password = new String(pass.getPassword());
				if (phonenumber.isEmpty() && phonenumber.length()!=10) {
					JOptionPane.showMessageDialog(new JFrame(), "Phone Number is required", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				
				boolean isRegistered = registerUser(username, password);
		        if (isRegistered) {
		            JOptionPane.showMessageDialog(null, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		            JOptionPane.showMessageDialog(null, "Failed to register user. Please try again later.", "Registration Failed", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		btnSignUp.setForeground(Color.BLACK);
		btnSignUp.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnSignUp.setBackground(new Color(0, 204, 255));
		btnSignUp.setBounds(161, 397, 98, 42);
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
		btnNewButton_1.setBounds(161, 461, 98, 35);
		panel.add(btnNewButton_1);

		fname = new JTextField();
		fname.setColumns(10);
		fname.setBounds(30, 141, 388, 42);
		panel.add(fname);

		JLabel lblNewLabel_3_1 = new JLabel("FullName");
		lblNewLabel_3_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(30, 98, 115, 26);
		panel.add(lblNewLabel_3_1);
	}
	

    private boolean registerUser(String username, String password) {
        try (Socket socket = new Socket("192.168.1.23", 12345);
             OutputStream output = socket.getOutputStream();
             ObjectOutputStream objectOutput = new ObjectOutputStream(output);
             InputStream input = socket.getInputStream();
             ObjectInputStream objectInput = new ObjectInputStream(input)) {

            objectOutput.writeObject(new String[]{username, password});
            objectOutput.flush();

            return objectInput.readBoolean();

        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
