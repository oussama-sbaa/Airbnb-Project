package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthHoteController {


    @FXML
    private TextField identifiant;

    @FXML
    private TextField motdepasse;

    public static String useri ;


    Connection conn =null;
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void GotoHote(ActionEvent actionEvent) throws SQLException, IOException {
        String username = identifiant.getText();
        String password = motdepasse.getText();

        conn = mysqlconnect.ConnectDb();
        String query = "SELECT * FROM users WHERE username=? AND password=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // user is an administrator
            useri=username;
            Scene currentScene = ((Node) actionEvent.getSource()).getScene();

            // Fermer la fenêtre correspondante à la scène
            Window currentWindow = currentScene.getWindow();
            currentWindow.hide();
            Parent root= FXMLLoader.load(getClass().getResource("hote.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");
        } else {
            // user is not an administrator
            showAlert(Alert.AlertType.WARNING, "Login Failed", "Invalid username or password.");
        }

    }
}
