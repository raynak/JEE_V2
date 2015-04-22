/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Remote;
import jpa.Shoppingcartitems;

/**
 *
 * @author raynak
 */
@Remote
public interface ShoppingcartitemsFacadeRemote {

    void create(Shoppingcartitems shoppingcartitems);

    void edit(Shoppingcartitems shoppingcartitems);

    void remove(Shoppingcartitems shoppingcartitems);

    Shoppingcartitems find(Object id);

    List<Shoppingcartitems> findAll();

    List<Shoppingcartitems> findRange(int[] range);

    int count();
    
}
