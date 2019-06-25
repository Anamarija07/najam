package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Utils {

    public void showNewWindow(String viewname, ActionEvent e) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/" + viewname + ".fxml"));
            loader.load();
            Parent root = loader.getRoot();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            ((Node) (e.getSource())).getScene().getWindow().hide();
            stage.show();
        } catch (IOException ex) {
            System.out.println("Nastala je greška: " + ex.getMessage());
        }
    }

}