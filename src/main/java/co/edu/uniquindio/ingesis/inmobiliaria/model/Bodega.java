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
public class Bodega extends Propiedad {

    private TipoBodegaLote tipo;

    public Bodega(String identificador, String direccion, Boolean disponible, Double precio, Empleado empleado, LocalDateTime fechaCreacion, DisposicionPropiedad disposicionPropiedad, Float valorArea, TipoArea unidadesArea, TipoBodegaLote tipo) {
        super(identificador, direccion, disponible, precio, empleado, fechaCreacion, disposicionPropiedad, valorArea, unidadesArea);
        this.tipo = tipo;
    }

    public boolean registrarBodega(int id_propiedad) {
        try{
            Conexion cx =  new Conexion();
            Connection con = cx.getConexion();

            PreparedStatement st = con.prepareStatement("INSERT INTO bodega (id, tipo, id_propiedad) VALUES(?,?,?)");
            st.setString(1, this.getIdentificador());
            st.setString(2, String.valueOf(this.tipo));
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

    public boolean alquilarBodega(String id) {
        try{
            if(consultarDisponibilidadBodega(id)) {
                Conexion cx =  new Conexion();
                Connection con = cx.getConexion();

                int id_propiedad = 0;
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("UPDATE propiedad p\n" +
                        "SET disponible = 'false'\n" +
                        "FROM bodega b\n" +
                        "WHERE (b.id_propiedad = p.id AND b.id = '"+id+"') RETURNING p.id");

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

    public boolean venderBodega(String id) {
        try{
            if(consultarDisponibilidadBodega(id)) {
                Conexion cx =  new Conexion();
                Connection con = cx.getConexion();

                int id_propiedad = 0;
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("UPDATE propiedad p\n" +
                        "SET disponible = 'false'\n" +
                        "FROM bodega b\n" +
                        "WHERE (b.id_propiedad = p.id AND b.id = '"+id+"') RETURNING p.id");

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

    public boolean retirarBodega(String id) {
        try{
            if(consultarDisponibilidadBodega(id)) {
                Conexion cx =  new Conexion();
                Connection con = cx.getConexion();

                int id_propiedad = 0;
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("UPDATE propiedad p\n" +
                        "SET disponible = 'false'\n" +
                        "FROM bodega b\n" +
                        "WHERE (b.id_propiedad = p.id AND b.id = '"+id+"') RETURNING p.id");

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

    public boolean consultarDisponibilidadBodega(String id) {
        try{
            Conexion cx =  new Conexion();
            Connection con = cx.getConexion();
            boolean r = false;

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT p.disponible\n" +
                    "FROM propiedad p\n" +
                    "INNER JOIN bodega b on p.id = b.id_propiedad\n" +
                    "WHERE b.id = '"+id+"'");
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

    public List<Bodega> devolverBodegas(String disponibilidad) {
        try{
            List<Bodega> bodegas = new ArrayList<>();
            Conexion cx =  new Conexion();
            Connection con = cx.getConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT p.direccion, p.precio, p.disposicion_propiedad, p.area, p.unidades_area,\n" +
                    "       b.tipo\n" +
                    "FROM propiedad p\n" +
                    "         INNER JOIN bodega b ON b.id_propiedad = p.id\n" +
                    "WHERE p.disponible = '"+disponibilidad+"'");
            while (rs.next()) {
                DisposicionPropiedad dp = DisposicionPropiedad.valueOf(rs.getString(3));
                TipoArea ta = TipoArea.valueOf(rs.getString(5));
                TipoBodegaLote tb = TipoBodegaLote.valueOf(rs.getString(6));
                Bodega b = new Bodega("", rs.getString(1), true, rs.getDouble(2), new Empleado(), LocalDateTime.now(), dp, rs.getFloat(4), ta, tb);
                bodegas.add(b);
            }
            rs.close();
            st.close();

            con.close();
            return bodegas;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
