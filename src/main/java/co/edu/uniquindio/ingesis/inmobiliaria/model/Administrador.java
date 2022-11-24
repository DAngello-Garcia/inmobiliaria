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
public class Administrador extends Persona{
    private List<Empleado> empleados;
    private Usuario usuario;

    public Administrador(String nombre, Integer documento, String celular, Usuario usuario) {
        super(nombre, documento, celular);
        this.usuario = usuario;
    }

    public Administrador(String nombre, Integer documento, String celular) {
        super(nombre, documento, celular);
    }

    public boolean registrarEmpleado(Empleado empleado){
        try{
            Conexion cx =  new Conexion();
            Connection con = cx.getConexion();
            int user_id = empleado.getUsuario().registrarUsuario(2);

            PreparedStatement st = con.prepareStatement("INSERT INTO empleado (id, nombre, celular, id_usuario) VALUES(?,?,?,?)");
            st.setInt(1, empleado.getDocumento());
            st.setString(2, empleado.getNombre());
            st.setString(3, empleado.getCelular());
            st.setInt(4, user_id);
            st.executeUpdate();
            st.close();

            con.close();
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean modificarEmpleado(Empleado empleado) {
        try{
            if(empleado.getUsuario().consultarIdUsuario(empleado.getUsuario().getCorreo()) > 0) {
                Conexion cx =  new Conexion();
                Connection con = cx.getConexion();

                PreparedStatement st = con.prepareStatement("UPDATE empleado SET nombre = '"+empleado.getNombre()+"', celular = '"+empleado.getCelular()+"'");

                st.executeUpdate();
                st.close();

                con.close();
                return true;
            }
            return false;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean desactivarEmpleado(Empleado empleado) {
        try{
            String correo = empleado.getUsuario().getCorreo();
            if(empleado.getUsuario().consultarIdUsuario(correo) > 0 && empleado.getUsuario().consultarEstadoUsuario(correo)) {
                Conexion cx =  new Conexion();
                Connection con = cx.getConexion();

                String user_email = empleado.getUsuario().getCorreo();
                int user_id = empleado.getUsuario().consultarIdUsuario(user_email);

                PreparedStatement st = con.prepareStatement("UPDATE usuario SET estado = '"+empleado.getUsuario().isEstado()+"' WHERE id = "+user_id+"");

                st.executeUpdate();
                st.close();

                con.close();
                return true;
            }
            return false;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean eliminarEmpleado(Empleado empleado) {
        try{
            if(empleado.getUsuario().consultarIdUsuario(empleado.getUsuario().getCorreo()) > 0) {
                Conexion cx =  new Conexion();
                Connection con = cx.getConexion();

                String user_email = empleado.getUsuario().getCorreo();
                int user_id = empleado.getUsuario().consultarIdUsuario(user_email);

                PreparedStatement st2 = con.prepareStatement("DELETE FROM empleado WHERE id = "+empleado.getDocumento()+"");

                st2.executeUpdate();
                st2.close();

                PreparedStatement st = con.prepareStatement("DELETE FROM usuario WHERE id = "+user_id+"");

                st.executeUpdate();
                st.close();

                con.close();
                return true;
            }
            return false;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Empleado> consultarEmpleados() {
        try{
            List<Empleado> e = new ArrayList<>();
            Conexion cx =  new Conexion();
            Connection con = cx.getConexion();

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT u.email, e.id, e.nombre, e.celular\n" +
                    "FROM usuario u\n" +
                    "         INNER JOIN empleado e on u.id = e.id_usuario\n" +
                    "WHERE u.estado = 'true'");
            while (rs.next()) {
                Usuario u = new Usuario(rs.getString(1));
                Empleado em = new Empleado(rs.getString(3), rs.getInt(2), rs.getString(4), u);
                e.add(em);
            }
            rs.close();
            st.close();

            con.close();
            return e;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
