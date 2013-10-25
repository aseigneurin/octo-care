package fr.octocare.conversion;

import java.util.LinkedList;
import java.util.List;

import fr.octocare.dto.HexaDTO;
import fr.octocare.entity.Hexa;

public class HexaConverter {

    public static List<HexaDTO> convertHexaEntitiesToDTOs(List<Hexa> hexas, String format) {

        List<HexaDTO> res = new LinkedList<HexaDTO>();
        for (Hexa hexa : hexas) {
            HexaDTO model = convertEntityToDTO(hexa, format);
            res.add(model);
        }

        return res;
    }

    public static HexaDTO convertEntityToDTO(Hexa hexa, String format) {
        HexaDTO res = new HexaDTO();
        if (hexa != null) {
            res.setId(hexa.getId());
            res.setEmail(hexa.getEmail());
            res.setName(hexa.getName());
        }
        return res;
    }
}
