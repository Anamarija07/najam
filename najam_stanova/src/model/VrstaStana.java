package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VrstaStana {
    private int ID;
    private String vrstaStana;


    public VrstaStana() {
    }

    public VrstaStana(int ID, String vrstaStana) {
        this.ID = ID;
        this.vrstaStana = vrstaStana;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getVrstaStana() {
        return vrstaStana;
    }

    public void setVrstaStana(String vrstaStana) {
        this.vrstaStana = vrstaStana;
    }

    public static VrstaStana add(VrstaStana vs) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("INSERT INTO vrstastana VALUES (null, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmnt.setString(1, vs.getVrstaStana());
            stmnt.executeUpdate();

            ResultSet rs = stmnt.getGeneratedKeys();
            if (rs.next()) {
                vs.setID(rs.getInt(1));
            }
            return vs;
        } catch (SQLException e) {
            System.out.println("Vrsta stana nije dodana: " + e.getMessage());
            return null;
        }
    }

    public static boolean remove(VrstaStana vs) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("DELETE FROM vrstastana WHERE ID_vrstaStana=?");
            stmnt.setInt(1, vs.getID());
            stmnt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Vrsta stana nije obrisana: " + e.getMessage());
            return false;
        }
    }


    public static boolean update(VrstaStana vs) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("UPDATE vrstaStana set vrstaStana=? WHERE ID_vrstaStana=?");
            stmnt.setString(1, vs.getVrstaStana());
            stmnt.setInt(2, vs.getID());
            stmnt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Vrsta stana nije uređeno: " + e.getMessage());
            return false;
        }
    }
    public static List<VrstaStana> select() {
        ObservableList<VrstaStana> vrste = FXCollections.observableArrayList();
        try {
            Statement stmnt = Database.CONNECTION.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM vrstastana");


            while(rs.next()){
                vrste.add(new VrstaStana(
                        rs.getInt(1),
                        rs.getString(2)
                        ));
            }
            return vrste;
        } catch (SQLException e) {
            System.out.println("Vrste stanova se ne mogu izvući iz baze: " + e.getMessage());
            return vrste;
        }
    }

}


