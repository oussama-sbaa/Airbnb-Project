package com.example.demo6;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class HoteController implements Initializable {

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
    private TextField tnbrperso;

    @FXML
    private TextField tadresse;

    @FXML
    private TableView<sejourModel> tbHotecol;

    @FXML
    private TextField tbain;
    @FXML
    public TableColumn<sejourModel, String> companycol;

    @FXML
    public TableColumn<sejourModel, String> Imagecol;

    @FXML
    public TableColumn<sejourModel, Integer> prixcol;

    @FXML
    private TextField tchambre;

    @FXML
    private TextField tdate;

    @FXML
    private TextArea tdescription;
    @FXML
    private TextField filter;

    @FXML
    private TableColumn<sejourModel, String> titrecol;

    @FXML
    private TextField tlieux;

    @FXML
    private TextField tlit;

    @FXML
    private TextField tprix;

    @FXML
    private TextField tid;

    @FXML
    private TextField timage;

    @FXML
    private TextArea ttitre;

    @FXML
    void GoToAuth(ActionEvent event) {

    }




    public void UpdateTable(){
        idcol.setCellValueFactory(new PropertyValueFactory<sejourModel,Integer>("id_sejour"));
        lieuxcol.setCellValueFactory(new PropertyValueFactory<sejourModel,String>("lieux"));
        titrecol.setCellValueFactory(new PropertyValueFactory<sejourModel,String>("titre"));
        prixcol.setCellValueFactory(new PropertyValueFactory<sejourModel,Integer>("prix"));
        chambrecol.setCellValueFactory(new PropertyValueFactory<sejourModel,Integer>("chambre"));
        baincol.setCellValueFactory(new PropertyValueFactory<sejourModel,Integer>("bain"));
        litcol.setCellValueFactory(new PropertyValueFactory<sejourModel,Integer>("lit"));
        nbpersocol.setCellValueFactory(new PropertyValueFactory<sejourModel,Integer>("nbperso"));
        datecol.setCellValueFactory(new PropertyValueFactory<sejourModel,String>("depart"));
        Imagecol.setCellValueFactory(new PropertyValueFactory<sejourModel,String>("image"));
        listM = mysqlconnect.getDatausersHote();
        tbHotecol.setItems(listM);
    }

    @FXML
    public void add_sejour(javafx.event.ActionEvent actionEvent) {
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into sejour (lieux,adresse,prix,nbperso,chambre,lit,bain,description,depart,titre,image)values(?,?,?,?,?,?,?,?,?,?,?)";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,tlieux.getText());
            pst.setString(2,tadresse.getText());
            pst.setString(8,tdescription.getText());
            pst.setString(10,ttitre.getText());
            pst.setString(9,tdate.getText());
            pst.setString(11,timage.getText());
            pst.setInt(7,Integer.parseInt(tbain.getText()));
            pst.setInt(5,Integer.parseInt(tchambre.getText()));
            pst.setInt(6,Integer.parseInt(tlit.getText()));
            pst.setInt(3,Integer.parseInt(tprix.getText()));
            pst.setInt(4,Integer.parseInt(tnbrperso.getText()));
            pst.execute();

            JOptionPane.showMessageDialog(null, "Bien ajouté");
            UpdateTable();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }

    }

    @FXML
    void delete_sejour(javafx.event.ActionEvent actionEvent) {

    }

    @FXML
    void update_sejour(javafx.event.ActionEvent actionEvent) {

    }

    ObservableList<sejourModel> listM;
    ObservableList<sejourModel> dataList;

    int index = -1;

    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;








    @FXML
    void search_user() {
        //companycol.setCellValueFactory(new PropertyValueFactory<sejourModel,String>("lieux"));
     // adressecol.setCellValueFactory(new PropertyValueFactory<sejourModel,String>("adresse"));
        prixcol.setCellValueFactory(new PropertyValueFactory<sejourModel,Integer>("prix"));
        nbpersocol.setCellValueFactory(new PropertyValueFactory<sejourModel,Integer>("nbperso"));
        datecol.setCellValueFactory(new PropertyValueFactory<sejourModel,String>("depart"));
        titrecol.setCellValueFactory(new PropertyValueFactory<sejourModel,String>("titre"));

        dataList = mysqlconnect.getDatausersHote();
        tbHotecol.setItems(dataList);
        FilteredList<sejourModel> filteredData = new FilteredList<>(dataList, b -> true);
        filter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }


                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getLieux().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches username
                } else if (person.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }
                else if (String.valueOf(person.getDepart()).indexOf(lowerCaseFilter)!=-1)
                    return true;// Filter matches email

                else
                    return false; // Does not match.
            });
        });
        SortedList<sejourModel> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tbHotecol.comparatorProperty());
        tbHotecol.setItems(sortedData);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search_user();
        UpdateTable();


    }

    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) {

        index = tbHotecol.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        tid.setText(String.valueOf(idcol.getCellData(index)));
        timage.setText(Imagecol.getCellData(index));
        tdate.setText(datecol.getCellData(index));
        tbain.setText(baincol.getCellData(index).toString());
        tchambre.setText(chambrecol.getCellData(index).toString());
        tlit.setText(litcol.getCellData(index).toString());
        tnbrperso.setText(nbpersocol.getCellData(index).toString());
        tprix.setText(prixcol.getCellData(index).toString());
        tlieux.setText(lieuxcol.getCellData(index));
        ttitre.setText(titrecol.getCellData(index));
    }

    public void Edit(){
        try{
            conn = mysqlconnect.ConnectDb();
            String v1 = tid.getText();
            String v2 = timage.getText();
            String v3 = tdate.getText();
            String v4 = tbain.getText();
            String v5 = tchambre.getText();
            String v6 = tlit.getText();
            String v7 = tnbrperso.getText();
            String v8 = tprix.getText();
            String v9 = tlieux.getText();
            String v10 = ttitre.getText();


            String sql ="update sejour set id_sejour='"+v1+"', image='"+v2+"' , depart='"+v3+"' ,bain='"+v4+"' ,chambre='"+v5+"' ,lit='"+v6+"' ," +
                    "nbperso='"+v7+"' ,prix='"+v8+"' ,lieux='"+v9+"' ,titre='"+v10+"' where id_sejour='"+v1+"'";

            pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Update !!");
            UpdateTable();

        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }


    public void Delete(){
        conn = mysqlconnect.ConnectDb();
        String sql="delete from sejour where id_sejour=?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,tid.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Delete !!");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
            UpdateTable();
        }
    }
    @FXML
    private Button Dec;

    public void GoToDec(javafx.event.ActionEvent actionEvent) {
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

    public void GoToReservationH(javafx.event.ActionEvent actionEvent) {

        try{
            Scene currentScene = ((Node) actionEvent.getSource()).getScene();

            // Fermer la fenêtre correspondante à la scène
            Window currentWindow = currentScene.getWindow();
            currentWindow.hide();
            Parent root= FXMLLoader.load(getClass().getResource("reservationHote.fxml"));
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

