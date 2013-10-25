package fr.octocare.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.spring.Autowire;

import fr.octocare.conversion.HexaConverter;
import fr.octocare.dto.HexaDTO;
import fr.octocare.entity.Hexa;
import fr.octocare.service.HexaService;

@Path("hexa")
@Component
@Autowire
public class HexaResource {

    @Autowired
    private HexaService hexaService;

    @Path("/current")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HexaDTO getCurrentHexa() {
        Hexa hexa = hexaService.getCurrentHexa();
        HexaDTO res = HexaConverter.convertEntityToDTO(hexa, "full");
        return res;
    }
}
