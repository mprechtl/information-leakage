
package information.leakage.psi.ex;

import javax.ws.rs.WebApplicationException;


/**
 *
 * @author Mike Prechtl
 */
public class NotFound extends WebApplicationException {

    public NotFound(String message) {
	super(message, 404);
    }

    public NotFound(String message, Throwable cause) {
	super(message, cause, 404);
    }

}
