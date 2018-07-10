
package information.leakage.psi.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Mike Prechtl
 */
@XmlRootElement
public class SongList {

    private List<Song> songs;

    public List<Song> getSongs() {
	return songs;
    }

    public void setSongs(List<Song> songs) {
	this.songs = songs;
    }

}
