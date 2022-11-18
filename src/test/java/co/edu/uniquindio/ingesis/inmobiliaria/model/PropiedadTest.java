package co.edu.uniquindio.ingesis.inmobiliaria.model;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import co.edu.uniquindio.ingesis.inmobiliaria.util.Conexion;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PropiedadTest {

    @Test
    void registrarEmpleado() {
        Usuario adminUser = new Usuario("admin", "clave", "frase", true);
        Administrador admin = new Administrador("admin", 1, "300", adminUser);
        Usuario empleUsuario = new Usuario("emple1@inmo.com", "clave", "frase", true);
        Empleado empleado = new Empleado("emple1", 1, "300", empleUsuario);
        assertTrue(admin.registrarEmpleado(empleado));
    }

    @Test
    void registrarCliente() {
        Usuario empleUser = new Usuario("emple1@inmo.com", "password", "frase_emple", true);
        Empleado empleado = new Empleado("empleado1", 1, "300", empleUser);
        Cliente cliente = new Cliente("cliente1", 1, "300");
        empleado.registrarCliente(cliente);
    }

    @Test
    void registrarPropietario() {
        Usuario empleUser = new Usuario("emple1@inmo.com", "password", "frase_emple", true);
        Empleado empleado = new Empleado("empleado1", 1, "300", empleUser);
        Cliente cliente = new Cliente("cliente1", 1, "300");
        Propietario propietario = new Propietario("propietario1", 1, "300");
        empleado.registrarPropietario(propietario);
    }

    @Test
    void registrarApartamento() {
        Usuario empleUser = new Usuario("emple1@inmo.com", "password", "frase_emple", true);
        Empleado empleado = new Empleado("empleado1", 1, "300", empleUser);
        DisposicionPropiedad dp = DisposicionPropiedad.VENTA;
        TipoArea t = TipoArea.M2;
        LocalDateTime fecha = LocalDateTime.now();
        Apartamento a = new Apartamento("apartamento1", "Calle 123", true, 5000.0, empleado, fecha, dp, 20.5f, t, 2, 2, "material", true, true, 3, 50f, 1);
        int id_propiedad = a.registrarPropiedad();
        int id_vivienda = a.registrarVivienda(id_propiedad);
        assertTrue(a.registrarApartamento(id_vivienda));
    }

    @Test
    void registrarCasa() {
        Usuario empleUser = new Usuario("emple1@inmo.com", "password", "frase_emple", true);
        Empleado empleado = new Empleado("empleado1", 1, "300", empleUser);
        DisposicionPropiedad dp = DisposicionPropiedad.VENTA;
        TipoArea t = TipoArea.M2;
        LocalDateTime fecha = LocalDateTime.now();
        Casa c = new Casa("casa1", "Calle 123", true, 5000.0, empleado, fecha, dp, 20.5f, t, 2, 2, 2, "material casa");
        int id_propiedad = c.registrarPropiedad();
        int id_vivienda = c.registrarVivienda(id_propiedad);
        assertTrue(c.registrarCasa(id_vivienda));
    }

    @Test
    void registrarChalet() {
        Usuario empleUser = new Usuario("emple1@inmo.com", "password", "frase_emple", true);
        Empleado empleado = new Empleado("empleado1", 1, "300", empleUser);
        DisposicionPropiedad dp = DisposicionPropiedad.VENTA;
        TipoArea t = TipoArea.M2;
        LocalDateTime fecha = LocalDateTime.now();
        Chalet c = new Chalet("chalet1", "Calle 123", true, 5000.0, empleado, fecha, dp, 20.5f, t, 2, 2, 2, "material chalet", false, true, true, true, true, false);
        int id_propiedad = c.registrarPropiedad();
        int id_vivienda = c.registrarVivienda(id_propiedad);
        c.registrarCasa(id_vivienda);
        assertTrue(c.registrarChalet());
    }

    @Test
    void registrarBodega() {
        Usuario empleUser = new Usuario("emple1@inmo.com", "password", "frase_emple", true);
        Empleado empleado = new Empleado("empleado1", 1, "300", empleUser);
        DisposicionPropiedad dp = DisposicionPropiedad.ALQUILER;
        TipoArea t = TipoArea.M2;
        TipoBodegaLote tb = TipoBodegaLote.URBANA;
        LocalDateTime fecha = LocalDateTime.now();
        Bodega b = new Bodega("bodega1", "Calle 123", true, 5000.0, empleado, fecha, dp, 20.5f, t, tb);
        int id_propiedad = b.registrarPropiedad();
        assertTrue(b.registrarBodega(id_propiedad));
    }

    @Test
    void registrarEdificio() {
        Usuario empleUser = new Usuario("emple1@inmo.com", "password", "frase_emple", true);
        Empleado empleado = new Empleado("empleado1", 1, "300", empleUser);
        DisposicionPropiedad dp = DisposicionPropiedad.VENTA;
        TipoArea t = TipoArea.M2;
        LocalDateTime fecha = LocalDateTime.now();
        Edificio e = new Edificio("edificio1", "Calle 123", true, 5000.0, empleado, fecha, dp, 20.5f, t, 7);
        int id_propiedad = e.registrarPropiedad();
        assertTrue(e.registrarEdificio(id_propiedad));
    }

    @Test
    void registrarLote() {
        Usuario empleUser = new Usuario("emple1@inmo.com", "password", "frase_emple", true);
        Empleado empleado = new Empleado("empleado1", 1, "300", empleUser);
        DisposicionPropiedad dp = DisposicionPropiedad.ALQUILER;
        TipoBodegaLote tb = TipoBodegaLote.URBANA;
        TipoArea t = TipoArea.M2;
        LocalDateTime fecha = LocalDateTime.now();
        Lote l = new Lote("lote1", "Calle 123", true, 5000.0, empleado, fecha, dp, 20.5f, t, tb);
        int id_propiedad = l.registrarPropiedad();
        assertTrue(l.registrarLote(id_propiedad));
    }

    @Test
    void registrarParqueadero() {
        Usuario empleUser = new Usuario("emple1@inmo.com", "password", "frase_emple", true);
        Empleado empleado = new Empleado("empleado1", 1, "300", empleUser);
        DisposicionPropiedad dp = DisposicionPropiedad.VENTA;
        TipoArea t = TipoArea.M2;
        LocalDateTime fecha = LocalDateTime.now();
        Parqueadero p = new Parqueadero("parqueadero1", "Calle 123", true, 5000.0, empleado, fecha, dp, 20.5f, t);
        int id_propiedad = p.registrarPropiedad();
        assertTrue(p.registrarParqueadero(id_propiedad));
    }

    @Test
    void alquilarLote() {
        Usuario empleUser = new Usuario("emple1@inmo.com", "password", "frase_emple", true);
        Empleado empleado = new Empleado("empleado1", 1, "300", empleUser);
        Cliente cliente = new Cliente("cliente1", 1, "300");
        DisposicionPropiedad dp = DisposicionPropiedad.ALQUILER;
        TipoBodegaLote tb = TipoBodegaLote.URBANA;
        TipoArea t = TipoArea.M2;
        LocalDateTime fecha = LocalDateTime.now();
        Lote l = new Lote("lote1", "Calle 123", true, 5000.0, empleado, fecha, dp, 20.5f, t, tb);
        l.setCliente(cliente);
        l.setDisponible(false);
        l.setFechaModificacion(LocalDateTime.now());
        assertTrue(l.alquilarLote(l.getIdentificador()));
    }

    @Test
    void venderEdificio() {
        Usuario empleUser = new Usuario("emple1@inmo.com", "password", "frase_emple", true);
        Empleado empleado = new Empleado("empleado1", 1, "300", empleUser);
        Cliente cliente = new Cliente("cliente1", 1, "300");
        Propietario propietario = new Propietario("propietario1", 1, "300");
        DisposicionPropiedad dp = DisposicionPropiedad.VENTA;
        TipoArea t = TipoArea.M2;
        LocalDateTime fecha = LocalDateTime.now();
        Edificio e = new Edificio("edificio1", "Calle 123", true, 5000.0, empleado, fecha, dp, 20.5f, t, 7);
        e.setCliente(cliente);
        e.setPropietario(propietario);
        e.setFechaModificacion(LocalDateTime.now());
        assertTrue(e.venderEdificio(e.getIdentificador()));
    }

    @Test
    void retirarChalet() {
        Usuario empleUser = new Usuario("emple1@inmo.com", "password", "frase_emple", true);
        Empleado empleado = new Empleado("empleado1", 1, "300", empleUser);
        DisposicionPropiedad dp = DisposicionPropiedad.ALQUILER;
        TipoArea t = TipoArea.M2;
        LocalDateTime fecha = LocalDateTime.now();
        Chalet c = new Chalet("chalet1", "Calle 123", true, 5000.0, empleado, fecha, dp, 20.5f, t, 2, 2, 2, "material chalet", false, true, true, true, true, false);
        c.setFechaModificacion(LocalDateTime.now());
        assertTrue(c.retirarCasa(c.getIdentificador()));
    }

    @Test
    void consultarDisponibilidadCasa() {
        Usuario empleUser = new Usuario("emple1@inmo.com", "password", "frase_emple", true);
        Empleado empleado = new Empleado("empleado1", 1, "300", empleUser);
        DisposicionPropiedad dp = DisposicionPropiedad.ALQUILER;
        TipoArea t = TipoArea.M2;
        LocalDateTime fecha = LocalDateTime.now();
        Casa c = new Casa("casa1", "Calle 123", true, 5000.0, empleado, fecha, dp, 20.5f, t, 2, 2, 2, "material casa");
        assertTrue(c.consultarDisponibilidadCasa(c.getIdentificador()));
    }
}