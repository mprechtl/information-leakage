
package information.leakage.psi;


/**
 *
 * @author Mike Prechtl
 */
public class Constants {

    public static String FRONTEND = "http://localhost/songbook/";

    public static String API_ENDPOINT = "http://localhost:8080/songbook/api/";

    public static class Jsp {

	public static String INDEX_PAGE = "/WEB-INF/jsps/index.jsp";

	public static String LOGIN_PAGE = "/WEB-INF/jsps/login.jsp";

    }

    public static class Parameter {

	public static String INPUT_EMAIL = "email";

	public static String INPUT_PASSWORD = "password";

	public static String ERROR_MESSAGE = "error";

	public static String SESSION_ID = "sid";

	public static String INPUT_TITLE = "title";

	public static String INPUT_ARTIST = "artist";

	public static String INPUT_DURATION = "duration";

    }

    public static class Batchlet {

	public static int BATCHLET_INTERVAL = 60;

	public static String JOB_XML_NAME = "session_simulation";

	public static String SUCCESS = "SUCCESS";

	public static String FAILURE = "FAILURE";

    }

}
