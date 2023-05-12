package com.example.demo6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.demo6.LoginController.useri;

public class DetailController implements Initializable {

    userHomeController u = new userHomeController();
    LoginController l = new  LoginController();
    @FXML
    private Label addresse;

    @FXML
    private Label date;

    @FXML
    private Label lieux;

    @FXML
    private Label nb;

    @FXML
    private Label nbb;

    @FXML
    private Label nbch;

    @FXML
    private Label nbl;

    @FXML
    private Label prix;

    @FXML
    private Label titre;

    @FXML
    private TextArea colcomm;

    public int ids = u.id_ssejour;
    public String user = useri;

    public void UpdateDetail() throws SQLException {
        Connection conn = mysqlconnect.ConnectDb();
        String sql = "select * from sejour where id_sejour='"+ids+"'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        String vlieux = "";
        String vtitre = "";
        String vadresse = "";
        int vlit = 0;
        int vchambre = 0;
        int vbain = 0;
        int vperso = 0;
        int vprix =0;
        String vdate = "";

        while (rs.next()) {

            vtitre = rs.getString("titre");
            vdate = rs.getString("depart");
             vlieux = rs.getString("lieux");
             vadresse = rs.getString("adresse");
             vchambre = rs.getInt("chambre");
            vbain = rs.getInt("bain");
             vlit = rs.getInt("lit");
            vprix = rs.getInt("prix");
            vperso = rs.getInt("nbperso");

        }
        conn.close();

       addresse.setText(vadresse);
       lieux.setText(vlieux);
       titre.setText(vtitre);
        date.setText(vdate);
       nbl.setText(Integer.toString(vlit));
        nbch.setText(Integer.toString(vchambre));
        nbb.setText(Integer.toString(vbain));
        prix.setText(Integer.toString(vprix));
        nb.setText(Integer.toString(vperso));


    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    Connection conn =null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            UpdateDetail();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    ResultSet rs = null;
    PreparedStatement pst = null;
    public void Reserver(ActionEvent actionEvent) throws SQLException {
        conn = mysqlconnect.ConnectDb();
       String s = "En cours";

        String sql ="insert into reservation (id_sejour_fk,id_user,statut)values('"+ids+"','"+useri+"','"+s+"')";

        pst=conn.prepareStatement(sql);
        pst.execute();
      //  showAlert(Alert.AlertType.WARNING, "Bien ajouté ", "La reservation est rajouté a votre panier.");

        String commentaire = colcomm.getText();
        String sql2 ="insert into commentaire (id_sejour_fk,id_user_fk,commentaire)values('"+ids+"','"+useri+"','"+commentaire+"')";

        pst=conn.prepareStatement(sql2);
        pst.execute();
        showAlert(Alert.AlertType.WARNING, "Bien ajouté ", "Commentaire Bien Ajouté.");
    }


    public void Commenter(ActionEvent actionEvent) throws SQLException {
        conn = mysqlconnect.ConnectDb();
        String s = "En cours";
        String commentaire = colcomm.getText();
        String sql ="insert into commentaire (id_sejour_fk,id_user_fk,commentaire)values('"+ids+"','"+useri+"','"+commentaire+"')";

        pst=conn.prepareStatement(sql);
        pst.execute();
        showAlert(Alert.AlertType.WARNING, "Bien ajouté ", "Commentaire Bien Ajouté.");
    }
}
