package fr.octocare.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("octo")
public class OctoResource {

    @Path("/{uid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findContents(@PathParam("uid") String octoUid) {

        return Response.ok(octoUid).build();
    }

}
