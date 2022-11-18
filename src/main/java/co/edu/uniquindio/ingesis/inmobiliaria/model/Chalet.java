package co.edu.uniquindio.ingesis.inmobiliaria.model;

import co.edu.uniquindio.ingesis.inmobiliaria.util.Conexion;
import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class Chalet extends Casa{
    private Boolean aguaPotable;
    private Boolean alcantarillado;
    private Boolean pozoSeptico;
    private Boolean internet;
    private Boolean energiaElectrica;
    private Boolean gasDomiciliario;

    public Chalet(String identificador, String direccion, Boolean disponible, Double precio, Empleado empleado, LocalDateTime fechaCreacion, DisposicionPropiedad disposicionPropiedad, Float valorArea, TipoArea unidadesArea, Integer numeroPisos, Integer numeroHabitaciones, Integer numeroBanos, String material, Boolean aguaPotable, Boolean alcantarillado, Boolean pozoSeptico, Boolean internet, Boolean energiaElectrica, Boolean gasDomiciliario) {
        super(identificador, direccion, disponible, precio, empleado, fechaCreacion, disposicionPropiedad, valorArea, unidadesArea, numeroPisos, numeroHabitaciones, numeroBanos, material);
        this.aguaPotable = aguaPotable;
        this.alcantarillado = alcantarillado;
        this.pozoSeptico = pozoSeptico;
        this.internet = internet;
        this.energiaElectrica = energiaElectrica;
        this.gasDomiciliario = gasDomiciliario;
    }

    public boolean registrarChalet() {
        try{
            Conexion cx =  new Conexion();
            Connection con = cx.getConexion();

            PreparedStatement st = con.prepareStatement("INSERT INTO chalet (id, agua_potable, alcantarillado, pozo_septico, internet, energia_electrica, gas_domiciliario, id_casa) VALUES(?,?,?,?,?,?,?,?)");
            st.setString(1, this.getIdentificador());
            st.setBoolean(2, this.aguaPotable);
            st.setBoolean(3, this.alcantarillado);
            st.setBoolean(4, this.pozoSeptico);
            st.setBoolean(5, this.internet);
            st.setBoolean(6, this.energiaElectrica);
            st.setBoolean(7, this.gasDomiciliario);
            st.setString(8, this.getIdentificador());

            st.executeUpdate();
            st.close();

            con.close();
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
