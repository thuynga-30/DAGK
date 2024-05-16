package View;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatClient extends JFrame {
	private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private JLabel nameLabel;
    private PrintWriter out;
    private String userName; // Thêm biến userName để lưu tên người dùng

    public ChatClient(String serverAddress) {
        // Thiết lập giao diện
    	setTitle("Chat Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
       
        nameLabel = new JLabel();
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(nameLabel, BorderLayout.NORTH);
        
        chatArea = new JTextArea(20, 50);
        chatArea.setEditable(false);
        
        inputField = new JTextField(30);
        
        sendButton = new JButton("Send");


        inputField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = inputField.getText();
                chatArea.append("You : " + message + "\n"); // Thêm userName vào tin nhắn
                out.println(message);
                inputField.setText("");
            }
        });
        JPanel panel = new JPanel();
        panel.add(inputField);
        panel.add(sendButton);

        add(new JScrollPane(chatArea), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        setVisible(true);
        

        // Nhập tên người dùng
        userName = JOptionPane.showInputDialog(this, "Nhập tên của bạn:");

        // Thiết lập kết nối
        new Thread(new Runnable() {
            public void run() {
                try {
                	Socket socket = new Socket(serverAddress, 12345);
                	out = new PrintWriter(socket.getOutputStream(), true);
                	out.println(userName);
                    chatArea.append("Đã kết nối tới " + serverAddress + " tại cổng 12345\n");
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                   

                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        chatArea.append("Admin: " + serverMessage + "\n");
                    }
                } catch (IOException e) {
                    chatArea.append("Lỗi: " + e.getMessage() + "\n");
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        String serverAddress = JOptionPane.showInputDialog(
                "Nhập địa chỉ IP của máy chủ:");
        new ChatClient(serverAddress);
    }
}
