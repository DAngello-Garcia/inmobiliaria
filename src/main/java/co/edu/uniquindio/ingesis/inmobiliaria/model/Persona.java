package co.edu.uniquindio.ingesis.inmobiliaria.model;

import lombok.Getter;
import lombok.Setter;
import static co.edu.uniquindio.ingesis.inmobiliaria.util.Validador.validacionObligatoriedad;

@Getter
@Setter
public class Persona {
    private final static String NOMBRE_INVALIDO="Debe ingresar un nombre";

    private final static String DOCUMENTO_INVALIDO="Debe ingresar su numero de documento";
    private String nombre;
    private Integer documento;
    private String celular;

    public Persona(String nombre, Integer documento, String celular) {
        validacionObligatoriedad(nombre,NOMBRE_INVALIDO );
        validacionObligatoriedad(documento,DOCUMENTO_INVALIDO);
        this.nombre = nombre;
        this.documento = documento;
        this.celular = celular;
    }

    public Persona() {
    }
}
