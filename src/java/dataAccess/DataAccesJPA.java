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
 * Permet de gerer l'interaction entre la base de donnees et l'interface web
 *
 * @author Tanguy Marechal - Yassine Badache
 */
@Stateless(mappedName = "Interface")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class DataAccesJPA implements ItfDataAccessLocal, ItfDataAccessRemote{

    @PersistenceContext
    private EntityManager em;
    
	/**
	* Retourne la liste des livres sans aucune parametre
	*/
    @Override
    public List<Books> getBooksList() {
        try {
            return em.createQuery("select b from BOOKS b").getResultList();
        }
        catch (Throwable th){
            throw new FirstException(th);
        }
    }

	/**
	* Retourne la liste des livres sans aucune parametre
	* @param author Le critere de selection
	*/
    @Override
    public List<Books> getBooksByAuthor(String author) {
        try {
            return em.createQuery("SELECT b FROM BOOKS b WHERE author=" + author).getResultList();
        } catch (Throwable th) {
            throw new FirstException(th);
        }   
    }

	/**
	* Retourne la liste des livres selon l'auteur et le titre
	* @param author Le premier critere de selection
	* @param title Le second parametre de selection
	*/
    @Override
    public Books getBookByTitleAndAuthor(String title, String author) {
        try {
            return (Books)em.createQuery("SELECT b FROM BOOKS b WHERE author=" +author+" AND title="+title).getSingleResult();
        } catch (Throwable th) {
            throw new FirstException(th);
        }   
    }

	/**
	* Supprime un livre de la base de donnees
	* @param book le livre que l'on souhaite supprimer
	*/
    @Override
    public void deleteBook(Books book) {
        try {
            em.remove(em.merge(book));
        } catch (Throwable th) {
            throw new FirstException(th);
        } 
    }

	/**
	* Ajoute un livre a la base de donnees
	* @param book le livre que l'on veut ajouter
	*/
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
