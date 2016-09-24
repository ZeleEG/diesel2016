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
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Dejan
 */
@ManagedBean(name = "passwordCont")
public class PasswordController implements Serializable {

    private User currentUser;
    private String oldPassword;
    private String newPassword;
    private String newPasswordConfirm;

    @PostConstruct
    public void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String userType = (String) context.getSessionMap().get("userType");
        if (userType != null) {
            currentUser = (User) context.getSessionMap().get("user");
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }

    public void addSuccessMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addFailureMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void changePassword() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (!newPassword.equals(newPasswordConfirm)) {
            addFailureMessage("Error!", "Passwords do not match!");
            context.validationFailed();
            return;
        }
        char[] oldPasswordChar = oldPassword.toCharArray();
        byte[] salt = currentUser.getSalt();
        byte[] hashedPass = currentUser.getPasswordHash();
        try {
            if (Passwords.isExpectedPassword(oldPasswordChar, salt, hashedPass)) {
                byte[] newPasswordHash = Passwords.getHashWithSalt(newPassword, salt);
                if (Arrays.equals(newPasswordHash, hashedPass)) {
                    addFailureMessage("Error!", "New password same as old one!");
                    context.validationFailed();
                    return;
                }
                currentUser.setPasswordHash(newPasswordHash);
                UserDAO dao = new UserDAO();
                if (dao.updateEntity(currentUser) == false) {
                    addFailureMessage("Database error!", "Error updating user password!");
                    context.validationFailed();
                    return;
                }
                addSuccessMessage("Success!", "Password changed successfully!");
                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                new FrontController().dispatchRequest("login");
            } else {
                addFailureMessage("Error", "Username and Password do not match!");
                context.validationFailed();
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
