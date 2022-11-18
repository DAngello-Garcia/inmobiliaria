package co.edu.uniquindio.ingesis.inmobiliaria.model;


import co.edu.uniquindio.ingesis.inmobiliaria.util.Conexion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente extends Persona {
    public Cliente(String nombre, Integer documento, String celular) {
        super(nombre, documento, celular);
    }

}
