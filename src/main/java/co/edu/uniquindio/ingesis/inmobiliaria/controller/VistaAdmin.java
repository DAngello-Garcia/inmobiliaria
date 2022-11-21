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

public class VistaAdmin {
    @FXML
    private Button btnEmpleados;
    @FXML
    private Button btnReportes;


    public void irReportes(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Inmobiliaria.class.getResource("vista-empleado-reporte.fxml"));
        Scene scene = new Scene(parent, 1280, 720);
        Stage stage = new Stage();
        stage.setTitle("Reportes");
        stage.setScene(scene);
        stage.initOwner(btnReportes.getScene().getWindow());
        btnReportes.getScene().getWindow().hide();
        stage.show();
    }

    public void irGestionEmpleados(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Inmobiliaria.class.getResource("gestion-empleado.fxml"));
        Scene scene = new Scene(parent, 1280, 720);
        Stage stage = new Stage();
        stage.setTitle("Gestión de empleados");
        stage.setScene(scene);
        stage.initOwner(btnEmpleados.getScene().getWindow());
        btnEmpleados.getScene().getWindow().hide();
        stage.show();
    }
}
