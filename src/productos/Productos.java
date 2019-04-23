/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

public class Productos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProductDao product = new ProductDaoImpl();
        
        // agregar nuevo producto
        product.insert(new Product(100, "Arroz", 1.50));
        
        // obtener el producto con ID = 100 e imprimirlo en pantalla
        Product p = product.read(100);
        System.out.println(p);
        
        // eliminar el producto con ID = 100
        product.delete(100);
    }
    
}
