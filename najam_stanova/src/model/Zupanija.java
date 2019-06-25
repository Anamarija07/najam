package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Zupanija {
    private int ID;
    private String nazivZupanije;


    public Zupanija() {
    }

    public Zupanija(int ID, String nazivZupanije) {
        this.ID = ID;
        this.nazivZupanije = nazivZupanije;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNazivZupanije() {
        return nazivZupanije;
    }

    public void setNazivZupanije(String nazivZupanije) {
        this.nazivZupanije = nazivZupanije;
    }

    public static Zupanija add(Zupanija z) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("INSERT INTO zupanije VALUES (null, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmnt.setString(1, z.getNazivZupanije());
            stmnt.executeUpdate();

            ResultSet rs = stmnt.getGeneratedKeys();
            if (rs.next()) {
                z.setID(rs.getInt(1));
            }
            return z;
        } catch (SQLException e) {
            System.out.println("Zupanija nije dodana: " + e.getMessage());
            return null;
        }
    }

    public static boolean remove(Zupanija z) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("DELETE FROM zupanija WHERE ID_zupanija=?");
            stmnt.setInt(1, z.getID());
            stmnt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Zupanija nije obrisana: " + e.getMessage());
            return false;
        }
    }


    public static boolean update(Zupanija z) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("UPDATE zupanija set nazivZupanije=? WHERE ID_zupanija=?");
            stmnt.setString(1, z.getNazivZupanije());
            stmnt.setInt(2, z.getID());
            stmnt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Zupanija nije uređena: " + e.getMessage());
            return false;
        }
    }
    public static List<Zupanija> select() {
        ObservableList<Zupanija> zupanije = FXCollections.observableArrayList();
        try {
            Statement stmnt = Database.CONNECTION.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM zupanija");


            while(rs.next()){
                zupanije.add(new Zupanija(
                        rs.getInt(1),
                        rs.getString(2)
                ));
            }
            return zupanije;
        } catch (SQLException e) {
            System.out.println("Zupanije se ne mogu izvući iz baze: " + e.getMessage());
            return zupanije;
        }
    }

}


