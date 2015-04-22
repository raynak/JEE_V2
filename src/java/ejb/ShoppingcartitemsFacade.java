/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.Shoppingcartitems;

/**
 *
 * @author raynak
 */
@Stateless
public class ShoppingcartitemsFacade extends AbstractFacade<Shoppingcartitems> implements ShoppingcartitemsFacadeLocal, ejb.ShoppingcartitemsFacadeRemote {
    @PersistenceContext(unitName = "BookShelfV2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ShoppingcartitemsFacade() {
        super(Shoppingcartitems.class);
    }
    
}
