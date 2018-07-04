
package prechtl.psi.insekta.stubs;

import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import prechtl.psi.insekta.utils.SecureRandomFactory;


/**
 *
 * @author Mike Prechtl
 */
@ApplicationScoped
public class SessionStore {

    private static final Map<String, String> SESSION_STORE = new HashMap() {
	{
	    put("9e78de73", "edb0e8665db4e9042fe0176a89aade16");
	}
    };

    public boolean isValidSession(String session) {
	return SESSION_STORE.containsValue(session);
    }

    public String addSession(String userId) {
	String sessionId = generateSessionToken();

	if (SESSION_STORE.containsKey(userId)) {
	    SESSION_STORE.remove(userId);
	}
	SESSION_STORE.put(userId, sessionId);

	return sessionId;
    }

    private String generateSessionToken() {
	String sessionId = SecureRandomFactory.generateToken();
	while (SESSION_STORE.containsValue(sessionId)) {
	    sessionId = SecureRandomFactory.generateToken();
	}
	return sessionId;
    }

}
