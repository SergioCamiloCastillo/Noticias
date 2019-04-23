/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noticias;

public class Productos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NoticeDao notice = new NoticeDaoImpl();
        
        // agregar nuevo producto
        notice.insert(new Notice(6,"Arroz", 1.50));
        
        // obtener el producto con ID = 100 e imprimirlo en pantalla
        Notice p = notice.read(2);
        System.out.println(p);
        
        // eliminar el producto con ID = 100
        //product.delete(1);
    }
    
}
