package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.user;
import Database.JDBCUtil;

public class hoSoDAO implements DAOinterface<user> {
    
    public static hoSoDAO getInstance() {
        return new hoSoDAO();
    }

    @Override
    public int insert(user t) {
        // Not implemented
        return 0;
    }

    @Override
    public int update(user t) {
        // Not implemented
        return 0;
    }

    @Override
    public int delete(user t) {
        // Not implemented
        return 0;
    }

    @Override
    public ArrayList<user> selectAll() {
        ArrayList<user> ketqua = new ArrayList<>();
        
         
         

        try {
            // Establish a database connection
        	 Connection con = JDBCUtil.getConnection();

            // Create a statement object
        	  Statement st= con.createStatement();

            // Execute the SQL query
            String sql = "SELECT * FROM \"users\"";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);

            // Process the result set
            while (rs.next()) {
                String fullName = rs.getString("fullname");
                String phoneNumber = rs.getString("phonenumber");
                String password = rs.getString("password");
                String rePassword = rs.getString("rerepass");
                boolean isApproved = rs.getBoolean("is_approved");

                user u = new user(fullName, phoneNumber, password, rePassword, isApproved);
                ketqua.add(u);
                JDBCUtil.closeConnection(con);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
        }
        

        return ketqua;
    }

    @Override
    public user selectById(user t) {
        // Not implemented
        return null;
    }

    @Override
    public ArrayList<user> selectByCondition(String condition) {
        // Not implemented
        return null;
    }
    public boolean updateApprovalStatus(String fullName, String phoneNumber, boolean isApproved) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Java", "postgres", "123");
            String query = "UPDATE users SET is_approved = ? WHERE fullname = ? AND phonenumber = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setBoolean(1, isApproved);
            pstmt.setString(2, fullName);
            pstmt.setString(3, phoneNumber);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            // Handle any SQL errors
            ex.printStackTrace();
            return false;
        } finally {
// Close PreparedStatement and Connection
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}