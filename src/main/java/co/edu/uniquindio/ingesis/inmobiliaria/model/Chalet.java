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

    public List<Chalet> devolverChalets(String disponibilidad) {
        try{
            List<Chalet> chalets = new ArrayList<>();
            Conexion cx =  new Conexion();
            Connection con = cx.getConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT p.direccion, p.precio, p.disposicion_propiedad, p.area, p.unidades_area,\n" +
                    "       v.numero_habitaciones, v.numero_banos, v.material,\n" +
                    "       c.numero_pisos, c2.agua_potable, c2.alcantarillado, c2.pozo_septico,\n" +
                    "       c2.internet, c2.energia_electrica, c2.gas_domiciliario\n" +
                    "FROM propiedad p\n" +
                    "         INNER JOIN vivienda v ON v.id_propiedad = p.id\n" +
                    "         INNER JOIN casa c ON c.id_vivienda = v.id\n" +
                    "        INNER JOIN chalet c2 on c.id = c2.id_casa\n" +
                    "WHERE p.disponible = '"+disponibilidad+"'");
            while (rs.next()) {
                DisposicionPropiedad dp = DisposicionPropiedad.valueOf(rs.getString(3));
                TipoArea ta = TipoArea.valueOf(rs.getString(5));
                Chalet c = new Chalet("", rs.getString(1), true, rs.getDouble(2), new Empleado(), LocalDateTime.now(), dp, rs.getFloat(4), ta, rs.getInt(9), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getBoolean(10), rs.getBoolean(11), rs.getBoolean(12), rs.getBoolean(13), rs.getBoolean(14), rs.getBoolean(15));
                chalets.add(c);
            }
            rs.close();
            st.close();

            con.close();
            return chalets;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
