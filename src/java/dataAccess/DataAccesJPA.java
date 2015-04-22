/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;


import javax.ejb.Stateless;
import javax.*;
import jpa.*;
import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import java.text.*;
import bookshelf.exception.*;

/**
 *
 * @author raynak
 */
@Stateless(mappedName = "Interface")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class DataAccesJPA implements ItfDataAccessLocal, ItfDataAccessRemote{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<Books> getBooksList() {
        try {
            return em.createQuery("select b from BOOKS b").getResultList();
        }
        catch (Throwable th){
            throw new FirstException(th);
        }
    }

    @Override
    public List<Books> getBooksByAuthor(String author) {
        try {
            return em.createQuery("SELECT b FROM BOOKS b WHERE author=" + author).getResultList();
        } catch (Throwable th) {
            throw new FirstException(th);
        }   
    }

    @Override
    public Books getBookByTitleAndAuthor(String title, String author) {
        try {
            return (Books)em.createQuery("SELECT b FROM BOOKS b WHERE author=" +author+" AND title="+title).getSingleResult();
        } catch (Throwable th) {
            throw new FirstException(th);
        }   
    }

    @Override
    public void deleteBook(Books book) {
        try {
            em.remove(em.merge(book));
        } catch (Throwable th) {
            throw new FirstException(th);
        } 
    }

    @Override
    public Books addBook(Books book) {
        try {
            em.persist(book);
            return book;
        } catch (Throwable th) {
            throw new FirstException(th);
        }
    }
    
}
