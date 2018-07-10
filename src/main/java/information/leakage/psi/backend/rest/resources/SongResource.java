
package information.leakage.psi.backend.rest.resources;

import information.leakage.psi.backend.stubs.SessionStore;
import information.leakage.psi.backend.stubs.SongStore;
import information.leakage.psi.model.Song;
import information.leakage.psi.model.SongList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author Mike Prechtl
 */
@Path("/songs")
public class SongResource {

    @Inject
    private SongStore songStore;

    @Inject
    private SessionStore sessionStore;

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response addSong(@QueryParam("sid") String sid, Song song) {
	if (sessionStore.isValidSession(sid)) {
	    songStore.addSong(song);
	    return Response.ok().build();
	} else {
	    return Response.status(403).build();
	}
    }

    @GET
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response getSongs(@QueryParam("sid") String sid) {
	if (sessionStore.isValidSession(sid)) {
	    List<Song> songs = songStore.getSongs();
	    return Response.ok().entity(createSongList(songs)).build();
	} else {
	    return Response.status(403).build();
	}
    }

    private SongList createSongList(List<Song> songs) {
	SongList songList = new SongList();
	songList.setSongs(songs);
	return songList;
    }

}
