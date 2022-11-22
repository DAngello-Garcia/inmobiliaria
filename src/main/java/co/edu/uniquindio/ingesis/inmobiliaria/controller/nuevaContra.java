package co.edu.uniquindio.ingesis.inmobiliaria.controller;

import co.edu.uniquindio.ingesis.inmobiliaria.Inmobiliaria;
import co.edu.uniquindio.ingesis.inmobiliaria.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import javax.swing.*;
import java.io.IOException;

public class nuevaContra {

    @FXML
    private TextField txtNuevaContra;
    @FXML
    private Button btnLogin;

    @FXML
    public void login(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Usuario user = (Usuario) stage.getUserData();
        String correo = user.getCorreo();
        if(user.cambiarClave(correo, txtNuevaContra.getText())) {
            Parent parent = FXMLLoader.load(Inmobiliaria.class.getResource("vista-admin.fxml"));
            Scene scene = new Scene(parent, 1280, 720);
            Stage stage2 = new Stage();
            stage2.setTitle("Nueva contraseña");
            stage2.setUserData(user);
            stage2.setScene(scene);
            stage2.initOwner(btnLogin.getScene().getWindow());
            btnLogin.getScene().getWindow().hide();
            stage2.show();
        } else {
            JOptionPane.showMessageDialog(null, "No existe");
        }
    }
}
