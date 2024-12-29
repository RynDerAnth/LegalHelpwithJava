package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Konsultan extends Model<Konsultan> {
    private int id;
    private String name;
    private double price;

    public Konsultan() {
        this.table = "consultant";
        this.primaryKey = "id";
    }
    
    public Konsultan(int id, String name, double price) {
        this.table = "consultant";
        this.primaryKey = "id";
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public Konsultan toModel(ResultSet rs) {
        try {
            return new Konsultan(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("price")
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
    
    public ArrayList<Konsultan> mostExpensive() {
        ArrayList<Konsultan> consultant = new ArrayList<>();
        String query = "SELECT * FROM " + table + " ORDER BY price DESC LIMIT 3";
        try (Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/legalhelpwithjava","root","")) {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                consultant.add(toModel(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultant;
    }
}
