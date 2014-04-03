package services_test;


import ar.edu.unq.persistencia1.Usuario;
import ar.edu.unq.persistencia1.exceptions.UsuarioYaExisteException;
import ar.edu.unq.persistencia1.homes.RepositorioDeUsuarios;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

public class TestRepositorioDeUsuario {

    RepositorioDeUsuarios service;
    Connection connection;
    PreparedStatement ps;

    @Before
    public void setUp() throws Exception {
        this.service = new RepositorioDeUsuarios("aterrizage_test");
        this.service.emptyTable("Usuario");
        this.connection = this.service.getConnection();

    }

    @After
    public void tearDown() throws Exception {
        this.service.emptyTable("Usuario");
        if (this.ps != null)
            this.ps.close();
        if (this.connection != null)
            this.connection.close();
    }

    @Test
    public void testTestExistUserReturnsTrue() throws Exception {
        this.connection = this.service.getConnection();
        this.ps = this.connection.prepareStatement("INSERT INTO Usuario (nombreDeUSuario, email) VALUES (?,?)");
        this.ps.setString(1, "unNombreDeUsuario");
        this.ps.setString(2, "unEmail");
        this.ps.execute();
        this.ps.close();
        this.connection.close();
        Usuario user = new Usuario("nombre", "apellido", "unNombreDeUsuario", "email", new Date());
        boolean result = this.service.existeUsuario(user);
        Assert.assertTrue(result);

    }

    @Test
    public void testTestExistUserReturnsFalse() throws Exception {
        Usuario user = new Usuario("nombre", "apellido", "unNombreDeUsuario", "email", new Date());
        boolean result = this.service.existeUsuario(user);
        Assert.assertFalse(result);

    }

    @Test
    public void testGuardarUsuario() throws Exception {
        Usuario usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date());
        this.service.guardarUsuario(usuario);
        Assert.assertTrue(this.service.existeUsuario(usuario));

    }

    @Test(expected = UsuarioYaExisteException.class)
    public void testLanzaExcepcionConUsuarioExistente() throws Exception {
        Usuario usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date());
        this.service.guardarUsuario(usuario);
        this.service.guardarUsuario(usuario);


    }

    @Test
    public void testSetearCodigoUsuario() throws Exception {
        Usuario usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date());
        RepositorioDeUsuarios repo = new RepositorioDeUsuarios("aterrizage_test");
        this.service.guardarUsuario(usuario);
        repo.guardarCodigo(usuario, "123");
        Assert.assertTrue(repo.existeCodigo("123"));


    }

    @Test
    public void testGetUsuario() throws Exception {
        Usuario usuario = new Usuario("Lalocura", "DeLalo", "Lalo", "Laloooo", new Date());
        this.service.guardarUsuario(usuario);
        this.setPassword(usuario, "123456");
        Usuario user = this.service.getUsuario(usuario.getNombreDeUsuario(), "123456");
        boolean sameUser = user.getNombreDeUsuario().equals(usuario.getNombreDeUsuario());
        Assert.assertTrue(sameUser);
    }

    /**
     * Remove this method after implement cambiarPassword in Sistema class.
     * @param usuario
     */
    public void setPassword(Usuario usuario, String password) throws Exception {
        Connection connection = this.service.getConnection();
        PreparedStatement ps = connection.prepareStatement("UPDATE Usuario SET password = " + password + " WHERE nombreDeUsuario = '" + usuario.getNombreDeUsuario() + "'");
        ps.execute();
        ps.close();
        connection.close();
    }


}
