/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pulsiraj.converter;

import com.pulsiraj.beans.City;
import com.pulsiraj.beans.Municipality;
import com.pulsiraj.controller.CityController;
import com.pulsiraj.controller.MunicipalityController;
import com.pulsiraj.dao.object.MunicipalityDAO;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Dejan
 */
@FacesConverter("municipalityConverter")
public class MunicipalityConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                MunicipalityDAO dao = new MunicipalityDAO();
                List<Municipality> municipalities = dao.listEntities();
                for (Municipality municipality : municipalities) {
                    if (municipality.getM_id() == Integer.parseInt(value)) {
                        return municipality;
                    }
                }
                return null;
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid discipline."));
            }
        }
        else {
            return null;
        }
    }
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
         if(object != null) {
            return String.valueOf(((Municipality) object).getM_id());
        }
        else {
            return null;
        }
    } 
}
