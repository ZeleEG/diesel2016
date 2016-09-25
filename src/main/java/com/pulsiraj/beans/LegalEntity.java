/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pulsiraj.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Time;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Dejan
 */
@ManagedBean(name = "legalEntity")
@ViewScoped
@Entity
@Table(name = "legalEntity")
public class LegalEntity implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "u_id")
    private User user;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @JoinColumn(name = "m_id")
    private Municipality municipality;
    @JoinColumn(name = "c_id")
    private City city;
    @Column(name = "land_phone1")
    private String landPhone1;
    @Column(name = "land_phone2")
    private String landPhone2;
    @Column(name = "mobile_phone1")
    private String mobilePhone1;
    @Column(name = "mobile_phone2")
    private String mobilePhone2;
    @Column(name = "website")
    private String website;
    @Column(name = "facebook_profile")
    private String facebookProfile;
    @Column(name = "instagram_profile")
    private String instagramProfile;
    @Temporal(TemporalType.TIME)
    @Column(name = "open_time")
    private Time openTime;
    @Column(name = "close_time")
    private Time closeTime;
    @Column(name = "youtube_profile")
    private String youtubeProfile;

    public String getYoutubeProfile() {
        return youtubeProfile;
    }

    public void setYoutubeProfile(String youtubeProfile) {
        this.youtubeProfile = youtubeProfile;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getLandPhone1() {
        return landPhone1;
    }

    public void setLandPhone1(String landPhone1) {
        this.landPhone1 = landPhone1;
    }

    public String getLandPhone2() {
        return landPhone2;
    }

    public void setLandPhone2(String landPhone2) {
        this.landPhone2 = landPhone2;
    }

    public String getMobilePhone1() {
        return mobilePhone1;
    }

    public void setMobilePhone1(String mobilePhone1) {
        this.mobilePhone1 = mobilePhone1;
    }

    public String getMobilePhone2() {
        return mobilePhone2;
    }

    public void setMobilePhone2(String mobilePhone2) {
        this.mobilePhone2 = mobilePhone2;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFacebookProfile() {
        return facebookProfile;
    }

    public void setFacebookProfile(String facebookProfile) {
        this.facebookProfile = facebookProfile;
    }

    public String getInstagramProfile() {
        return instagramProfile;
    }

    public void setInstagramProfile(String instagramProfile) {
        this.instagramProfile = instagramProfile;
    }

    public Time getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Time openTime) {
        this.openTime = openTime;
    }

    public Time getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.user);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (user == null) {
            return false;
        }
        final LegalEntity other = (LegalEntity) obj;
        
        return user.equals(other.user);
    }

    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        String countryImageFolder
                = context.getExternalContext().getInitParameter("countryImageFolder");
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String filename = context.getExternalContext().getRequestParameterMap().get("filename");
            if (filename == null || filename.equals("")) {
                filename = "noImage.png";
            }
            return new DefaultStreamedContent(new FileInputStream(new File(countryImageFolder, filename)));
        }
    }

}
