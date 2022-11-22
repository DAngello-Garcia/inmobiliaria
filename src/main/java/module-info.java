module co.edu.uniquindio.ingesis.inmobiliaria {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;
    requires java.desktop;


    opens co.edu.uniquindio.ingesis.inmobiliaria to javafx.fxml;
    exports co.edu.uniquindio.ingesis.inmobiliaria;
    exports co.edu.uniquindio.ingesis.inmobiliaria.model;
    opens co.edu.uniquindio.ingesis.inmobiliaria.model to javafx.fxml;
    exports co.edu.uniquindio.ingesis.inmobiliaria.controller;
    opens co.edu.uniquindio.ingesis.inmobiliaria.controller to javafx.fxml;
    exports co.edu.uniquindio.ingesis.inmobiliaria.util;
    opens co.edu.uniquindio.ingesis.inmobiliaria.util to javafx.fxml;
}