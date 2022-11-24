package co.edu.uniquindio.ingesis.inmobiliaria.model;

import co.edu.uniquindio.ingesis.inmobiliaria.util.Conexion;
import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.time.LocalDateTime;

@Setter
@Getter
public class Edificio extends Propiedad{
    private Integer numeroPisos;

    public Edificio(String identificador, String direccion, Boolean disponible, Double precio, Empleado empleado, LocalDateTime fechaCreacion, DisposicionPropiedad disposicionPropiedad, Float valorArea, TipoArea unidadesArea, Integer numeroPisos) {
        super(identificador, direccion, disponible, precio, empleado, fechaCreacion, disposicionPropiedad, valorArea, unidadesArea);
        this.numeroPisos = numeroPisos;
    }

    public boolean registrarEdificio(int id_propiedad) {
        try{
            Conexion cx =  new Conexion();
            Connection con = cx.getConexion();

            PreparedStatement st = con.prepareStatement("INSERT INTO edificio (id, numero_pisos, id_propiedad) VALUES(?,?,?)");
            st.setString(1, this.getIdentificador());
            st.setInt(2, this.numeroPisos);
            st.setInt(3, id_propiedad);

            st.executeUpdate();
            st.close();

            con.close();
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean alquilarEdificio(String id) {
        try{
            if(consultarDisponibilidadEdificio(id)) {
                Conexion cx =  new Conexion();
                Connection con = cx.getConexion();

                int id_propiedad = 0;
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("UPDATE propiedad p\n" +
                        "SET disponible = 'false'\n" +
                        "FROM edificio e\n" +
                        "WHERE (e.id_propiedad = p.id AND e.id = '"+id+"') RETURNING p.id");

                if(rs.next()) id_propiedad = rs.getInt(1);
                rs.close();
                st.close();

                PreparedStatement st2 = con.prepareStatement("UPDATE historial_propiedad SET " +
                        "id_cliente = "+this.getCliente().getDocumento()+", " +
                        "fecha_modificacion = '"+this.getFechaModificacion()+"' " +
                        "WHERE id_propiedad = "+id_propiedad+"");

                st2.executeUpdate();
                st2.close();

                con.close();
                return true;
            }
            return false;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean venderEdificio(String id) {
        try{
            if(consultarDisponibilidadEdificio(id)) {
                Conexion cx =  new Conexion();
                Connection con = cx.getConexion();

                int id_propiedad = 0;
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("UPDATE propiedad p\n" +
                        "SET disponible = 'false'\n" +
                        "FROM edificio e\n" +
                        "WHERE (e.id_propiedad = p.id AND e.id = '"+id+"') RETURNING p.id");

                if(rs.next()) id_propiedad = rs.getInt(1);
                rs.close();
                st.close();

                PreparedStatement st2 = con.prepareStatement("UPDATE historial_propiedad SET" +
                        " id_propietario= "+ this.getPropietario().getDocumento() +", " +
                        "id_cliente= "+ this.getCliente().getDocumento() +", " +
                        "fecha_modificacion='"+this.getFechaModificacion()+"' " +
                        "WHERE id_propiedad = "+id_propiedad+"");

                st2.executeUpdate();
                st2.close();

                con.close();
                return true;
            }
            return false;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean retirarEdificio(String id) {
        try{
            if(consultarDisponibilidadEdificio(id)) {
                Conexion cx =  new Conexion();
                Connection con = cx.getConexion();

                int id_propiedad = 0;
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("UPDATE propiedad p\n" +
                        "SET disponible = 'false'\n" +
                        "FROM edificio e\n" +
                        "WHERE (e.id_propiedad = p.id AND e.id = '"+id+"') RETURNING p.id");

                if(rs.next()) id_propiedad = rs.getInt(1);
                rs.close();
                st.close();

                PreparedStatement st2 = con.prepareStatement("UPDATE historial_propiedad SET" +
                        " fecha_modificacion='"+this.getFechaModificacion()+"' " +
                        "WHERE id_propiedad = "+id_propiedad+"");

                st2.executeUpdate();
                st2.close();

                con.close();
                return true;
            }
            return false;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean consultarDisponibilidadEdificio(String id) {
        try{
            Conexion cx =  new Conexion();
            Connection con = cx.getConexion();
            boolean r = false;

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT p.disponible\n" +
                    "FROM propiedad p\n" +
                    "INNER JOIN edificio e on p.id = e.id_propiedad\n" +
                    "WHERE e.id = '"+id+"'");
            if(rs.next()) r = rs.getBoolean(1);
            rs.close();
            st.close();

            con.close();
            return r;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
