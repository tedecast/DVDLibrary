/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dto;

import java.util.Objects;

/**
 *
 * @author Teresa
 */
public class DVD {
    private String title;
    private String releaseDate;
    private String mpaaRating;
    private String directorsName;
    private String studioName;
    private String userRating;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.title);
        hash = 67 * hash + Objects.hashCode(this.releaseDate);
        hash = 67 * hash + Objects.hashCode(this.mpaaRating);
        hash = 67 * hash + Objects.hashCode(this.directorsName);
        hash = 67 * hash + Objects.hashCode(this.studioName);
        hash = 67 * hash + Objects.hashCode(this.userRating);
        hash = 67 * hash + Objects.hashCode(this.dvdID);
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
        final DVD other = (DVD) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        if (!Objects.equals(this.mpaaRating, other.mpaaRating)) {
            return false;
        }
        if (!Objects.equals(this.directorsName, other.directorsName)) {
            return false;
        }
        if (!Objects.equals(this.studioName, other.studioName)) {
            return false;
        }
        if (!Objects.equals(this.userRating, other.userRating)) {
            return false;
        }
        if (!Objects.equals(this.dvdID, other.dvdID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DVD{" + "title=" + title + ", releaseDate=" + releaseDate + ", mpaaRating=" + mpaaRating + ", directorsName=" + directorsName + ", studioName=" + studioName + ", userRating=" + userRating + ", dvdID=" + dvdID + '}';
    }
    private String dvdID;
    
    //dvdID does not have a setter, read only field. (key)
    //Unable to change ID once set, can only change below. 
    public DVD(String dvdID) {
        this.dvdID = dvdID;
    }
    public String getDVDID(){
        return dvdID;
    }
    
    // example: title is the key, value is whatever it is set to
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirectorsName() {
        return directorsName;
    }

    public void setDirectorsName(String directorsName) {
        this.directorsName = directorsName;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    } 
}
