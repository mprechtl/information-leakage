
package information.leakage.psi.backend.stubs;

import information.leakage.psi.ex.BadSong;
import information.leakage.psi.model.Song;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;


/**
 *
 * @author Mike Prechtl
 */
@ApplicationScoped
public class SongStore {

    private static final List<Song> SONGS = new ArrayList() {
	{
	    add(createSong("You're Gonna Go Far, Kid", "The Offspring", 198));
	    add(createSong("Fox On The Run", "Sweet", 210));
	    add(createSong("Face Down", "The Red Jumpsuit Apparatus", 210));
	    add(createSong("Alan Walker", "All Falls Down", 220));
	    add(createSong("Zombie", "Bad Wolves", 275));

	}
    };

    private static Song createSong(String title, String artist, double duration) {
	Song song = new Song();
	song.setArtist(artist);
	song.setDuration(duration);
	song.setTitle(title);
	return song;
    }

    public List<Song> getSongs() {
	return Collections.unmodifiableList(SONGS);
    }

    public void addSong(Song song) {
	Optional<Song> alreadyAdded = SONGS.stream().filter(s -> s.getTitle().equalsIgnoreCase(song.getTitle())
		    && s.getArtist().equalsIgnoreCase(song.getArtist()))
		.findAny();

	if (! alreadyAdded.isPresent()) {
	    SONGS.add(song);
	} else {
	    throw new BadSong("Song is already part of the database.");
	}
    }

}
