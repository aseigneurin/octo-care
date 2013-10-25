package fr.octocare.conversion;

import java.util.LinkedList;
import java.util.List;

import fr.octocare.dto.EventDTO;
import fr.octocare.dto.HexaDTO;
import fr.octocare.dto.OctoDTO;
import fr.octocare.entity.Event;
import fr.octocare.entity.Octo;

public class OctoConverter {

    public static List<OctoDTO> convertOctoEntitiesToDTOs(List<Octo> octos, String format) {

        List<OctoDTO> res = new LinkedList<OctoDTO>();
        for (Octo octo : octos) {
            OctoDTO model = convertEntityToDTO(octo, format);
            res.add(model);
        }

        return res;
    }

    public static OctoDTO convertEntityToDTO(Octo octo, String format) {

        OctoDTO res = new OctoDTO(octo.getId(), octo.getName());
        return res;
    }

    public static List<EventDTO> convertEventEntitiesToDTOs(List<Event> events, String format) {

        List<EventDTO> res = new LinkedList<EventDTO>();
        for (Event event : events) {
            EventDTO model = convertEntityToDTO(event, format);
            res.add(model);
        }

        return res;
    }

    public static EventDTO convertEntityToDTO(Event event, String format) {

        HexaDTO reporterDTO = HexaConverter.convertEntityToDTO(event.getAuthor().get(), "simple");
        EventDTO res = new EventDTO(reporterDTO, event.getId(), event.getDate(), event.getText());
        return res;
    }
}
