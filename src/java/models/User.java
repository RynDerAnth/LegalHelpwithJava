package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class User extends Model<User> implements UserManagement {
    private int id;
    private String name;
    private String username;
    private String password;
    private String handphone;
    private String role;
    private double balance;
    private String profile_path;

    public User() {
        this.table = "user";
        this.primaryKey = "id";
    }
    
    public User(int id, String name, String username, String password, String role, String hp, double balance, String profile_path) {
        this.table = "user";
        this.primaryKey = "id";
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.handphone = hp;
        this.balance = balance;
        this.profile_path = profile_path;

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
                rs.getDouble("balance"),
                rs.getString("profile_path")
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
    
    public String getProfile_path() {
        return profile_path;
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
        this.handphone = "- ";
        this.balance = 0;
        this.profile_path = " - ";
    }
    
    @Override
    public void login(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    
    @Override
    public void updateProfile(int id,String username, String name, String hp, String profile_path) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.handphone = hp;
        this.profile_path = profile_path;
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
    
    public int userCount() {
        int count = 0;
        String query = "SELECT COUNT(*) AS total FROM " + table + " WHERE role = 'user'";
        try (Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/legalhelpwithjava","root","")) {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(query);
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public User find(String column, Object value) {
    User user = null;
    String query = "SELECT * FROM " + this.table + " WHERE " + column + " = ? LIMIT 1";
    
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/legalhelpwithjava", "root", "");
         PreparedStatement stmt = con.prepareStatement(query)) {
         
        stmt.setObject(1, value); // Menggunakan parameterized query untuk mencegah SQL Injection
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            user = this.toModel(rs); // Gunakan metode `toModel` untuk memetakan hasil query
        }
        
    } catch (SQLException e) {
        System.out.println("Error during find operation: " + e.getMessage());
    }
    
    return user;
    }
}
