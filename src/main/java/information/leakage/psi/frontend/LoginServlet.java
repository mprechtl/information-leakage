package information.leakage.psi.frontend;

import information.leakage.psi.Constants;
import information.leakage.psi.backend.stubs.SessionStore;
import information.leakage.psi.backend.stubs.UserStore;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;


/**
 *
 * @author Mike Prechtl
 */
@WebServlet("")
public class LoginServlet extends HttpServlet {

    @Inject
    private UserStore userStore;

    @Inject
    private SessionStore sessionStore;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String sessionId = req.getParameter(Constants.Parameter.SESSION_ID);

	String errorMsg = req.getParameter(Constants.Parameter.ERROR_MESSAGE);

	// set error msg as attribute
	if (errorMsg != null) {
	    req.setAttribute(Constants.Parameter.ERROR_MESSAGE, errorMsg);
	}

	if (sessionId != null && sessionStore.isValidSession(sessionId)) {
	    req.getRequestDispatcher(Constants.Jsp.INDEX_PAGE).forward(req, resp);
	} else {
	    req.getRequestDispatcher(Constants.Jsp.LOGIN_PAGE).forward(req, resp);
	}
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String email = req.getParameter(Constants.Parameter.INPUT_EMAIL);
	String password = req.getParameter(Constants.Parameter.INPUT_PASSWORD);

	if (email != null && password != null) {
	    try {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		password = DatatypeConverter.printHexBinary(hash);
	    } catch (NoSuchAlgorithmException ex) {
		throw new RuntimeException("Unable to hash password...", ex);
	    }

	    if (userStore.isValidUser(email, password)) {
		String sessionId = sessionStore.addSession(userStore.getUserId(email, password));

		req.setAttribute(Constants.Parameter.SESSION_ID, sessionId);
		String requestDispatcher = String.format(Constants.Jsp.INDEX_PAGE, sessionId);
		req.getRequestDispatcher(requestDispatcher).forward(req, resp);
	    } else {
		req.setAttribute(Constants.Parameter.ERROR_MESSAGE, "Username or Password incorrect.");
		req.getRequestDispatcher(Constants.Jsp.LOGIN_PAGE).forward(req, resp);
	    }
	} else {
	    req.setAttribute(Constants.Parameter.ERROR_MESSAGE, "Provide an username and a password!");
	    req.getRequestDispatcher(Constants.Jsp.LOGIN_PAGE).forward(req, resp);
	}
    }

}
