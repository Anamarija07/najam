package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Klijent {
    private int ID;
    private String name;
    private String surname;
    private String username;
    private String phoneNumber;

    public Klijent() {
    }

    public Klijent(int ID, String name, String surname, String phoneNumber) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.phoneNumber = phoneNumber;

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public static Klijent add(Klijent k) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("INSERT INTO klijent VALUES (null, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmnt.setString(1, k.getName());
            stmnt.setString(2, k.getSurname());
            stmnt.setString(3, k.getPhoneNumber());
            stmnt.executeUpdate();

            ResultSet rs = stmnt.getGeneratedKeys();
            if (rs.next()) {
                k.setID(rs.getInt(1));
            }
            return k;
        } catch (SQLException e) {
            System.out.println("Klijent nije dodan: " + e.getMessage());
            return null;
        }
    }

    public static boolean remove(Klijent k) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("DELETE FROM klijent WHERE ID_klijent=?");
            stmnt.setInt(1, k.getID());
            stmnt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Klijent nije obrisan: " + e.getMessage());
            return false;
        }
    }


    public static boolean update(Klijent k) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("UPDATE klijent set ime=?, prezime=?, korisnickoIme=?, lozinka=?, uloga=?, brojTelefona=?, adresa=? WHERE ID_klijent=?");
            stmnt.setString(1, k.getName());
            stmnt.setString(2, k.getSurname());
            stmnt.setString(3, k.getPhoneNumber());
            stmnt.setInt(4, k.getID());
            stmnt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Klijent nije uređen: " + e.getMessage());
            return false;
        }
    }
    public static List<Klijent> select() {
        ObservableList<Klijent> klijenti = FXCollections.observableArrayList();
        try {
            Statement stmnt = Database.CONNECTION.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM klijent");


            while(rs.next()){
                klijenti.add(new Klijent(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                        ));
            }
            return klijenti;
        } catch (SQLException e) {
            System.out.println("Klijenti se ne mogu izvući iz baze: " + e.getMessage());
            return klijenti;
        }
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
