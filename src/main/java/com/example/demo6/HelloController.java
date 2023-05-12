package com.example.demo6;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class HelloController implements Initializable {



    @FXML
    private TableColumn<sejourModel, String> adressecol;

    @FXML
    private TableColumn<sejourModel, Integer> baincol;
    @FXML
    public TableColumn<sejourModel, Integer> nbpersocol;


    @FXML
    private TableColumn<sejourModel, Integer> chambrecol;

    @FXML
    private TableColumn<sejourModel, String> datecol;

    @FXML
    private TableColumn<sejourModel, Integer> idcol;

    @FXML
    private TableColumn<sejourModel, String> lieuxcol;

    @FXML
    private TableColumn<sejourModel, Integer> litcol;


    @FXML
    public TableColumn<sejourModel, String> companycol;

    @FXML
    public TableColumn<sejourModel, String> imagecol;

    @FXML
    public TableColumn<sejourModel, Integer> prixcol;

    @FXML
    private TableView<sejourModel> tbUsercol;

    @FXML
    private TableColumn<sejourModel, String> titrecol;

    @FXML
    private TextField filter;

    ObservableList<sejourModel> listM;
    ObservableList<sejourModel> dataList;

    int index = -1;

    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public HelloController() throws SQLException {
    }


    public void UpdateTable(){

        idcol.setCellValueFactory(new PropertyValueFactory<sejourModel,Integer>("id_sejour"));
        lieuxcol.setCellValueFactory(new PropertyValueFactory<sejourModel,String>("lieux"));
        titrecol.setCellValueFactory(new PropertyValueFactory<sejourModel,String>("titre"));
        prixcol.setCellValueFactory(new PropertyValueFactory<sejourModel,Integer>("prix"));
        nbpersocol.setCellValueFactory(new PropertyValueFactory<sejourModel,Integer>("nbperso"));
        datecol.setCellValueFactory(new PropertyValueFactory<sejourModel,String>("depart"));
        listM = mysqlconnect.getDatausersHote();
        tbUsercol.setItems(listM);
    }



    @FXML
    void search_user() {
       // companycol.setCellValueFactory(new PropertyValueFactory<sejourModel,String>("lieux"));
       // adressecol.setCellValueFactory(new PropertyValueFactory<sejourModel,String>("adresse"));
        titrecol.setCellValueFactory(new PropertyValueFactory<sejourModel,String>("titre"));
        nbpersocol.setCellValueFactory(new PropertyValueFactory<sejourModel,Integer>("nbperso"));
        datecol.setCellValueFactory(new PropertyValueFactory<sejourModel,String>("depart"));
        lieuxcol.setCellValueFactory(new PropertyValueFactory<sejourModel,String>("lieux"));

        dataList = mysqlconnect.getDatausersHote();
        tbUsercol.setItems(dataList);
        FilteredList<sejourModel> filteredData = new FilteredList<>(dataList, b -> true);
        filter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }


                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getLieux().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches username
                } else if (person.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }else if (person.getDepart().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }
                else if (String.valueOf(person.getDepart()).indexOf(lowerCaseFilter)!=-1)
                    return true;// Filter matches email

                else
                    return false; // Does not match.
            });
        });
        SortedList<sejourModel> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tbUsercol.comparatorProperty());
        tbUsercol.setItems(sortedData);
    }

    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        int id_sejour = 0;
        index = tbUsercol.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        id_sejour= idcol.getCellData(index);

        Parent root= FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UpdateTable();
        search_user();


    }


    public void GoToAuth(javafx.event.ActionEvent actionEvent) {
        try{
            Scene currentScene = ((Node) actionEvent.getSource()).getScene();

            // Fermer la fenêtre correspondante à la scène
            Window currentWindow = currentScene.getWindow();
            currentWindow.hide();
            Parent root= FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }


    public void GoToHot(javafx.event.ActionEvent actionEvent) {
        try{
            Scene currentScene = ((Node) actionEvent.getSource()).getScene();

            // Fermer la fenêtre correspondante à la scène
            Window currentWindow = currentScene.getWindow();
            currentWindow.hide();
            Parent root= FXMLLoader.load(getClass().getResource("AuthHote.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }





    public void GoToInscription(javafx.event.ActionEvent actionEvent) {
        try{
            Parent root= FXMLLoader.load(getClass().getResource("inscription.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}