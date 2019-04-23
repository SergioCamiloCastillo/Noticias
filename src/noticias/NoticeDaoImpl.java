/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noticias;
    import noticias.Notice;
/**
 *
 * @author User
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NoticeDaoImpl implements NoticeDao {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/";
    static final String DB_USER = "sergio";
    static final String DB_NAME = "curd_test";

    static final String DB_PASS = "sergio";

    private void registerDriver() {
        try {   
            Class.forName(JDBC_DRIVER).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.err.println("ERROR");
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Notice product) {
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sergio", "root", "");

            //conn=DriverManager.getConnection(DB_URL+DB_NAME+DB_USER+DB_PASS);
            try (Statement stmt = conn.createStatement()) {
                // enviar el comando insert
                stmt.executeUpdate("insert into uni values ("
                        + product.getId() + ",'"
                        + product.getName() + "',"
                        + product.getPrice() + ");");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(Notice empleado) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Integer id) {
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sergio", "root", "");
            try (Statement stmt = conn.createStatement()) {
                // enviar el comando delete
                stmt.executeUpdate("delete from uni where id = " + id + ";");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Notice read(Integer id) {
        Connection conn = null;
        Notice product = null;

        try {
            registerDriver();
            // abrir la conexion
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sergio", "root", "");
            // consulta select (selecciona el producto con ID especificado)
            try (PreparedStatement ps = conn.prepareStatement(
                    "select * from uni where id = ?")) {
                // indicar el ID que buscamos
                ps.setInt(1, id);
                // ejecutar la consulta
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // obtener cada una de la columnas y mapearlas a la clase Product
                        product = new Notice(id,
                                rs.getString("nombre"),
                                rs.getDouble("apellido"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return product;
    }
}
