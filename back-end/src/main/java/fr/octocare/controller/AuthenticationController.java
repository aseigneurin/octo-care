package fr.octocare.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@Controller
public class AuthenticationController {

    public AuthenticationController() {

    }

    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws URISyntaxException, IOException {

        UserService userService = UserServiceFactory.getUserService();

        String targetURL;
        if (userService.isUserLoggedIn()) {
            targetURL = "/";
        } else {
            targetURL = userService.createLoginURL("/");
        }

        response.sendRedirect(targetURL);
    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws URISyntaxException, IOException {

        UserService userService = UserServiceFactory.getUserService();

        String targetURL;
        if (userService.isUserLoggedIn()) {
            targetURL = userService.createLogoutURL("/");
        } else {
            targetURL = "/";
        }

        response.sendRedirect(targetURL);
    }

}
