package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Timestamp;

public class Article extends Model<Article> {
    private int id;
    private String headline;
    private String content;
    private String picture_path;
    private Timestamp created_at;

    public Article() {
        this.table = "article";
        this.primaryKey = "id";
    }

    public Article(int id, String headline, String content, Timestamp created_at, String picture_path) {
        this.table = "article";
        this.primaryKey = "id";
        this.id = id;
        this.headline = headline;
        this.content = content;
        this.created_at = created_at;
        this.picture_path = picture_path;
    }

    @Override
    public Article toModel(ResultSet rs) {
        try {
            return new Article(
                rs.getInt("id"),
                rs.getString("headline"),
                rs.getString("content"),
                rs.getTimestamp("created_at"),
                rs.getString("picture_path")
                
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

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getPicture_path() {
        return picture_path;
    }

    public void setPicture_path(String picture_path) {
        this.picture_path = picture_path;
    }

    
    public ArrayList<Article> mostRecent() {
        ArrayList<Article> article = new ArrayList<>();
        String query = "SELECT * FROM " + table + " ORDER BY id DESC";
        try (Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/legalhelpwithjava","root","")) {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                article.add(toModel(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }
    
    public int articleCount() {
        int count = 0;
        String query = "SELECT COUNT(*) AS total FROM " + table;
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
    
    
}
