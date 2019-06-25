package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Stan {
    private int ID;
    private int vlasnik;
    private String adresa;
    private String brojKvadrata;
    private int brojSoba;
    private String cijena;
    private int mjesto;
    private int vrstaStana;

    public Stan() {

    }

    public Stan(int ID, int vlasnik, String adresa, String brojKvadrata, int brojSoba, String cijena, int mjesto, int vrstaStana) {
        this.ID = ID;
        this.vlasnik = vlasnik;
        this.adresa = adresa;
        this.brojKvadrata =brojKvadrata;
        this.brojSoba = brojSoba;
        this.cijena = cijena;
        this.mjesto = mjesto;
        this.vrstaStana = vrstaStana;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getVlasnik() {
        return vlasnik;
    }

    public void setName(int vlasnik) {
        this.vlasnik = vlasnik;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setSurname(String adresa) {
        this.adresa = adresa;
    }

    public String getBrojKvadrata() {
        return brojKvadrata;
    }

    public void setBrojKvadrata(String brojKvadrata) {
        this.brojKvadrata = brojKvadrata;
    }

    public int getBrojSoba() {
        return brojSoba;
    }

    public void setBrojSoba(int brojSoba) {
        this.brojSoba = brojSoba;
    }

    public String getCijena() {
        return cijena;
    }

    public void setCijena(String cijena) {
        this.cijena = cijena;
    }

    public int getMjesto() {
        return mjesto;
    }

    public void setMjesto(int mjesto) {
        this.mjesto = mjesto;
    }

    public int getVrstaStana() {
        return vrstaStana;
    }

    public void setVrstaStana(int vrstaStana) {
        this.vrstaStana = vrstaStana;
    }

    public static Stan add(Stan s ) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("INSERT INTO stan VALUES (null, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmnt.setInt(1, s.getVlasnik());
            stmnt.setString(2, s.getAdresa());
            stmnt.setString(3, s.getBrojKvadrata());
            stmnt.setInt(4, s.getBrojSoba());
            stmnt.setString(5, s.getCijena());
            stmnt.setInt(6, s.getMjesto());
            stmnt.setInt(7, s.getVrstaStana());
            stmnt.executeUpdate();

            ResultSet rs = stmnt.getGeneratedKeys();
            if (rs.next()) {
                s.setID(rs.getInt(1));
            }
            return s;
        } catch (SQLException e) {
            System.out.println("Stan nije dodan: " + e.getMessage());
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


    public static boolean update(Stan s) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("UPDATE stan set id_vlasnik=?, adresa=?, brojKvadrata=?, brojSoba=?, cijena=?, mjesto=?, vrstaStana=?, WHERE ID_stan=?");
            stmnt.setInt(1, s.getVlasnik());
            stmnt.setString(2, s.getAdresa());
            stmnt.setString(3, s.getBrojKvadrata());
            stmnt.setInt(4, s.getBrojSoba());
            stmnt.setString(5, s.getCijena());
            stmnt.setInt(6, s.getMjesto());
            stmnt.setInt(7, s.getVrstaStana());
            stmnt.setInt(8, s.getID());
            stmnt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Stan nije uređen: " + e.getMessage());
            return false;
        }
    }
    public static List<Stan> select() {
        ObservableList<Stan> stanovi = FXCollections.observableArrayList();
        try {
            Statement stmnt = Database.CONNECTION.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM stan");


            while(rs.next()){
                stanovi.add(new Stan(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8)

                ));
            }
            return stanovi;
        } catch (SQLException e) {
            System.out.println("Stanovi se ne mogu izvući iz baze: " + e.getMessage());
            return stanovi;
        }
    }

}

