package co.edu.uniquindio.ingesis.inmobiliaria.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.GridPane;

public class GestionarPropiedad {
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtValorArea;
    @FXML
    private TextField txtUnidadesArea;
    @FXML
    private ComboBox cbDisposicion;
    @FXML
    private ComboBox txtTipo;
    @FXML
    private TextField txtNumeroHabitaciones;
    @FXML
    private TextField txtNumeroBanos;
    @FXML
    private TextField txtMaterial;
    @FXML
    private TextField txtNumeroPisos;
    @FXML
    private TextField txtPiso;
    @FXML
    private TextField txtValorAdministracion;
    @FXML
    private TextField txtNumeroParqueaderos;
    @FXML
    private Label lbNumHab;
    @FXML
    private Label lbNumBa;
    @FXML
    private Label lbMaterial;
    @FXML
    private Label lablNumPisos;
    @FXML
    private Label lblPiso;
    @FXML
    private Label lbValorAdmin;
    @FXML
    private Label lblNumPar;
    @FXML
    private Label lbTipo;
    @FXML
    private CheckBox checkAlcantarillado;
    @FXML
    private CheckBox checkAguaPotable;
    @FXML
    private CheckBox checkPozoSeptico;
    @FXML
    private CheckBox checkInternet;
    @FXML
    private CheckBox checkEnergiaElectrica;
    @FXML
    private CheckBox checkGasDomiciliario;
    @FXML
    private CheckBox checkBalcon;
    @FXML
    private CheckBox checkAscensor;
    @FXML
    private ComboBox comboTipoLote;

    @FXML
    public void initialize() {
        ObservableList<String> tipos = FXCollections.observableArrayList("Apartamento", "Bodega", "Casa", "Chalet", "Edificio", "Lote", "Parqueadero");
        txtTipo.setItems(tipos);
        txtNumeroHabitaciones.setVisible(false);
    }

    @FXML
    public void mostrarInformacion(ActionEvent actionEvent) {
        Node source = (Node)actionEvent.getSource() ;
        Integer col = GridPane.getColumnIndex(source);
        Integer row = GridPane.getRowIndex(source);
        System.out.println(col);
        System.out.println(row);
        txtNumeroHabitaciones.setMaxHeight(0d);
    }
}
