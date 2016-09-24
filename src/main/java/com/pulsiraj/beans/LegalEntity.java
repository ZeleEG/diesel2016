/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pulsiraj.beans;

import java.io.Serializable;
import java.sql.Time;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

    

}
