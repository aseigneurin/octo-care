package fr.octocare.dataAccess;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

import fr.octocare.entity.Hexa;

public class HexaDAO {

    static {
        ObjectifyService.register(Hexa.class);
    }

    public List<Hexa> getHexas() {
        Objectify objectify = ObjectifyService.ofy();
        List<Hexa> hexas = objectify.load().type(Hexa.class).list();
        return hexas;
    }

    public Hexa getHexa(String uid) {
        Objectify objectify = ObjectifyService.ofy();
        Key<Hexa> hexaKey = Key.create(Hexa.class, uid);
        Hexa hexa = objectify.load().key(hexaKey).now();
        return hexa;
    }

    public Hexa getHexaByEmail(String email) {
        Objectify objectify = ObjectifyService.ofy();
        List<Hexa> hexas = objectify.load().type(Hexa.class).filter("email =", email).list();
        if (hexas.size() > 1)
            throw new RuntimeException("More than one Hexa with email " + email);
        if (hexas.size() == 0)
            return null;
        Hexa res = hexas.get(0);
        return res;
    }

    public void saveHexa(Hexa hexa) {
        Objectify objectify = ObjectifyService.ofy();
        objectify.save().entities(hexa).now();
    }
}
