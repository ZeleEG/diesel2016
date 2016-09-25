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

import com.pulsiraj.beans.LegalEntity;
import com.pulsiraj.beans.PhysicalEntity;
import com.pulsiraj.beans.User;
import com.pulsiraj.dao.object.LegalEntityDAO;
import com.pulsiraj.dao.object.UserDAO;
import com.pulsiraj.security.Passwords;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Dejan
 */
@ManagedBean(name = "regCont")
public class RegistrationController implements Serializable {

    @ManagedProperty(value = "#{user}")
    private User newUser;
    @ManagedProperty(value = "#{legalEntity}")
    private LegalEntity newLegalEntity;
    @ManagedProperty(value = "#{physicalEntity}")
    private PhysicalEntity newPhysicalEntity;
    private String password;
    private String passwordConfirm;

    public void hashPassword() throws NoSuchAlgorithmException {
        byte[] salt = Passwords.getNextSalt();
        newUser.setSalt(salt);
        newUser.setPasswordHash(Passwords.getHashWithSalt(password, salt));
        password = passwordConfirm = "";
    }

    public String legalOnFlowProcess(FlowEvent event) {
        switch (event.getOldStep()) {
            case "tabUser":

                break;
            case "tabLegal":

                break;
            case "tabLocation":

                break;
            case "tabWorkHours":

                break;
            case "tabSocial":

                break;
        }

        return event.getNewStep();
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public LegalEntity getNewLegalEntity() {
        return newLegalEntity;
    }

    public void setNewLegalEntity(LegalEntity newLegalEntity) {
        this.newLegalEntity = newLegalEntity;
    }

    public PhysicalEntity getNewPhysicalEntity() {
        return newPhysicalEntity;
    }

    public void setNewPhysicalEntity(PhysicalEntity newPhysicalEntity) {
        this.newPhysicalEntity = newPhysicalEntity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public void addSuccessMessage(String header, String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addFailureMessage(String header, String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void createUser() {
        if (!passwordConfirm.equals(password)) {
            addFailureMessage("Error!", "Password missmatch!");
            password = passwordConfirm = "";
            FacesContext.getCurrentInstance().validationFailed();
            return;
        }

        addSuccessMessage("Success!", "User created successfully!");
        new FrontController().dispatchRequest("index");
    }

    public void registerLegal() {
        if (!passwordConfirm.equals(password)) {
            addFailureMessage("Error!", "Password missmatch!");
            password = passwordConfirm = "";
            FacesContext.getCurrentInstance().validationFailed();
            return;
        }
        newUser.setUserType("legal");
        try {
            hashPassword();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        UserDAO dao = new UserDAO();
        if (dao.createEntity(newUser) != null) {
            newLegalEntity.setUser(newUser);
            LegalEntityDAO lDao = new LegalEntityDAO();
            if (lDao.createWeakEntity(newLegalEntity) != null) {
                addSuccessMessage(password, password);
            } else {
                dao.deleteEntity(newUser);
                addFailureMessage("Error!", "Error accessing database!");
                FacesContext.getCurrentInstance().validationFailed();
                return;
            }
        } else {
            addFailureMessage("Error!", "Error creating user!");
            FacesContext.getCurrentInstance().validationFailed();
            return;
        }

        addSuccessMessage("Success!", "Prospect created successfully!");
        new FrontController().dispatchRequest("index");
    }

}
