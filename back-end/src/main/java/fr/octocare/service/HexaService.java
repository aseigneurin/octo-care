package fr.octocare.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import fr.octocare.dataAccess.HexaDAO;
import fr.octocare.entity.Hexa;

public class HexaService {

    private UserService userService;

    @Autowired
    private HexaDAO hexaDAO;

    public HexaService() {

    }

    @PostConstruct
    public void initialize() {
        userService = UserServiceFactory.getUserService();
    }

    public Hexa getCurrentHexa() {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null)
            return null;
        Hexa hexa = hexaDAO.getHexaByEmail(currentUser.getEmail());
        return hexa;
    }
}
