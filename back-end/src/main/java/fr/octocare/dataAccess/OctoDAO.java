package fr.octocare.dataAccess;

import java.util.List;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

import fr.octocare.entity.Octo;

public class OctoDAO {

    static {
        ObjectifyService.register(Octo.class);
    }

    public List<Octo> getOctos() {
        Objectify objectify = ObjectifyService.ofy();
        List<Octo> octos = objectify.load().type(Octo.class).list();
        return octos;
    }

    public void saveOcto(Octo octo) {
        Objectify objectify = ObjectifyService.ofy();
        objectify.save().entities(octo).now();
    }
}
