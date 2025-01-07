package models;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Timestamp;

public class Post extends Model<Post> {
    private int id;
    private String content;
    private String userName;
    private int userId;
    private Timestamp created_at;

    public Post() {
        this.table = "post";
        this.primaryKey = "id";
    }

    public Post(int id, String content, int userId, String userName, Timestamp created_at) {
        this.table = "post";
        this.primaryKey = "id";
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.userName = userName;
        this.created_at = created_at;
    }

    @Override
    public Post toModel(ResultSet rs) {
        try {
            return new Post(
                rs.getInt("id"),
                rs.getString("content"),
                rs.getInt("userId"),
                rs.getString("userName"),
                rs.getTimestamp("created_at")
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

    public String getUserName() {
        return userName;
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
    
    public ArrayList<Post> mostRecent() {
        ArrayList<Post> post = new ArrayList<>();
        String query = "SELECT * FROM " + table + " ORDER BY id DESC";
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
    
    public int postCount() {
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
    public String getProfile_path() {
        User user = new User();
        user = user.find("id", this.userId);
        return user != null ? ("uploads/" + user.getProfile_path()) : "https://via.placeholder.com/50" ;
    }
}