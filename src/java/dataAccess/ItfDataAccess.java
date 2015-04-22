/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import javax.ejb.Stateless;
import jpa.*;
import java.util.*;
/**
 *
 * @author raynak
 */
@Stateless
public interface ItfDataAccess {

    public List<Books> getBooksList();
    public List<Books> getBooksByAuthor(String author);
    public Books getBookByTitleAndAuthor(String title, String author);
    public void deleteBook(Books book);
    public Books addBook(Books book);
    
}
