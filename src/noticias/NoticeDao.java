/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noticias;

/**
 *
 * @author User
 */
public interface NoticeDao {
    public void insert(Notice product);
    public void update(Notice product);
    public void delete(Integer id);
    public Notice read(Integer id);
}
