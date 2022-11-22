package co.edu.uniquindio.ingesis.inmobiliaria.controller;

import co.edu.uniquindio.ingesis.inmobiliaria.Inmobiliaria;
import co.edu.uniquindio.ingesis.inmobiliaria.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtContra;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnOlvidar;


    public void login(ActionEvent actionEvent) throws IOException {
        Usuario user = new Usuario(txtCorreo.getText(), txtContra.getText());
        int rol = user.login();
        if(rol == 1) {
            Parent parent = FXMLLoader.load(Inmobiliaria.class.getResource("vista-admin.fxml"));
            Scene scene = new Scene(parent, 1280, 720);
            Stage stage = new Stage();
            stage.setTitle("Admin");
            stage.setScene(scene);
            stage.initOwner(btnLogin.getScene().getWindow());
            btnLogin.getScene().getWindow().hide();
            stage.show();
        } else if(rol == 2) {
            Parent parent = FXMLLoader.load(Inmobiliaria.class.getResource("vista-empleado.fxml"));
            Scene scene = new Scene(parent, 1280, 720);
            Stage stage = new Stage();
            stage.setTitle("Empleado");
            stage.setScene(scene);
            stage.initOwner(btnLogin.getScene().getWindow());
            btnLogin.getScene().getWindow().hide();
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("No existe el usuario ingresado");
        }
    }

    public void olvidarContra(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Inmobiliaria.class.getResource("recuperar-contra.fxml"));
        Scene scene = new Scene(parent, 1280, 720);
        Stage stage = new Stage();
        stage.setTitle("Recuperar clave");
        stage.setScene(scene);
        stage.initOwner(btnOlvidar.getScene().getWindow());
        btnOlvidar.getScene().getWindow().hide();
        stage.show();
    }
}
