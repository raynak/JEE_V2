/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.Users;

/**
 *
 * @author raynak
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> implements UsersFacadeLocal, ejb.UsersFacadeRemote {
    @PersistenceContext(unitName = "BookShelfV2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
}
