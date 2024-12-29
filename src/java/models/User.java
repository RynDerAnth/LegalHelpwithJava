package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends Model<User> implements UserManagement {
    private int id;
    private String name;
    private String username;
    private String password;
    private String handphone;
    private String role;
    private double balance;

    public User() {
        this.table = "user";
        this.primaryKey = "id";
    }
    
    public User(int id, String name, String username, String password, String role, String hp, double balance) {
        this.table = "user";
        this.primaryKey = "id";
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.handphone = hp;
        this.balance = balance;
    }

    @Override
    public User toModel(ResultSet rs) {
        try {
            return new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("role"),
                rs.getString("handphone"),
                rs.getDouble("balance")
            );
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public double getBalance() {
        return balance;
    }

    public String getHandphone() {
        return handphone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void register(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = "user";
        this.balance = 0;
    }
    
    @Override
    public void login(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    
    @Override
    public void updateProfile(int id, String name, String hp) {
        this.id = id;
        this.name = name;
        this.handphone = hp;
    }
    
    public boolean isValidUser() {
        boolean result = false;
        String query = "SELECT COUNT(*) FROM " + table + " WHERE username = ? AND password = ?";
        
        
        try (Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/legalhelpwithjava","root","");
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setString(1, username);
            ps.setString(2, password);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    result = (count > 0);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public String roleCheck() {
        String result = null;
        String query = "SELECT role FROM " + table + " WHERE username = ? AND password = ?";
        
        
        try (Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/legalhelpwithjava","root","");
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setString(1, username);
            ps.setString(2, password);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = rs.getString("role");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
