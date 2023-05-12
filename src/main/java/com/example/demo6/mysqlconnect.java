package com.example.demo6;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author amir
 */
public class mysqlconnect {

    static LoginController l =new LoginController();


    // Connection conn = null;
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sejour","root","");
           // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    public static ObservableList<sejourModel> getDatausers(){
        Connection conn = ConnectDb();
        ObservableList<sejourModel> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select lieux,adresse,prix,nbperso from sejour");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new sejourModel( rs.getString("lieux"),rs.getString("adresse"), Integer.parseInt(rs.getString("prix")), Integer.parseInt(rs.getString("nbperso"))));

            }
        } catch (Exception e) {
        }
        return list;
    }


    public static ObservableList<sejourModel> getDatausersHote(){
        Connection conn = ConnectDb();
        ObservableList<sejourModel> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from sejour");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new sejourModel( Integer.parseInt(rs.getString("id_sejour")),rs.getString("lieux"),rs.getString("adresse"), Integer.parseInt(rs.getString("prix")), Integer.parseInt(rs.getString("nbperso")),Integer.parseInt(rs.getString("chambre"))
                ,Integer.parseInt(rs.getString("lit")),Integer.parseInt(rs.getString("bain")),rs.getString("depart"),rs.getString("description"),rs.getString("titre"),rs.getString("image")));

            }
        } catch (Exception e) {
        }
        return list;
    }



    public static ObservableList<sejourModel> getDataReservation(){
        Connection conn = ConnectDb();
        ObservableList<sejourModel> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select titre , statut , id_reservation , id_sejour  from sejour INNER JOIN reservation ON sejour.id_sejour = reservation.id_sejour_fk ");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new sejourModel( Integer.parseInt(rs.getString("id_sejour")),rs.getString("titre"),rs.getString("statut"),Integer.parseInt(rs.getString("id_reservation"))));

            }
        } catch (Exception e) {
        }
        return list;
    }

}