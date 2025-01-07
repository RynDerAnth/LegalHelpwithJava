package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Transaction extends Model<Transaction> {
    private int id;
    private int senderId;
    private int receiverId;
    private double sum;
    private Timestamp created_at;

    public Transaction() {
        this.table = "transaction";
        this.primaryKey = "id";
    }

    public Transaction(int id, int senderId, int receiverId, double sum, Timestamp created_at) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.sum = sum;
        this.created_at = created_at;
    }

    @Override
    public Transaction toModel(ResultSet rs) {
        try {
            return new Transaction(
                rs.getInt("id"),
                rs.getInt("senderId"),
                rs.getInt("receiverId"),
                rs.getDouble("sum"),
                rs.getTimestamp("created_at")
            );
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
    
    public int getId() {
        return id;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public double getSum() {
        return sum;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

}
