module com.example.demo6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
     requires eu.hansolo.fx.countries;
    requires eu.hansolo.toolbox;
    requires eu.hansolo.toolboxfx;
 //  requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires javafx.swing;
    requires org.json.ld;
    requires json;
    requires java.sql;
    opens com.example.demo6 to javafx.fxml;
    exports com.example.demo6;
}