package co.edu.uniquindio.ingesis.inmobiliaria.model;

import co.edu.uniquindio.ingesis.inmobiliaria.util.Conexion;
import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class Vivienda extends Propiedad {
      private Integer numeroHabitaciones;
      private Integer numeroBanos;
      private String material;

    public Vivienda(String identificador, String direccion, Boolean disponible, Double precio, Empleado empleado, LocalDateTime fechaCreacion, DisposicionPropiedad disposicionPropiedad, Float valorArea, TipoArea unidadesArea, Integer numeroHabitaciones, Integer numeroBanos, String material) {
        super(identificador, direccion, disponible, precio, empleado, fechaCreacion, disposicionPropiedad, valorArea, unidadesArea);
        this.numeroHabitaciones = numeroHabitaciones;
        this.numeroBanos = numeroBanos;
        this.material = material;
    }

    public int registrarVivienda(int id_propiedad) {
        try{
            Conexion cx =  new Conexion();
            Connection con = cx.getConexion();

            PreparedStatement st = con.prepareStatement("INSERT INTO vivienda (numero_habitaciones, numero_banos, material, id_propiedad) VALUES(?,?,?,?) RETURNING id", Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, this.getNumeroHabitaciones());
            st.setInt(2, this.getNumeroBanos());
            st.setString(3, this.getMaterial());
            st.setInt(4, id_propiedad);

            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            int id = 0;
            if (rs.next()) id = rs.getInt(1);
            st.close();

            con.close();
            return id;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
    }
}
