package prechtl.psi.insekta.resources;

import prechtl.psi.insekta.model.Artist;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * @author Mike Prechtl
 */
@Path("/xml")
public class XmlReceiver {

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response receiveXmlXEE(Artist artist) {
        return Response.ok().entity(artist).build();
    }

}
