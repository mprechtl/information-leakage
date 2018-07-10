
package information.leakage.psi.backend.rest.clients;

import information.leakage.psi.Constants;
import information.leakage.psi.ex.BadSong;
import information.leakage.psi.model.Song;
import information.leakage.psi.model.SongList;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Mike Prechtl
 */
public class SongClient {

    private static final Logger LOG = LoggerFactory.getLogger(SongClient.class);

    public List<Song> retrieveSongs(String sid) {
	try {
	    URL endpointUrl = new URL(Constants.API_ENDPOINT + "songs?sid=" + sid);

	    HttpURLConnection connection = (HttpURLConnection) endpointUrl.openConnection();
	    connection.setRequestMethod("GET");
	    connection.setRequestProperty("Content-Type", "application/xml");
	    connection.setRequestProperty("Accept", "application/xml");

	    connection.connect();

	    InputStream is = connection.getInputStream();
	    return createSongs(is);
	} catch (IOException ex) {
	    LOG.warn(ex.getMessage(), ex);
	    return null;
	}
    }

    public void addSong(Song song, String sid) throws BadSong {
	try {
	    URL endpointUrl = new URL(Constants.API_ENDPOINT + "songs?sid=" + sid);

	    HttpURLConnection connection = (HttpURLConnection) endpointUrl.openConnection();
	    connection.setRequestMethod("POST");
	    connection.setRequestProperty("Content-Type", "application/xml");
	    connection.setRequestProperty("Accept", "application/xml");

	    connection.setDoOutput(true);
	    OutputStream out = connection.getOutputStream();

	    byte[] songBytes = getBytesOfSong(song);
	    out.write(songBytes);

	    connection.connect();

	    int responseCode = connection.getResponseCode();

	    if (responseCode != 200) {
		throw new BadSong("Unable to create song.");
	    }
	} catch (IOException ex) {
	    LOG.warn(ex.getMessage(), ex);
	    throw new BadSong("Unable to send request to Web service.", ex);
	}
    }

    private byte[] getBytesOfSong(Song song) {
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	try {
	    JAXBContext ctx = JAXBContext.newInstance(Song.class);
	    Marshaller marshaller = ctx.createMarshaller();

	    marshaller.marshal(song, out);

	    return out.toByteArray();
	} catch (JAXBException ex) {
	    LOG.warn("Unable to marshall song.", ex);
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
