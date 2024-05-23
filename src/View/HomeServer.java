package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class HomeServer extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JPanel userPanel;
	private JPanel createPanel;
	private JPanel contactPanel;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeServer frame = new HomeServer();
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
	public HomeServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 1470, 830);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel Menu = new JPanel();
		Menu.setLayout(null);
		Menu.setBackground(new Color(59, 178, 213));
		Menu.setBounds(0, 0, 312, 871);
		contentPane.add(Menu);
		
		JButton btnContact = new JButton("CONTACT");
		btnContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userPanel.setVisible(false);
				createPanel.setVisible(false);
				contactPanel.setVisible(true);
				
			}
		});
		btnContact.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnContact.setBackground(Color.WHITE);
		btnContact.setBounds(62, 581, 188, 59);
		Menu.add(btnContact);
		
		JButton btnUser = new JButton("USER");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userPanel.setVisible(true);
				createPanel.setVisible(false);
				contactPanel.setVisible(false);

			}
		});
		btnUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnUser.setBackground(Color.WHITE);
		btnUser.setBounds(62, 387, 188, 59);
		Menu.add(btnUser);
		
		JButton btnPhr_1 = new JButton("");
		btnPhr_1.setIcon(new ImageIcon(HomeServer.class.getResource("/Ảnh/snapedit_1710344305319.png")));
		btnPhr_1.setBackground(new Color(89, 191, 240));
		btnPhr_1.setBounds(62, 717, 67, 71);
		Menu.add(btnPhr_1);
		
		JLabel close = new JLabel("");
		close.setIcon(new ImageIcon(HomeServer.class.getResource("/Ảnh/snapedit_1711641684894.png")));
		close.setBounds(265, 0, 45, 42);
		Menu.add(close);
		
		JLabel lblNewLabel_4 = new JLabel("HEALTHTRACK");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Showcard Gothic", Font.BOLD, 35));
		lblNewLabel_4.setBounds(0, 220, 315, 82);
		Menu.add(lblNewLabel_4);
		
		JLabel lblNewLabel = new JLabel("EXIT");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(151, 745, 61, 27);
		Menu.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(HomeServer.class.getResource("/Ảnh/snapedit_1710164001351.png")));
		lblNewLabel_1.setBounds(47, 20, 208, 171);
		Menu.add(lblNewLabel_1);
		
		JButton btnCreateNewAccout = new JButton("CREATE ACCOUNT");
		btnCreateNewAccout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userPanel.setVisible(false);
				createPanel.setVisible(true);
				contactPanel.setVisible(false);

			}
		});
		btnCreateNewAccout.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnCreateNewAccout.setBackground(Color.WHITE);
		btnCreateNewAccout.setBounds(62, 482, 188, 59);
		Menu.add(btnCreateNewAccout);
		
		JPanel panel = new JPanel();
		panel.setBounds(310, 0, 1238, 890);
		contentPane.add(panel);
		panel.setLayout(new CardLayout(0, 0));
		userPanel = new JPanel();
		userPanel.setBackground(new Color(255, 255, 255));
		panel.add(userPanel, "name_435772619182000");
		userPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1270, 846);
		userPanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Full Name", "Phonenumber", "Password"
			}
		));
		scrollPane.setViewportView(table);
		
		createPanel = new JPanel();
		createPanel.setBackground(new Color(255, 255, 255));
		panel.add(createPanel, "name_436108357530900");
		createPanel.setLayout(null);
		
		JLabel lblNewLabel_3_1 = new JLabel("FullName");
		lblNewLabel_3_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(211, 215, 609, 26);
		createPanel.add(lblNewLabel_3_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(356, 210, 464, 42);
		createPanel.add(textField);
		
		JLabel lblNewLabel_3 = new JLabel("PhoneNumber");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_3.setBounds(211, 310, 609, 26);
		createPanel.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(356, 305, 464, 42);
		createPanel.add(textField_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("Password");
		lblNewLabel_4_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_4_1.setBounds(211, 413, 609, 26);
		createPanel.add(lblNewLabel_4_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(356, 408, 464, 42);
		createPanel.add(passwordField);
		
		JButton btnCreate = new JButton("CREATE");
		btnCreate.setForeground(Color.BLACK);
		btnCreate.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnCreate.setBackground(new Color(0, 204, 255));
		btnCreate.setBounds(523, 499, 98, 42);
		createPanel.add(btnCreate);
		
		JLabel lblNewLabel_2_1 = new JLabel("SIGNUP");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(new Color(59, 178, 213));
		lblNewLabel_2_1.setFont(new Font("Segoe UI", Font.BOLD, 36));
		lblNewLabel_2_1.setBounds(450, 47, 167, 49);
		createPanel.add(lblNewLabel_2_1);
		
		contactPanel = new JPanel();
		panel.add(contactPanel, "name_436607498136800");
		contactPanel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 29, 1239, 673);
		contactPanel.add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		textField_2 = new JTextField();
		textField_2.setBounds(0, 712, 989, 68);
		contactPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("SEND");
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton.setBounds(1007, 712, 140, 68);
		contactPanel.add(btnNewButton);
	}
}
