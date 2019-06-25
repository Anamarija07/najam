package model;

public class Database {
    public static java.sql.Connection CONNECTION = null;

    static {
        try {
            CONNECTION = new Connection().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}