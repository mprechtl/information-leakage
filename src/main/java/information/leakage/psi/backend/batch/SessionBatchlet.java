
package information.leakage.psi.backend.batch;

import information.leakage.psi.Constants;
import information.leakage.psi.backend.stubs.SessionStore;
import information.leakage.psi.backend.stubs.UserStore;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import javax.batch.api.AbstractBatchlet;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Mike Prechtl
 */
@Named("SessionBatchlet")
@Dependent
public class SessionBatchlet extends AbstractBatchlet {

    private static final Logger LOG = LoggerFactory.getLogger(SessionBatchlet.class);

    @Inject
    private SessionStore sessionStore;

    @Inject
    private UserStore userStore;

    @Override
    public String process() throws Exception {
	LOG.info("Entering Session Batchlet...");

	String activeSession = getActiveSession();
	simulateActiveSession(activeSession);

	LOG.info("Finishing Session Batchlet...");

	return Constants.Batchlet.SUCCESS;
    }

    private void simulateActiveSession(String sessionId) {
	try {
	    URL endpointUrl = new URL(Constants.FRONTEND + "?sid=" + sessionId);

	    HttpURLConnection connection = (HttpURLConnection) endpointUrl.openConnection();
	    connection.setRequestMethod("GET");

	    connection.connect();

	    int responseCode = connection.getResponseCode();
	    if (responseCode != 200) {
		LOG.warn("Simulation of session failed, response code: {}", responseCode);
	    }
	} catch (IOException ex) {
	    LOG.warn(ex.getMessage(), ex);
	}
    }

    private String getActiveSession() {
	Map<String, String> sessions = sessionStore.getAllSessions();
	if (sessions.isEmpty()) {
	    List<String> userIds = userStore.getUserIds();
	    if (userIds.isEmpty()) {
		throw new RuntimeException("There are no users available.");
	    } else {
		return sessionStore.addSession(userIds.get(0));
	    }
	} else {
	    return sessions.values().stream().findFirst().get();
	}
    }

}
