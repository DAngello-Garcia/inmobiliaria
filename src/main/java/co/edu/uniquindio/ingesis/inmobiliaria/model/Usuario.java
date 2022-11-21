package co.edu.uniquindio.ingesis.inmobiliaria.model;

import co.edu.uniquindio.ingesis.inmobiliaria.util.Conexion;
import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Getter
@Setter
public class Usuario {

    private String correo;
    private String clave;
    private String fraseSeguridad;
    private boolean estado;

    public Usuario(String correo, String clave, String fraseSeguridad, boolean estado) {
        this.correo = correo;
        this.clave = clave;
        this.fraseSeguridad = fraseSeguridad;
        this.estado = estado;
    }

    public Usuario(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
    }

    public int registrarUsuario(int rol) {
        try{
            Conexion cx =  new Conexion();
            Connection con = cx.getConexion();

            PreparedStatement st = con.prepareStatement("INSERT INTO usuario (email, password, frase, id_rol) VALUES(?,?,?,?) RETURNING id", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, this.getCorreo());
            st.setString(2, this.getClave());
            st.setString(3, this.getFraseSeguridad());
            st.setInt(4, rol);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            int id = 0;
            if (rs.next()) id = rs.getInt(1);
            st.close();
            st.close();

            con.close();
            return id;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public int consultarUsuario(String correo) {
        try{
            Conexion cx =  new Conexion();
            Connection con = cx.getConexion();
            int count = 0;

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM usuario WHERE email = '"+correo+"'");
            while (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            st.close();

            con.close();
            return count;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public boolean consultarEstadoUsuario(String correo) {
        try{
            Conexion cx =  new Conexion();
            Connection con = cx.getConexion();
            boolean count = false;

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT estado FROM usuario WHERE email = '"+correo+"'");
            while (rs.next()) {
                count = rs.getBoolean(1);
            }
            rs.close();
            st.close();

            con.close();
            return count;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String recuperarClave(String correo, String frase) {
        try{
            if(consultarUsuario(correo) > 0 && verificarFrase(correo, frase)) {
                Conexion cx =  new Conexion();
                Connection con = cx.getConexion();
                String clave = "";

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT password FROM usuario WHERE email = '"+correo+"'");
                while (rs.next()) {
                    clave = rs.getString(1);
                }
                rs.close();
                st.close();

                con.close();
                return clave;
            }
            return "";
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "";
        }
    }

    public boolean verificarFrase(String correo, String frase) {
        try{
            Conexion cx =  new Conexion();
            Connection con = cx.getConexion();
            String frase_db = "";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT frase FROM usuario WHERE email = '"+correo+"'");
            while (rs.next()) {
                frase_db += rs.getString(1);
            }
            rs.close();
            st.close();

            con.close();

            return frase_db.equals(frase);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public int login() {
        try{
            if(consultarUsuario(this.correo) != 0) {
                Conexion cx =  new Conexion();
                Connection con = cx.getConexion();
                int id = 0;

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT id_rol FROM usuario WHERE email = '"+this.correo+"' AND password = '"+this.clave+"'");
                while (rs.next()) {
                    id = rs.getInt(1);
                }
                rs.close();
                st.close();

                con.close();

                return id;
            }
            return 0;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
