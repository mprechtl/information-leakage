package prechtl.psi.insekta.frontend;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import prechtl.psi.insekta.stubs.SessionStore;


@WebServlet("")
public class IndexServlet extends HttpServlet {

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

}
