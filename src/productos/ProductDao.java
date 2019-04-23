/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

/**
 *
 * @author User
 */
public interface ProductDao {
    public void insert(Product product);
    public void update(Product product);
    public void delete(Integer id);
    public Product read(Integer id);
}
