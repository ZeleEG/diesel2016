/*
 * Copyright (C) 2016 Dejan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.pulsiraj.controller;

import com.pulsiraj.beans.User;
import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Dejan
 */
public class Dispatcher {

    private RegistrationController regCont;
    private LoginController logInCont;

    public Dispatcher() {
        regCont = new RegistrationController();
        logInCont = new LoginController();
    }

    public void dispatch(String request) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        User currentUser = (User) context.getSessionMap().get("user");
        String userType = (String) context.getSessionMap().get("userType");
        if (userType == null) {
            userType = "unregisteredUser";
        }
        try {
            switch (request.toLowerCase()) {
                case "onama":
                    context.redirect("oNama.xhtml");
                    break;
                case "kontakt":
                    context.redirect("kontakt.xhtml");
                    break;
                case "index":
                    context.redirect("index.xhtml");
                    break;
                case "registrujfizicko":
                    if (userType.equals("unregisteredUser")) {
                        context.redirect("registrujFizicko.xhtml");
                        break;
                    }
                case "registrujpravno":
                    if (userType.equals("unregisteredUser") && request.equalsIgnoreCase("registrujpravno")) {
                        context.redirect("registrujPravno.xhtml");
                        break;
                    }
                case "login":
                    if (!request.equals("registrujpravno") && !request.equals("registrujfizicko") && userType.equals("unregisteredUser")) {
                        context.redirect("login.xhtml");
                        break;
                    }
                case "success":
                    String redirectURL = null;
                    switch (userType.toLowerCase()) {
                        case "legal":
                            redirectURL = "pravnoLice.xhtml";
                            break;
                        case "physical":
                            redirectURL = "fizickoLice.xhtml";
                            break;
                        default:
                            redirectURL = "error404.xhtml";
                            break;
                    }
                    context.redirect(redirectURL);
                    break;
                case "logout":
                    if (currentUser != null) {
                        LoginController.logOut();
                    }
                    context.redirect("index.xhtml");
                    break;
                case "fail":
                    context.redirect("fail.xhtml");
                    break;
                case "autherr":
                    context.redirect("autherr.xhtml");
                    break;
                case "changepass":
                    currentUser = (User) context.getSessionMap().get("user");
                    if (currentUser == null || userType.equals("unregisteredUser")) {
                        context.redirect("login.xhtml");
                    } else {
                        context.redirect("changePassword.xhtml");
                    }
                    break;
                default:
                    context.redirect("error404.xhtml");
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
