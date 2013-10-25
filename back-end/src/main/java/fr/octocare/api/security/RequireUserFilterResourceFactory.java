package fr.octocare.api.security;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.sun.jersey.api.model.AbstractMethod;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;
import com.sun.jersey.spi.container.ResourceFilterFactory;

public class RequireUserFilterResourceFactory implements ResourceFilterFactory {

    @Context
    private SecurityContext sc;

    @Override
    public List<ResourceFilter> create(AbstractMethod am) {
        if (am.isAnnotationPresent(RequireUser.class) || am.getResource().isAnnotationPresent(RequireUser.class)) {
            return Collections.<ResourceFilter> singletonList(new Filter());
        }
        // TODO Auto-generated method stub
        return null;
    }

    private class Filter implements ResourceFilter, ContainerRequestFilter {

        @Override
        public ContainerRequestFilter getRequestFilter() {
            return this;
        }

        @Override
        public ContainerResponseFilter getResponseFilter() {
            return null;
        }

        @Override
        public ContainerRequest filter(ContainerRequest request) {
            Principal userPrincipal = sc.getUserPrincipal();
            if (userPrincipal == null)
                throw new WebApplicationException(Response.Status.FORBIDDEN);
            return request;
        }
    }

}
