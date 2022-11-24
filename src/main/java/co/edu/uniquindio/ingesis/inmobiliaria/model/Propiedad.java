package co.edu.uniquindio.ingesis.inmobiliaria.model;

import co.edu.uniquindio.ingesis.inmobiliaria.util.Conexion;
import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Propiedad {
    private String identificador;
    private String direccion;
    private Propietario propietario;
    private Boolean disponible;
    private Double precio;
    private Empleado empleado;
    private LocalDateTime fechaModificacion;
    private LocalDateTime fechaCreacion;
    private DisposicionPropiedad disposicionPropiedad;
    private Float valorArea;
    private TipoArea unidadesArea;
    private Cliente cliente;
    private String tipoPropiedad = this.getClass().getSimpleName();

    public Propiedad(String identificador, String direccion, Boolean disponible, Double precio, Empleado empleado, LocalDateTime fechaCreacion, DisposicionPropiedad disposicionPropiedad, Float valorArea, TipoArea unidadesArea) {
        this.identificador = identificador;
        this.direccion = direccion;
        this.disponible = disponible;
        this.precio = precio;
        this.empleado = empleado;
        this.fechaCreacion = fechaCreacion;
        this.disposicionPropiedad = disposicionPropiedad;
        this.valorArea = valorArea;
        this.unidadesArea = unidadesArea;
    }

    public int registrarPropiedad() {
        try{
            Conexion cx =  new Conexion();
            Connection con = cx.getConexion();

            PreparedStatement st = con.prepareStatement("INSERT INTO propiedad (direccion, disponible, precio, fecha_creacion, area, unidades_area, disposicion_propiedad, tipo_propiedad) VALUES(?,?,?,?,?,?,?,?) RETURNING id", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, this.getDireccion());
            st.setBoolean(2, this.getDisponible());
            st.setDouble(3, this.getPrecio());
            st.setTimestamp(4, Timestamp.valueOf(this.getFechaCreacion()));
            st.setFloat(5, this.getValorArea());
            st.setString(6, String.valueOf(this.getUnidadesArea()));
            st.setString(7, String.valueOf(this.getDisposicionPropiedad()));
            st.setString(8, this.getTipoPropiedad());

            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            int id = 0;
            if (rs.next()) id = rs.getInt(1);
            st.close();

            PreparedStatement st2 = con.prepareStatement("INSERT INTO historial_propiedad (id_propiedad, id_empleado) VALUES(?,?)");
            st2.setInt(1, id);
            st2.setInt(2, this.getEmpleado().getDocumento());
            st2.executeUpdate();
            st2.close();

            con.close();
            return id;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
    }

}
