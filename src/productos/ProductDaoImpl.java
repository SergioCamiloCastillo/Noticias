/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;
    import productos.Product;
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

public class ProductDaoImpl implements ProductDao {

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
    public void insert(Product product) {
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/curd_test", "root", "");

            //conn=DriverManager.getConnection(DB_URL+DB_NAME+DB_USER+DB_PASS);
            try (Statement stmt = conn.createStatement()) {
                // enviar el comando insert
                stmt.executeUpdate("insert into datos values ("
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
    public void update(Product empleado) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Integer id) {
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexion 
            conn = DriverManager.getConnection(DB_URL+DB_NAME+ DB_USER+ DB_PASS);
            try (Statement stmt = conn.createStatement()) {
                // enviar el comando delete
                stmt.executeUpdate("delete from datos where id = " + id + ";");
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
    public Product read(Integer id) {
        Connection conn = null;
        Product product = null;

        try {
            registerDriver();
            // abrir la conexion
            conn = DriverManager.getConnection(DB_URL);
            // consulta select (selecciona el producto con ID especificado)
            try (PreparedStatement ps = conn.prepareStatement(
                    "select * from datos where id = ?")) {
                // indicar el ID que buscamos
                ps.setInt(1, id);
                // ejecutar la consulta
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // obtener cada una de la columnas y mapearlas a la clase Product
                        product = new Product(id,
                                rs.getString("name"),
                                rs.getDouble("price"));
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
