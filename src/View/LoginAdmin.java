package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class LoginAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginAdmin frame = new LoginAdmin();
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
	public LoginAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 838, 537);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(null);
		rightPanel.setBackground(new Color(59, 178, 213));
		rightPanel.setBounds(0, 0, 388, 506);
		contentPane.add(rightPanel);
		
		JLabel healthTrackLabel = new JLabel("HEALTHTRACK");
		healthTrackLabel.setHorizontalAlignment(SwingConstants.CENTER);
		healthTrackLabel.setForeground(Color.WHITE);
		healthTrackLabel.setFont(new Font("Showcard Gothic", Font.BOLD, 35));
		healthTrackLabel.setBounds(36, 262, 315, 92);
		rightPanel.add(healthTrackLabel);
		
		JLabel rightPanelImageLabel = new JLabel("");
		rightPanelImageLabel.setIcon(new ImageIcon(LoginAdmin.class.getResource("/Ảnh/snapedit_1710164001351.png")));
		rightPanelImageLabel.setBounds(81, 100, 208, 168);
		rightPanel.add(rightPanelImageLabel);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(null);
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setBounds(383, 0, 448, 506);
		contentPane.add(leftPanel);
		
		JLabel loginLabel = new JLabel("LOGIN");
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setForeground(new Color(59, 178, 213));
		loginLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
		loginLabel.setBounds(157, 56, 167, 49);
		leftPanel.add(loginLabel);
		
		JLabel phoneNumberLabel = new JLabel("UserName");
		phoneNumberLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		phoneNumberLabel.setBounds(31, 146, 115, 26);
		leftPanel.add(phoneNumberLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		passwordLabel.setBounds(31, 263, 115, 26);
		leftPanel.add(passwordLabel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(31, 189, 388, 42);
		leftPanel.add(textField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(31, 299, 388, 42);
		leftPanel.add(passwordField);
		
		JButton loginButton = new JButton("Log In");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(passwordField.getPassword());
				String userString =textField.getText();
				if ("Admin".equals(userString) && "123123".equals(password)) {
					HomeServer homeServer = new HomeServer();
					homeServer.setVisible(true);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Login Failed");
				}
			}
		});
		loginButton.setForeground(Color.BLACK);
		loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		loginButton.setBackground(new Color(0, 204, 255));
		loginButton.setBounds(162, 394, 98, 49);
		leftPanel.add(loginButton);
		
		JLabel statusLabel = new JLabel("");
		statusLabel.setForeground(Color.RED);
		statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		statusLabel.setBounds(10, 363, 128, 26);
		leftPanel.add(statusLabel);
	}

}
