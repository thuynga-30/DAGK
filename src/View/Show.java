package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.ChatServer;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Show extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Show() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 514, 169);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(216, 243, 252));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WHO ARE YOU?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel.setBounds(152, 10, 170, 27);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("ADMIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                      ChatServer chatServer = new ChatServer();
                      chatServer.setVisible(true);
                    }
        		});
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(59, 178, 213));
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNewButton.setBounds(103, 65, 89, 38);
		contentPane.add(btnNewButton);
		
		JButton btnAdmin = new JButton("CLIENT");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                      String IP="192.168.1.8";
                      ChatClient chatClient= new ChatClient(IP);
                      chatClient.setVisible(true);
                      
                    }
        		});
				dispose();
			}
		});
		btnAdmin.setBackground(new Color(59, 178, 213));
		btnAdmin.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnAdmin.setBounds(278, 65, 89, 38);
		contentPane.add(btnAdmin);
	}
}
