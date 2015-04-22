/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookshelf.exception;

/**
 * Custom exception.
 *
 * @author Tanguy Marechal - Yassine Badache
 */
import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)

public class FirstException extends RuntimeException {

    public FirstException(Throwable th) {
        super(th);
    }
    
}
