
package prechtl.psi.insekta.stubs;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;


/**
 *
 * @author Mike Prechtl
 */
@ApplicationScoped
public class SessionStore {

    private static final List<String> SESSION_STORE = new ArrayList() {
	{
	    add("edb0e8665db4e9042fe0176a89aade16");
	}
    };

    public boolean isValidSession(String session) {
	return SESSION_STORE.contains(session);
    }

    public void addSession(String session) {
	SESSION_STORE.add(session);
    }

}
