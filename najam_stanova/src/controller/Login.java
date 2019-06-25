package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Database;
import model.Vlasnik;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Login implements Initializable {
    @FXML
    Button prijavaBtn;

    @FXML
    TextField korisnikTxt;

    @FXML
    TextField lozinkaTxt;

    public static Vlasnik logiraniKorisnik;

    @FXML
    public void loginUser(ActionEvent a) {
        String username= korisnikTxt.getText();
        String password = lozinkaTxt.getText();
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("SELECT * FROM vlasnik WHERE korisnickoIme=? AND lozinka=?");
            stmnt.setString(1, username);
            stmnt.setString(2, password);
            ResultSet rs = stmnt.executeQuery();



            if (rs.next()) {
                Login.logiraniKorisnik = Vlasnik.get(rs.getInt(1));
                Utils u = new Utils();
                if (logiraniKorisnik.getUloga().equals("KLIJENT")|| logiraniKorisnik.getUloga().equals("VLASNIK")) {
                    u.showNewWindow("", a);
                }else {
                    u.showNewWindow("vjezba", a);
                }
            } else {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
