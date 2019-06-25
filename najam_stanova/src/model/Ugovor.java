package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Ugovor {
    private int ID;
    private String opis;
    private Date datum;
    private int brojSoba;
    private int stan;
    private int vlasnik;
    private int klijent;

    public Ugovor(int anInt, String string, Date date, int rsInt, int i, int anInt1) {

    }

    public Ugovor(int ID, String opis, Date datum, int brojSoba, int stan, int vlasnik, int klijent) {
        this.ID = ID;
        this.opis = opis;
        this.datum = datum;
        this.brojSoba = brojSoba;
        this.stan = stan;
        this.vlasnik = vlasnik;
        this.klijent = klijent;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getBrojSoba() {
        return brojSoba;
    }

    public void setBrojSoba(int brojSoba) {
        this.brojSoba = brojSoba;
    }

    public int getStan() {
        return stan;
    }

    public void setStan(int stan) {
        this.stan = stan;
    }

    public int getVlasnik() {
        return vlasnik;
    }

    public void setVlasnik(int vlasnik) {
        this.vlasnik = vlasnik;
    }

    public int getKlijent() {
        return klijent;
    }

    public void setKlijent(int klijent) {
        this.klijent = klijent;
    }

    public static Ugovor add(Ugovor g) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("INSERT INTO ugovor VALUES (null, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmnt.setString(1, g.getOpis());
            stmnt.setDate(2, g.getDatum());
            stmnt.setInt(3, g.getStan());
            stmnt.setInt(4, g.getVlasnik());
            stmnt.setInt(5, g.getKlijent());
            stmnt.executeUpdate();

            ResultSet rs = stmnt.getGeneratedKeys();
            if (rs.next()) {
                g.setID(rs.getInt(1));
            }
            return g;
        } catch (SQLException e) {
            System.out.println("Ugovor nije dodan: " + e.getMessage());
            return null;
        }
    }

    public static boolean remove(Stan s) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("DELETE FROM stan WHERE ID_stan=?");
            stmnt.setInt(1, s.getID());
            stmnt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Stan nije obrisan: " + e.getMessage());
            return false;
        }
    }


    public static boolean update(Ugovor g) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("UPDATE stan set id_ugovor=?, opis=?, datum=? id_stan=?, id_vlasnik=?, id_klijent=?  WHERE ID_ugovor=?");
            stmnt.setString(1, g.getOpis());
            stmnt.setDate(2, g.getDatum());
            stmnt.setInt(3, g.getStan());
            stmnt.setInt(4, g.getVlasnik());
            stmnt.setInt(5, g.getKlijent());
            stmnt.setInt(8, g.getID());
            stmnt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Ugovor nije uređen: " + e.getMessage());
            return false;
        }
    }
    public static List<Ugovor> select() {
        ObservableList<Ugovor> ugovori = FXCollections.observableArrayList();
        try {
            Statement stmnt = Database.CONNECTION.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM ugovor");


            while(rs.next()){
                ugovori.add(new Ugovor(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6)

                ));
            }
            return ugovori;
        } catch (SQLException e) {
            System.out.println("Ugovori se ne mogu izvući iz baze: " + e.getMessage());
            return ugovori;
        }
    }

}

