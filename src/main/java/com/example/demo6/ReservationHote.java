package com.example.demo6;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ReservationHote implements Initializable {


    @FXML
    private TextField filter;

    @FXML
    private TableColumn<sejourModel, Integer> idcol;

    @FXML
    private TableColumn<sejourModel, Integer> idreservationcol;

    @FXML
    private TableColumn<sejourModel, Integer> idsejourcol;

    @FXML
    private TableColumn<sejourModel, String> sejourcol;

    @FXML
    private TableColumn<sejourModel, String> statutcol;

    @FXML
    private TableView<sejourModel> tbrcol;

    @FXML
    private TextField tid;

    ObservableList<sejourModel> listM;
    ObservableList<sejourModel> dataList;

    int index = -1;

    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void UpdateTable(){

        idcol.setCellValueFactory(new PropertyValueFactory<sejourModel,Integer>("id_sejour"));
        statutcol.setCellValueFactory(new PropertyValueFactory<sejourModel,String>("statut"));
        sejourcol.setCellValueFactory(new PropertyValueFactory<sejourModel,String>("titre"));
        idreservationcol.setCellValueFactory(new PropertyValueFactory<sejourModel,Integer>("id_reservation"));
        listM = mysqlconnect.getDataReservation();
        tbrcol.setItems(listM);
    }

    public void getSelected(MouseEvent mouseEvent) {
        index = tbrcol.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        tid.setText(String.valueOf(idcol.getCellData(index)));

        tid.setText(idcol.getCellData(index).toString());
    }

    public void Valider(ActionEvent actionEvent) {

        try{
            conn = mysqlconnect.ConnectDb();
            String statut = "Acceptée";
            String v1 = tid.getText();



            String sql ="update reservation set statut='"+statut+"' where id_sejour_fk='"+v1+"'";

            pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Vous avez acceptée la demande  !!");
            UpdateTable();

        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

    public void Abandonner(ActionEvent actionEvent) {


        try{
            conn = mysqlconnect.ConnectDb();
            String statut = "Abandonée";
            String v1 = tid.getText();



            String sql ="update reservation set statut='"+statut+"' where id_sejour_fk='"+v1+"'";

            pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Vous avez refusée la demande  !!");
            UpdateTable();

        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

    public void GoToAuth(ActionEvent actionEvent) {

        try{
            Scene currentScene = ((Node) actionEvent.getSource()).getScene();

            // Fermer la fenêtre correspondante à la scène
            Window currentWindow = currentScene.getWindow();
            currentWindow.hide();
            Parent root= FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void GoToAcc(ActionEvent actionEvent) {
        try{
            Scene currentScene = ((Node) actionEvent.getSource()).getScene();

            // Fermer la fenêtre correspondante à la scène
            Window currentWindow = currentScene.getWindow();
            currentWindow.hide();
            Parent root= FXMLLoader.load(getClass().getResource("Hote.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UpdateTable();
    }
}
