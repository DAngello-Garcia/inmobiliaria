package co.edu.uniquindio.ingesis.inmobiliaria.controller;

import co.edu.uniquindio.ingesis.inmobiliaria.Inmobiliaria;
import co.edu.uniquindio.ingesis.inmobiliaria.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import java.io.IOException;

public class RecuperarClave {
    @FXML
    private Button btnRecuperar;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtRespuesta;

    @FXML
    public void initialize() {

    }

    public void nuevaContra(ActionEvent actionEvent) throws IOException {
        Usuario user = new Usuario(txtCorreo.getText());
        if(user.verificarFrase(txtCorreo.getText(), txtRespuesta.getText())) {
            Parent parent = FXMLLoader.load(Inmobiliaria.class.getResource("nueva-contra.fxml"));
            Scene scene = new Scene(parent, 1280, 720);
            Stage stage = new Stage();
            stage.setTitle("Nueva contraseña");
            stage.setUserData(user);
            stage.setScene(scene);
            stage.initOwner(btnRecuperar.getScene().getWindow());
            btnRecuperar.getScene().getWindow().hide();
            stage.show();
        } else {
            JOptionPane.showMessageDialog(null, "Usuario inactivo o frase de seguridad inválida");
        }
    }
}
