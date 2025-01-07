package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class Konsultan extends Model<Konsultan> {
    private int id;
    private String name;
    private double price;
    private String bio;
    private String address;
    private String profile_path;

    public Konsultan() {
        this.table = "consultant";
        this.primaryKey = "id";
    }
    
    public Konsultan(int id, String name, double price, String bio, String address, String profile_path) {
        this.table = "consultant";
        this.primaryKey = "id";
        this.id = id;
        this.name = name;
        this.price = price;
        this.bio = bio;
        this.address = address;
        this.profile_path = profile_path;
    }

    @Override
    public Konsultan toModel(ResultSet rs) {
        try {
            return new Konsultan(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getString("bio"),
                rs.getString("address"),
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

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }
    
    public int konsultanCount() {
        int count = 0;
        String query = "SELECT COUNT(*) AS total FROM " + table + " WHERE id > 1";
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
    
    public ArrayList<Konsultan> showAll() {
        ArrayList<Konsultan> cons = new ArrayList<>();
        String query = "SELECT * FROM " + table + " WHERE id > 1";
        try (Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/legalhelpwithjava","root","")) {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                cons.add(toModel(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cons;
    }
    
    public Konsultan find(String column, Object value) {
    Konsultan consultant = null;
    String query = "SELECT * FROM " + this.table + " WHERE " + column + " = ? LIMIT 1";
    
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/legalhelpwithjava", "root", "");
         PreparedStatement stmt = con.prepareStatement(query)) {
         
        stmt.setObject(1, value); // Menggunakan parameterized query untuk mencegah SQL Injection
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            consultant = this.toModel(rs); // Gunakan metode `toModel` untuk memetakan hasil query
        }
        
    } catch (SQLException e) {
        System.out.println("Error during find operation: " + e.getMessage());
    }
    
    return consultant;
    }
    
    
}
