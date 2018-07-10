
package information.leakage.psi.model;

import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Mike Prechtl
 */
@XmlRootElement
public class Song {

    private String title;
    private String artist; // name of artist
    private double duration; // in seconds

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getArtist() {
	return artist;
    }

    public void setArtist(String artist) {
	this.artist = artist;
    }

    public double getDuration() {
	return duration;
    }

    public void setDuration(double duration) {
	this.duration = duration;
    }

}
