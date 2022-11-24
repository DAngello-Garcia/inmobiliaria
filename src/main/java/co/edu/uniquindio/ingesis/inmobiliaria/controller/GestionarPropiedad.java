package co.edu.uniquindio.ingesis.inmobiliaria.controller;

import co.edu.uniquindio.ingesis.inmobiliaria.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.GridPane;

import java.time.LocalDateTime;
import java.util.List;

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
        cbDisposicion.getItems().addAll("Venta","Alquiler");
        txtTipo.setItems(tipos);
        txtNumeroHabitaciones.setVisible(false);
        checkAlcantarillado.setVisible(false);
        checkAguaPotable.setVisible(false);
        checkPozoSeptico.setVisible(false);
        checkInternet.setVisible(false);
        checkEnergiaElectrica.setVisible(false);
        checkGasDomiciliario.setVisible(false);
        checkBalcon.setVisible(false);
        checkAscensor.setVisible(false);
        comboTipoLote.setVisible(false);
        txtNumeroBanos.setVisible(false);
        txtMaterial.setVisible(false);
        txtNumeroPisos.setVisible(false);
        txtPiso.setVisible(false);
        txtValorAdministracion.setVisible(false);
        txtNumeroParqueaderos.setVisible(false);
        lbNumHab.setVisible(false);
        lbNumBa.setVisible(false);
        lbMaterial.setVisible(false);
        lablNumPisos.setVisible(false);
        lblPiso.setVisible(false);
        lbValorAdmin.setVisible(false);
        lblNumPar.setVisible(false);
        lbTipo.setVisible(false);
        /*llenarTabla(INSTANCE.getDiplomado().buscar(null, null, null));
        colNumeroIdentificacion.setCellValueFactory(new PropertyValueFactory<>("numeroIdentificacion"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        tblEstudiantes.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> llenarCampos(newValue));
        tfNumeroIdentificacion.setTextFormatter(new TextFormatter<>(TextFormatterUtil::integerFormat));
        tfNombre.setTextFormatter(new TextFormatter<>(TextFormatterUtil::upperCaseFormat));
        cbGenero.setItems( FXCollections.observableArrayList(Genero.values()) );*/
    }

    /*
    private void llenarTabla(List<Propiedad> propiedades) {
        /*tblEstudiantes.setItems(FXCollections.observableArrayList(propiedades));
        tblEstudiantes.refresh();*/
    //}

    private void limpiarCampos() {
        /*tfNumeroIdentificacion.setText("");
        tfNombre.setText("");
        cbGenero.setValue(null);
        lbNotas.setText("");*/
    }
    @FXML
    public void buttonClicked(Event e){
        System.out.println(txtTipo.getValue().toString());
    switch (txtTipo.getValue().toString()){
        case "Apartamento":
           /* Apartamento a=new Vivienda("33",txtDireccion.getText(), null,null,
                    null,null,null,null,
                    null,22,1,
                  //  "null");
           int id_propiedad = a.registrarPropiedad();
            int id_vivienda = a.registrarVivienda(11);*/
            break;
        case "Bodega":
            Usuario empleUser = new Usuario("emple1@inmo.com", "password", "frase_emple", true);
            Empleado empleado = new Empleado("empleado1", 1, "300", empleUser);
            LocalDateTime fecha = LocalDateTime.now();
            String dp=cbDisposicion.getValue().toString();
            DisposicionPropiedad dpclass;
            TipoArea t = TipoArea.M2;
            TipoBodegaLote tipoBodegaLote=TipoBodegaLote.URBANA;
            Float valorArea=Float.parseFloat(txtValorArea.getText());

            if(dp=="Venta"){
                 dpclass=DisposicionPropiedad.VENTA;
            }else{
                 dpclass=DisposicionPropiedad.ALQUILER;

            }
            Bodega b=new Bodega(txtId.getText(),txtDireccion.getText(),true,
                    Double.parseDouble(txtPrecio.getText()),empleado,fecha,dpclass,valorArea,
                    t,tipoBodegaLote);
            int id_propiedad2 = b.registrarPropiedad();
            b.registrarBodega(id_propiedad2);
        case "Chalet":

        case "Lote":
        case "Casa":
        case "Edificio":
        case "Parqueadero":
        default: break;
    }

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

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
