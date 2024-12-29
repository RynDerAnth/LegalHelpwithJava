package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Post extends Model<Post> {
    private int id;
    private String content;
    private int userId;

    public Post() {
        this.table = "post";
        this.primaryKey = "id";
    }

    public Post(int id, String content, int userId) {
        this.table = "post";
        this.primaryKey = "id";
        this.id = id;
        this.content = content;
        this.userId = userId;
    }

    @Override
    public Post toModel(ResultSet rs) {
        try {
            return new Post(
                rs.getInt("id"),
                rs.getString("content"),
                rs.getInt("user_id")
            );
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public ArrayList<Post> mostRecent() {
        ArrayList<Post> post = new ArrayList<>();
        String query = "SELECT * FROM " + table + " ORDER BY created_at DESC LIMIT 3";
        try (Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/legalhelpwithjava","root","")) {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                post.add(toModel(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }
}