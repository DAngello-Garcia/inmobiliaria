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
public class Apartamento extends Vivienda {
    private Boolean balcon;
    private Boolean ascensor;
    private Integer piso;
    private Float valorAdministracion;
    private Integer numeroParqueaderos;

    public Apartamento(String identificador, String direccion, Boolean disponible, Double precio, Empleado empleado, LocalDateTime fechaCreacion, DisposicionPropiedad disposicionPropiedad, Float valorArea, TipoArea unidadesArea, Integer numeroHabitaciones, Integer numeroBanos, String material, Boolean balcon, Boolean ascensor, Integer piso, Float valorAdministracion, Integer numeroParqueaderos) {
        super(identificador, direccion, disponible, precio, empleado, fechaCreacion, disposicionPropiedad, valorArea, unidadesArea, numeroHabitaciones, numeroBanos, material);
        this.balcon = balcon;
        this.ascensor = ascensor;
        this.piso = piso;
        this.valorAdministracion = valorAdministracion;
        this.numeroParqueaderos = numeroParqueaderos;
    }

    public boolean registrarApartamento(int id_vivienda) {
        try{
            Conexion cx =  new Conexion();
            Connection con = cx.getConexion();

            PreparedStatement st = con.prepareStatement("INSERT INTO apartamento (id, balcon, piso, valor_administracion, numero_parqueaderos, ascensor, id_vivienda) VALUES(?,?,?,?,?,?,?)");
            st.setString(1, this.getIdentificador());
            st.setBoolean(2, this.balcon);
            st.setInt(3, this.piso);
            st.setFloat(4, this.valorAdministracion);
            st.setInt(5, this.numeroParqueaderos);
            st.setBoolean(6, this.ascensor);
            st.setInt(7, id_vivienda);

            st.executeUpdate();
            st.close();

            con.close();
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean alquilarApartamento(String id) {
        try{
            if(consultarDisponibilidadApartamento(id)) {
                Conexion cx =  new Conexion();
                Connection con = cx.getConexion();

                int id_propiedad = 0;
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("UPDATE propiedad p\n" +
                        "SET disponible = 'false'\n" +
                        "FROM vivienda v, apartamento a\n" +
                        "WHERE (v.id_propiedad = p.id AND a.id_vivienda = v.id AND a.id = '"+id+"') RETURNING p.id");

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

    public boolean venderApartamento(String id) {
        try{
            if(consultarDisponibilidadApartamento(id)) {
                Conexion cx =  new Conexion();
                Connection con = cx.getConexion();

                int id_propiedad = 0;
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("UPDATE propiedad p\n" +
                        "SET disponible = 'false'\n" +
                        "FROM vivienda v, apartamento a\n" +
                        "WHERE (v.id_propiedad = p.id AND a.id_vivienda = v.id AND a.id = '"+id+"') RETURNING p.id");

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

    public boolean retirarApartamento(String id) {
        try{
            if(consultarDisponibilidadApartamento(id)) {
                Conexion cx =  new Conexion();
                Connection con = cx.getConexion();

                int id_propiedad = 0;
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("UPDATE propiedad p\n" +
                        "SET disponible = 'false'\n" +
                        "FROM vivienda v, apartamento a\n" +
                        "WHERE (v.id_propiedad = p.id AND a.id_vivienda = v.id AND a.id = '"+id+"') RETURNING p.id");

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

    public boolean consultarDisponibilidadApartamento(String id) {
        try{
            Conexion cx =  new Conexion();
            Connection con = cx.getConexion();
            boolean r = false;

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT p.disponible\n" +
                    "FROM propiedad p\n" +
                    "INNER JOIN vivienda v ON v.id_propiedad = p.id\n" +
                    "INNER JOIN apartamento a on a.id_vivienda = v.id\n" +
                    "WHERE a.id = '"+id+"'");
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

    public List<Apartamento> devolverApartamentos(String disponibilidad) {
        try{
            List<Apartamento> apt = new ArrayList<>();
            Conexion cx =  new Conexion();
            Connection con = cx.getConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT p.direccion, p.precio, p.disposicion_propiedad, p.area, p.unidades_area, \n" +
                    "       v.numero_habitaciones, v.numero_banos, v.material,\n" +
                    "       a.balcon, a.ascensor, a.piso, a.valor_administracion, a.numero_parqueaderos\n" +
                    "FROM propiedad p\n" +
                    "         INNER JOIN vivienda v ON v.id_propiedad = p.id\n" +
                    "         INNER JOIN apartamento a ON a.id_vivienda = v.id\n" +
                    "WHERE p.disponible = '"+disponibilidad+"'");
            while (rs.next()) {
                DisposicionPropiedad dp = DisposicionPropiedad.valueOf(rs.getString(3));
                TipoArea ta = TipoArea.valueOf(rs.getString(5));
                Apartamento a = new Apartamento("", rs.getString(1), true, rs.getDouble(2), new Empleado(), LocalDateTime.now(), dp, rs.getFloat(4), ta, rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getBoolean(9), rs.getBoolean(10), rs.getInt(11), rs.getFloat(12), rs.getInt(13));
                apt.add(a);
            }
            rs.close();
            st.close();

            con.close();
            return apt;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
