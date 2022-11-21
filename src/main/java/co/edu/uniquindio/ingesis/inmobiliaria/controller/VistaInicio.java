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

public class VistaInicio {

    @FXML
    private Button btnIngresar;
    public void ingresar(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Inmobiliaria.class.getResource("login.fxml"));
        Scene scene = new Scene(parent, 1280, 720);
        Stage stage = new Stage();
        stage.setTitle("Principal");
        stage.setScene(scene);
        stage.initOwner(btnIngresar.getScene().getWindow());
        btnIngresar.getScene().getWindow().hide();
        stage.show();
    }
}
