package co.edu.uniquindio.ingesis.inmobiliaria.controller;

import co.edu.uniquindio.ingesis.inmobiliaria.Inmobiliaria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class VistaEmpleado {
    @FXML
    Button btnregistarPropiedad;

    @FXML
    Button btnPropiedadDispo;

    @FXML
    Button btnPropiedadNoDispo;
    public void registrarPropiedad(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Inmobiliaria.class.getResource("registrar-propiedad.fxml"));
        Scene scene = new Scene(parent, 1280, 720);
        Stage stage = new Stage();
        stage.setTitle("Registrar propiedad");
        stage.setScene(scene);
        stage.initOwner(btnregistarPropiedad.getScene().getWindow());
        btnregistarPropiedad.getScene().getWindow().hide();
        stage.show();
    }

    public void propiedadDisponible(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Inmobiliaria.class.getResource("propiedades-disponibles.fxml"));
        Scene scene = new Scene(parent, 1280, 720);
        Stage stage = new Stage();
        stage.setTitle("Propiedades disponibles");
        stage.setScene(scene);
        stage.initOwner(btnPropiedadDispo.getScene().getWindow());
        btnPropiedadDispo.getScene().getWindow().hide();
        stage.show();
    }

    public void propiedadNoDisponible(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Inmobiliaria.class.getResource("propiedades-no-disponibles.fxml"));
        Scene scene = new Scene(parent, 1280, 720);
        Stage stage = new Stage();
        stage.setTitle("Propiedades no disponibles");
        stage.setScene(scene);
        stage.initOwner(btnPropiedadNoDispo.getScene().getWindow());
        btnPropiedadNoDispo.getScene().getWindow().hide();
        stage.show();
    }


}
