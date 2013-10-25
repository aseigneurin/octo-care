package fr.octocare.api;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.googlecode.objectify.Ref;
import com.sun.jersey.api.spring.Autowire;

import fr.octocare.api.security.RequireUser;
import fr.octocare.conversion.OctoConverter;
import fr.octocare.dataAccess.OctoDAO;
import fr.octocare.dto.EventDTO;
import fr.octocare.dto.OctoDTO;
import fr.octocare.entity.Event;
import fr.octocare.entity.Octo;

@Path("octo")
@Component
@Autowire
@RequireUser
public class OctoResource {

    @Autowired
    private OctoDAO octoDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOctos() {
        List<Octo> octos = octoDAO.getOctos();
        List<OctoDTO> res = OctoConverter.convertOctoEntitiesToDTOs(octos, "list");
        return Response.ok(res).build();
    }

    @Path("/{uid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOcto(@PathParam("uid") String octoUid) {
        Octo res = octoDAO.getOcto(octoUid);
        return Response.ok(res).build();
    }

    @Path("/{uid}/history")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHistory(@PathParam("uid") String octoUid) {
        Octo octo = octoDAO.getOcto(octoUid);
        List<Event> octos = octoDAO.getEvents(octo);
        List<EventDTO> res = OctoConverter.convertEventEntitiesToDTOs(octos, "list");
        return Response.ok(res).build();
    }

    @Path("/{uid}/history")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveEvent(@PathParam("uid") String octoUid, EventDTO eventDTO) {
        Octo octo = octoDAO.getOcto(octoUid);

        Event event = new Event();
        event.setId(UUID.randomUUID().toString());
        event.setOcto(Ref.create(octo));
        event.setDate(new Date());
        event.setText(eventDTO.getText());

        octoDAO.saveEvent(event);

        EventDTO res = OctoConverter.convertEntityToDTO(event, null);
        return Response.ok(res).build();
    }
}
