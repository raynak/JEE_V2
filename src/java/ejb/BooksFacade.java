/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.Books;

/**
 *
 * @author Tanguy Marechal - Yassine Badache
 */
@Stateless
public class BooksFacade extends AbstractFacade<Books> implements BooksFacadeLocal, BooksFacadeRemote {
    @PersistenceContext(unitName = "BookShelfV2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BooksFacade() {
        super(Books.class);
    }
    
}
