package fr.octocare.dataAccess;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

import fr.octocare.entity.Event;
import fr.octocare.entity.Octo;

public class OctoDAO {

    static {
        ObjectifyService.register(Octo.class);
        ObjectifyService.register(Event.class);
    }

    public List<Octo> getOctos() {
        Objectify objectify = ObjectifyService.ofy();
        List<Octo> octos = objectify.load().type(Octo.class).list();
        return octos;
    }

    public Octo getOcto(String uid) {
        Objectify objectify = ObjectifyService.ofy();
        Key<Octo> octoKey = Key.create(Octo.class, uid);
        Octo octo = objectify.load().key(octoKey).now();
        return octo;
    }

    public void saveOcto(Octo octo) {
        Objectify objectify = ObjectifyService.ofy();
        objectify.save().entities(octo).now();
    }

    public List<Event> getEvents(Octo octo) {
        Objectify objectify = ObjectifyService.ofy();
        List<Event> events = objectify.load().type(Event.class).ancestor(octo).list();
        return events;
    }

    public void saveEvent(Event event) {
        Objectify objectify = ObjectifyService.ofy();
        objectify.save().entities(event).now();
    }
}
