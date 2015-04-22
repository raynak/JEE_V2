/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;
import jpa.Books;

/**
 *
 * @author raynak
 */
@Local
public interface BooksFacadeLocal {

    void create(Books books);

    void edit(Books books);

    void remove(Books books);

    Books find(Object id);

    List<Books> findAll();

    List<Books> findRange(int[] range);

    int count();
    
}
