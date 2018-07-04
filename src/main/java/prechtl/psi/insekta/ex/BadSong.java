
package prechtl.psi.insekta.ex;

import javax.ws.rs.WebApplicationException;


/**
 *
 * @author Mike Prechtl
 */
public class BadSong extends WebApplicationException {

    public BadSong(String message) {
	super(message, 400);
    }

    public BadSong(String message, Throwable cause) {
	super(message, cause, 400);
    }

}
