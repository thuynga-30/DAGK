package Controller;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginAndRegisterServer {
    private ServerSocket serverSocket;
    private boolean isRunning = true;

    public static void main(String[] args) {
        LoginAndRegisterServer server = new LoginAndRegisterServer();
        server.startServer();
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(1234);
            System.out.println("Server is listening on port 1234");

            while (isRunning) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("New client connected");

                    new ClientHandler(socket).start();
                } catch (IOException e) {
                    if (isRunning) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (serverSocket != null && !serverSocket.isClosed()) {
                    serverSocket.close();
                    System.out.println("Server stopped.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void stopServer() {
        isRunning = false;
        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(password.getBytes());

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

    class ClientHandler extends Thread {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

                String action = input.readLine();

                // Process login request
                if ("login".equalsIgnoreCase(action)) {
                    String username = input.readLine();
                    String password = input.readLine();
                    handleLogin(username, password, output);
                } 
                // Process signup request
else if ("signup".equalsIgnoreCase(action)) {
                    String fullName = input.readLine();
                    String phoneNumber = input.readLine();
                    String password = input.readLine();
                    String rePassword = input.readLine();
                    boolean isApproved = Boolean.parseBoolean(input.readLine());
                    handleSignUp(fullName, phoneNumber, password, rePassword, isApproved, output);
                } 
                // Unknown action
                else {
                    output.println("Unknown action");
                    System.out.println("Unknown action: " + action);
                }
            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        private void handleLogin(String username, String password, PrintWriter output) throws SQLException, IOException, ClassNotFoundException {
            // JDBC connection details
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/Java";
            String userName = "postgres";
            String passw = "123";

            try (Connection con = DriverManager.getConnection(url, userName, passw)) {
                // Login handling
                String query = "SELECT * FROM public.\"users\" WHERE phonenumber = ? AND password = ?";
                try (PreparedStatement pstmt = con.prepareStatement(query)) {
                    pstmt.setString(1, username); 
                    pstmt.setString(2, hashPassword(password)); // Mã hóa mật khẩu trước khi so sánh

                    System.out.println("Executing query: " + pstmt.toString());

                    try (ResultSet rst = pstmt.executeQuery()) {
                        if (rst.next()) {
                            // Kiểm tra trạng thái duyệt của tài khoản
                            boolean isApproved = rst.getBoolean("is_approved");
                            if (isApproved) {
                                output.println("Login successful");
                                System.out.println("Login successful");
                            } else {
                                output.println("Your account is not approved yet. Please wait for approval.");
                                System.out.println("Account not approved");
                            }
                        } else {
                            output.println("Login failed");
                            System.out.println("Login failed");
                        }
                    }
                }
            }
        }



        private void handleSignUp(String fullName, String phoneNumber, String password, String rePassword, boolean isApproved, PrintWriter output) throws SQLException, IOException, ClassNotFoundException {
// JDBC connection details
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/Java";
            String userName = "postgres";
            String passw = "123";

            try (Connection con = DriverManager.getConnection(url, userName, passw)) {
                // SignUp handling
                String query = "INSERT INTO public.\"users\" (fullname, phonenumber, password, rerepass, is_approved) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = con.prepareStatement(query)) {
                    pstmt.setString(1, fullName);
                    pstmt.setString(2, phoneNumber);
                    pstmt.setString(3, hashPassword(password));
                    pstmt.setString(4, hashPassword(rePassword));
                    pstmt.setBoolean(5, isApproved);

                    System.out.println("Executing query: " + pstmt.toString());
                    int rowsAffected = pstmt.executeUpdate();

                    if (rowsAffected > 0) {
                        output.println("SignUp successful");
                        System.out.println("SignUp successful");
                    } else {
                        output.println("SignUp failed");
                        System.out.println("SignUp failed");
                    }
                }
            }
        }
    }
}