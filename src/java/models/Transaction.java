package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Transaction extends Model<Transaction> {
    private int id;
    private int senderId;
    private int receiverId;
    private double sum;

    public Transaction() {
        this.table = "transaction";
        this.primaryKey = "id";
    }

    public Transaction(int id, int senderId, int receiverId, double sum) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.sum = sum;
    }

    @Override
    public Transaction toModel(ResultSet rs) {
        try {
            return new Transaction(
                rs.getInt("id"),
                rs.getInt("sender_id"),
                rs.getInt("receiver_id"),
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
