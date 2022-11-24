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
public class Parqueadero extends Propiedad{

    public Parqueadero(String identificador, String direccion, Boolean disponible, Double precio, Empleado empleado, LocalDateTime fechaCreacion, DisposicionPropiedad disposicionPropiedad, Float valorArea, TipoArea unidadesArea) {
        super(identificador, direccion, disponible, precio, empleado, fechaCreacion, disposicionPropiedad, valorArea, unidadesArea);
    }

    public boolean registrarParqueadero(int id_propiedad) {
        try{
            Conexion cx =  new Conexion();
            Connection con = cx.getConexion();

            PreparedStatement st = con.prepareStatement("INSERT INTO parqueadero (id, id_propiedad) VALUES(?,?)");
            st.setString(1, this.getIdentificador());
            st.setInt(2, id_propiedad);

            st.executeUpdate();
            st.close();

            con.close();
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean alquilarParqueadero(String id) {
        try{
            if(consultarDisponibilidadParqueadero(id)) {
                Conexion cx =  new Conexion();
                Connection con = cx.getConexion();

                int id_propiedad = 0;
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("UPDATE propiedad p\n" +
                        "SET disponible = 'false'\n" +
                        "FROM parqueadero p2\n" +
                        "WHERE (p2.id_propiedad = p.id AND p2.id = '"+id+"') RETURNING p.id");

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

    public boolean venderParqueadero(String id) {
        try{
            if(consultarDisponibilidadParqueadero(id)) {
                Conexion cx =  new Conexion();
                Connection con = cx.getConexion();

                int id_propiedad = 0;
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("UPDATE propiedad p\n" +
                        "SET disponible = 'false'\n" +
                        "FROM parqueadero p2\n" +
                        "WHERE (p2.id_propiedad = p.id AND p2.id = '"+id+"') RETURNING p.id");

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

    public boolean retirarParqueadero(String id) {
        try{
            if(consultarDisponibilidadParqueadero(id)) {
                Conexion cx =  new Conexion();
                Connection con = cx.getConexion();

                int id_propiedad = 0;
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("UPDATE propiedad p\n" +
                        "SET disponible = 'false'\n" +
                        "FROM parqueadero p2\n" +
                        "WHERE (p2.id_propiedad = p.id AND p2.id = '"+id+"') RETURNING p.id");

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

    public boolean consultarDisponibilidadParqueadero(String id) {
        try{
            Conexion cx =  new Conexion();
            Connection con = cx.getConexion();
            boolean r = false;

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT p.disponible\n" +
                    "FROM propiedad p\n" +
                    "INNER JOIN parqueadero p2 on p.id = p2.id_propiedad\n" +
                    "WHERE p2.id = '"+id+"'");
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

    public List<Parqueadero> devolverParqueaderos(String disponibilidad) {
        try{
            List<Parqueadero> parqueaderos = new ArrayList<>();
            Conexion cx =  new Conexion();
            Connection con = cx.getConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT p.direccion, p.precio, p.disposicion_propiedad, p.area, p.unidades_area\n" +
                    "FROM propiedad p\n" +
                    "         INNER JOIN parqueadero pa on p.id = pa.id_propiedad\n" +
                    "WHERE p.disponible = '"+disponibilidad+"'");
            while (rs.next()) {
                DisposicionPropiedad dp = DisposicionPropiedad.valueOf(rs.getString(3));
                TipoArea ta = TipoArea.valueOf(rs.getString(5));
                Parqueadero p = new Parqueadero("", rs.getString(1), true, rs.getDouble(2), new Empleado(), LocalDateTime.now(), dp, rs.getFloat(4), ta);
                parqueaderos.add(p);
            }
            rs.close();
            st.close();

            con.close();
            return parqueaderos;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
