package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Vlasnik {
    private int ID;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;
    private String uloga;
    private String brojTelefona;
    private String adresa;

    public Vlasnik(int ID, String anamarija, String dumančić, String s, String admin, String s1) {

    }


    public Vlasnik() {
    }

    public Vlasnik(int ID, String ime, String prezime, String korisnickoIme, String lozinka, String uloga, String brojTelefona, String adresa) {
        this.ID = ID;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.uloga = uloga;
        this.brojTelefona = brojTelefona;
        this.adresa = adresa;
    }

    public static Vlasnik get(int anInt) {
        return null;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) throws Exception {
        if (uloga.toLowerCase().equals("admin") || uloga.toLowerCase().equals("vlasnik") || uloga.toLowerCase().equals("klijent"))
            this.uloga = uloga.toUpperCase();
        else
            throw new Exception("Molim Vas izaberite novu korisničku ulogu");
    }

    public static Vlasnik add(Vlasnik v) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("INSERT INTO vlasnik VALUES (null, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmnt.setString(1, v.getIme());
            stmnt.setString(2, v.getPrezime());
            stmnt.setString(3, v.getKorisnickoIme());
            stmnt.setString(4, v.getLozinka());
            stmnt.setString(5, v.getUloga());
            stmnt.setString(6, v.getBrojTelefona());
            stmnt.setString(7, v.getAdresa());
            stmnt.executeUpdate();

            ResultSet rs = stmnt.getGeneratedKeys();
            if (rs.next()) {
                v.setID(rs.getInt(1));
            }
            return v;
        } catch (SQLException e) {
            System.out.println("Korisnik nije dodan: " + e.getMessage());
            return null;
        }
    }

    public static boolean remove(Vlasnik v) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("DELETE FROM vlasnik WHERE ID_vlasnik=?");
            stmnt.setInt(1, v.getID());
            stmnt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Korisnik nije obrisan: " + e.getMessage());
            return false;
        }
    }


    public static boolean update(Vlasnik v) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("UPDATE vlasnik set ime=?, prezime=?, korisnickoIme=?, lozinka=?, uloga=?, brojTelefona=?, adresa=? WHERE ID_vlasnik=?");
            stmnt.setString(1, v.getIme());
            stmnt.setString(2, v.getPrezime());
            stmnt.setString(3, v.getIme());
            stmnt.setString(4, v.getLozinka());
            stmnt.setString(5, v.getUloga());
            stmnt.setString(6, v.getBrojTelefona());
            stmnt.setString(7, v.getAdresa());
            stmnt.setInt(8, v.getID());
            stmnt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Korisnik nije ureden: " + e.getMessage());
            return false;
        }
    }
    public static List<Vlasnik> select() {
        ObservableList<Vlasnik> vlasnici = FXCollections.observableArrayList();
        try {
            Statement stmnt = Database.CONNECTION.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM vlasnik");


            while(rs.next()){
                vlasnici.add(new Vlasnik(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)

                ));
            }
            return vlasnici;
        } catch (SQLException e) {
            System.out.println("Nisam uspio izvuci korisnike iz baze: " + e.getMessage());
            return vlasnici;
        }
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
}
