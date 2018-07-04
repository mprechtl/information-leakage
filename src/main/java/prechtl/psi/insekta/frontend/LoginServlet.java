package prechtl.psi.insekta.frontend;

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
import prechtl.psi.insekta.stubs.SessionStore;
import prechtl.psi.insekta.stubs.UserStore;


@WebServlet("")
public class LoginServlet extends HttpServlet {

    @Inject
    private UserStore userStore;

    @Inject
    private SessionStore sessionStore;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String sessionId = req.getParameter("sid");

	if (sessionId != null && sessionStore.isValidSession(sessionId)) {
	    req.getRequestDispatcher("/WEB-INF/jsps/index.jsp").forward(req, resp);
	} else {
	    req.getRequestDispatcher("/WEB-INF/jsps/login.jsp").forward(req, resp);
	}
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String email = req.getParameter("email");
	String password = req.getParameter("password");

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

		req.setAttribute("sid", sessionId);
		String requestDispatcher = String.format("/WEB-INF/jsps/index.jsp", sessionId);
		req.getRequestDispatcher(requestDispatcher).forward(req, resp);
	    } else {
		req.setAttribute("error", "Username or Password incorrect.");
		req.getRequestDispatcher("/WEB-INF/jsps/login.jsp").forward(req, resp);
	    }
	} else {
	    req.setAttribute("error", "Provide an username and a password!");
	    req.getRequestDispatcher("/WEB-INF/jsps/login.jsp").forward(req, resp);
	}
    }

}
