module co.edu.uniquindio.ingesis.inmobiliaria {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;


    opens co.edu.uniquindio.ingesis.inmobiliaria to javafx.fxml;
    exports co.edu.uniquindio.ingesis.inmobiliaria;
}