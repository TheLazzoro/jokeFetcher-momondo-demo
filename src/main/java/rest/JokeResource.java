package rest;

import parallel.ParallelJokes;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author lam
 */
@Path("jokes")
public class JokeResource {

    @Context
    private UriInfo context;

   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJokes() {
        ParallelJokes jokes = new ParallelJokes();
        String msg = "";

        for (int i = 0; i < jokes.getJokes().size(); i++) {
            msg += jokes.getJokes().get(i).getValue() + "\n";
        }

        return msg;
    }
}
