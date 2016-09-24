/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pulsiraj.controller;

import com.pulsiraj.beans.Municipality;
import com.pulsiraj.dao.object.MunicipalityDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Dejan
 */
@ManagedBean(name = "municipalityCont")
@SessionScoped
public class MunicipalityController {
    List<Municipality> municipalityList;

    public List<Municipality> getMunicipalityList() {
        return municipalityList;
    }

    public void setMunicipalityList(List<Municipality> municipalityList) {
        this.municipalityList = municipalityList;
    }
    
    public List<Municipality> listMunicipalitiesForCountry(int c_id) {
        if (c_id < 1) {
            return null;
        }
        MunicipalityDAO dao = new MunicipalityDAO();
        return dao.listMunicipalityForCity(c_id);
    }
    
    public List<Municipality> listMunicipalities() {
        MunicipalityDAO dao = new MunicipalityDAO();
        return dao.listEntities();
    }
    
    public void updateMunicipalitiesForCountry(int cnt_id) {
        municipalityList = listMunicipalitiesForCountry(cnt_id);
    }
    
    public void updateMunicipalities() {
        municipalityList = listMunicipalities();
    }
}
