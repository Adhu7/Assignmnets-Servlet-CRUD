package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bean.User;


public class UserDAO {
	   private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testservlet";
	    private static final String JDBC_USER = "root";
	    private static final String JDBC_PASSWORD = "";

	    private Connection getConnection() throws SQLException {
	       
	        try {
	    		Class.forName("com.mysql.jdbc.Driver");
	    		}
	    		catch (Exception e) {
	    			System.out.println(e);
	    		}

	    		return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
	    	}
	    

	    public void addUser(User user) throws SQLException {
	        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
	        try (Connection conn = getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, user.getName());
	            stmt.setString(2, user.getEmail());
	            stmt.executeUpdate();
	        }
	    }

	    public User getUserById(int id) throws SQLException {
	        String sql = "SELECT * FROM users WHERE id = ?";
	        try (Connection conn = getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, id);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
	            }
	        }
	        return null;
	    }

	    public List<User> getAllUsers() throws SQLException {
	        List<User> users = new ArrayList<>();
	        String sql = "SELECT * FROM users";
	        try (Connection conn = getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                users.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("email")));
	            }
	        }
	        return users;
	    }

	    public void updateUser(User user) throws SQLException {
	        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
	        try (Connection conn = getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, user.getName());
	            stmt.setString(2, user.getEmail());
	            stmt.setInt(3, user.getId());
	            stmt.executeUpdate();
	        }
	    }

	    public void deleteUser(int id) throws SQLException {
	        String sql = "DELETE FROM users WHERE id = ?";
	        try (Connection conn = getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, id);
	            stmt.executeUpdate();
	        }
	    }

}
