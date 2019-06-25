package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Mjesto {
    private int ID;
    private String mjesto;
    private String zupanija;

    public Mjesto() {
    }

    public Mjesto(int ID, String mjesto, String zupanija) {
        this.ID = ID;
        this.mjesto = mjesto;
        this.zupanija = zupanija;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMjesto() {
        return mjesto;
    }

    public void setMjesto(String mjesto) {
        this.mjesto = mjesto;
    }

    public String getZupanija() {
        return zupanija;
    }

    public void setZupanija(String zupanija) {
        this.zupanija = zupanija;
    }


    public static Mjesto add(Mjesto m) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("INSERT INTO mjesto VALUES (null, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmnt.setString(1, m.getMjesto());
            stmnt.setString(2, m.getZupanija());
            stmnt.executeUpdate();

            ResultSet rs = stmnt.getGeneratedKeys();
            if (rs.next()) {
                m.setID(rs.getInt(1));
            }
            return m;
        } catch (SQLException e) {
            System.out.println("Mjesto nije dodano: " + e.getMessage());
            return null;
        }
    }

    public static boolean remove(Mjesto m) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("DELETE FROM mjesto WHERE ID_mjesto=?");
            stmnt.setInt(1, m.getID());
            stmnt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Mjesto nije obrisano: " + e.getMessage());
            return false;
        }
    }


    public static boolean update(Mjesto m) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("UPDATE mjesto set nazivMjesta=?, zupanija=? WHERE ID_mjesto=?");
            stmnt.setString(1, m.getMjesto());
            stmnt.setString(2, m.getZupanija());
            stmnt.setInt(3, m.getID());
            stmnt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Mjesto nije uređeno: " + e.getMessage());
            return false;
        }
    }
    public static List<Mjesto> select() {
        ObservableList<Mjesto> mjesta = FXCollections.observableArrayList();
        try {
            Statement stmnt = Database.CONNECTION.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM mjesto");


            while(rs.next()){
                mjesta.add(new Mjesto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                ));
            }
            return mjesta;
        } catch (SQLException e) {
            System.out.println("Mjesta se ne mogu izvući iz baze: " + e.getMessage());
            return mjesta;
        }
    }

}

