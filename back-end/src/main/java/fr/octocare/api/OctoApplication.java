package fr.octocare.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class OctoApplication extends Application {

    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(OctoResource.class);
        return s;
    }

}