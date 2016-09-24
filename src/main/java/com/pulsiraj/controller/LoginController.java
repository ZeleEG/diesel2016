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
import com.pulsiraj.dao.object.UserDAO;
import com.pulsiraj.security.Passwords;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;


/**
 *
 * @author Dejan
 */
@ManagedBean(name = "loginCont")
@RequestScoped
public class LoginController {

    @ManagedProperty(value = "#{user}")
    private User user;
    private String username;
    private String password;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addMessage(String header, String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, header, summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void saveUser() {
        UserDAO dao = new UserDAO();
        dao.createEntity(user);
        clearAll();
    }

    public void clearAll() {
        user.setApprovalStatus(false);
        user.setEmail("");
        user.setUserName("");
        user.setPasswordHash(null);
    }

    public void validateUser() {
        UserDAO dao = new UserDAO();
        User usr = dao.readEntity(username);
        if (usr == null) {
            username = "";
            addMessage("Error", "Username doesn't exist");
            return;
        }
        char[] passChar = password.toCharArray();
        byte[] salt = usr.getSalt();
        byte[] hashedPass = usr.getPasswordHash();
        try {
            if (Passwords.isExpectedPassword(passChar, salt, hashedPass)) {
                FacesContext context = FacesContext.getCurrentInstance();
                String userType = usr.getUserType();
                context.getExternalContext().getSessionMap().put("user", usr);
                context.getExternalContext().getSessionMap().put("userType", userType);
                new FrontController().dispatchRequest("success");
            } else {
                addMessage("Error", "Username and Password do not match!");
                password = "";
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void logOut() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    public boolean checkIfLoggedIn() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String userType = (String)context.getSessionMap().get("userType");
        return userType != null;
        
    }
}
