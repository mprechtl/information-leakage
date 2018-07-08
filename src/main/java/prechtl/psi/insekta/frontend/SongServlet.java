
package prechtl.psi.insekta.frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import prechtl.psi.insekta.Constants;
import prechtl.psi.insekta.ex.BadSong;
import prechtl.psi.insekta.model.Song;
import prechtl.psi.insekta.rest.clients.SongClient;


/**
 *
 * @author Mike Prechtl
 */
@WebServlet("/songs")
public class SongServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String title = req.getParameter(Constants.Parameter.INPUT_TITLE);
	String artist = req.getParameter(Constants.Parameter.INPUT_ARTIST);
	String duration = req.getParameter(Constants.Parameter.INPUT_DURATION);

	// extract session id
	String sessionId = req.getParameter(Constants.Parameter.SESSION_ID);
	req.setAttribute(Constants.Parameter.SESSION_ID, sessionId);

	// error msg
	String errorMsg = null;

	try {
	    boolean error = evaluateParameters(title, artist, duration);
	    if (! error) {
		int durationInSeconds = Integer.parseInt(duration);
		Song song = createSong(title, artist, durationInSeconds);

		SongClient songClient = new SongClient();
		songClient.addSong(song, sessionId);
	    } else {
		errorMsg = "You have to provide a title, artist and the duration!";
	    }
	} catch (NumberFormatException ex) {
	    errorMsg = "Duration should be given in seconds!";
	} catch (BadSong ex) {
	    errorMsg = "Unable to create song. Maybe song is already listed.";
	}

	if (errorMsg == null) {
	    resp.sendRedirect("/songbook/?sid=" + sessionId);
	} else {
	    resp.sendRedirect(String.format("/songbook/?sid=%s&error=%s", sessionId, errorMsg));
	}
    }

    private boolean evaluateParameters(String title, String artist, String duration) {
	return artist == null || title == null || duration == null;
    }

    private Song createSong(String title, String artist, int duration) {
	Song song = new Song();
	song.setArtist(artist);
	song.setDuration(duration);
	song.setTitle(title);
	return song;
    }

}
