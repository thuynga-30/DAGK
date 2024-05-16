package Controller;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatServer extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private JLabel nameLabel;
    private PrintWriter out;
    private String userName;
    public ChatServer() {
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
                chatArea.append("You: " + message + "\n");
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

        // Thiết lập kết nối
        
        new Thread(new Runnable() {
            public void run() {
                try (
                	ServerSocket serverSocket = new ServerSocket(12345)) {
                    chatArea.append("Server đang lắng nghe tại cổng 12345...\n");
                    Socket clientSocket = serverSocket.accept();
                    chatArea.append("Đã kết nối với " + clientSocket.getInetAddress() + "\n");

                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    inputField.setEditable(true);
                    userName = in.readLine();
                    System.out.println(userName + " has joined the chat.");
                    String clientMessage;
                    while ((clientMessage = in.readLine()) != null) {
                        chatArea.append(userName+":" + clientMessage + "\n");
                    }
                } catch (IOException e) {
                    chatArea.append("Lỗi: " + e.getMessage() + "\n");
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        new ChatServer();
    }
}