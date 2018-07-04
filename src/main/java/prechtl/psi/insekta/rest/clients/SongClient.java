
package prechtl.psi.insekta.rest.clients;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prechtl.psi.insekta.Constants;
import prechtl.psi.insekta.model.Song;
import prechtl.psi.insekta.model.SongList;


/**
 *
 * @author Mike Prechtl
 */
public class SongClient {

    private static final Logger LOG = LoggerFactory.getLogger(SongClient.class);

    public List<Song> retrieveSongs() {
	try {
	    URL endpointUrl = new URL(Constants.API_ENDPOINT + "songs");

	    HttpURLConnection connection = (HttpURLConnection) endpointUrl.openConnection();
	    connection.setRequestMethod("GET");
	    connection.connect();

	    InputStream is = connection.getInputStream();
	    return createSongs(is);
	} catch (IOException ex) {
	    LOG.warn(ex.getMessage(), ex);
	    return null;
	}
    }

    private List<Song> createSongs(InputStream is) {
	try {
	    JAXBContext ctx = JAXBContext.newInstance(SongList.class);
	    Unmarshaller unmarshaller = ctx.createUnmarshaller();

	    SongList songList = (SongList) unmarshaller.unmarshal(is);
	    return songList.getSongs();
	} catch (JAXBException ex) {
	    LOG.warn("Unable to unmarshall response entity.", ex);
	    return null;
	}
    }

}
