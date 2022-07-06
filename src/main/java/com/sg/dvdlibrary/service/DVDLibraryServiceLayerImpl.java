/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DVDLibraryAuditDao;
import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Teresa
 */
public class DVDLibraryServiceLayerImpl implements DVDLibraryServiceLayer {
    
    private DVDLibraryDao dao;
    private DVDLibraryAuditDao auditDao;
   
    public DVDLibraryServiceLayerImpl(DVDLibraryDao dao, DVDLibraryAuditDao auditDdao){
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    private Map<String, DVD> dvds = new HashMap<>();

    @Override
    public void addDVD(DVD dvd) throws DVDLibraryDuplicateIdException, DVDLibraryDataValidationException, DVDLibraryPersistenceException {
        
        if(dao.getDVD(dvd.getDVDID()) != null){
            throw new DVDLibraryDuplicateIdException("ERROR: Could not create DVD. DVD ID " + dvd.getDVDID() + " already exists.");
        }
        
        validateDVDData(dvd);
        
        dao.addDVD(dvd.getDVDID(), dvd);
        
        auditDao.writeAuditEntry("DVD " + dvd.getDVDID() + " CREATED");
    }
    
//    @Override
//    public DVD addDVD(String dvdID, DVD dvd) throws DVDLibraryPersistenceException {
//        DVD prevDVD = dvds.put(dvdID, dvd);
//        return prevDVD;
//    }
    
    
    
    @Override
    public DVD removeDVD(String dvdID) throws DVDLibraryPersistenceException {
        DVD removedDVD = dao.removeDVD(dvdID);
        auditDao.writeAuditEntry("DVD " + dvdID + " REMOVED");
        return removedDVD;
    }
    
    @Override
    public DVD editDVD(String dvdID, DVD dvd, String prevDVDTitle) throws DVDLibraryPersistenceException {
        prevDVDTitle = dvd.getDVDID(); // Gets DVD ID
        DVD editDVD = dvds.remove(prevDVDTitle); // removes ^
        editDVD = dvds.put(dvdID, dvd); // puts new DVD ID
        return editDVD;
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryPersistenceException {
        return dao.getAllDVDs();
    }

    @Override
    public DVD getDVD(String dvdID) throws DVDLibraryPersistenceException {
        return dao.getDVD(dvdID);
    }
    
    @Override
    public DVD findDVDByTitle(String title) throws DVDLibraryPersistenceException {
        Collection<DVD> dvdLibrary = dvds.values();
        // loops through the values to get title
        for (DVD currentDVD : dvdLibrary) {
           if(currentDVD.getTitle().equals(title)){
               return currentDVD;
           }
        }
        return null;
    }

    @Override
    public DVD changeTitle(String dvdID, String dvd) throws DVDLibraryPersistenceException {
        DVD dvdToEdit = dvds.get(dvdID); // Gets DVD ID
        dvdToEdit.setTitle(dvd); // Sets new DVD Title
        return dvdToEdit;
    }

    @Override
    public DVD changeReleaseDate(String dvdID, String releaseDate) throws DVDLibraryPersistenceException {
        DVD dvdToEdit = dvds.get(dvdID);
        dvdToEdit.setReleaseDate(releaseDate);
        return dvdToEdit;
    }

    @Override
    public DVD changeMpaaRating(String dvdID, String mpaaRating) throws DVDLibraryPersistenceException {
        DVD dvdToEdit = dvds.get(dvdID);
        dvdToEdit.setMpaaRating(mpaaRating);
        return dvdToEdit;
    }

    @Override
    public DVD changeDirectorName(String dvdID, String directorName) throws DVDLibraryPersistenceException {
        DVD dvdToEdit = dvds.get(dvdID);
        dvdToEdit.setDirectorsName(directorName);
        return dvdToEdit;
    }

    @Override
    public DVD changeUserRating(String dvdID, String userRating) throws DVDLibraryPersistenceException {
        DVD dvdToEdit = dvds.get(dvdID);
        dvdToEdit.setUserRating(userRating);
        return dvdToEdit;
    }

    @Override
    public DVD changeStudioName(String dvdID, String studioName) throws DVDLibraryPersistenceException {
        DVD dvdToEdit = dvds.get(dvdID);
        dvdToEdit.setStudioName(studioName);
        return dvdToEdit;
    }

    
    private void validateDVDData(DVD dvd) throws DVDLibraryDataValidationException {
        if (dvd.getTitle() == null || dvd.getTitle().trim().length() == 0 ||
                dvd.getReleaseDate() == null || dvd.getReleaseDate().trim().length() == 0 ||
                dvd.getMpaaRating() == null || dvd.getMpaaRating().trim().length() == 0 ||
                dvd.getDirectorsName() == null || dvd.getDirectorsName().trim().length() == 0 ||
                dvd.getStudioName() == null || dvd.getStudioName().trim().length() == 0 ||
                dvd.getUserRating() == null || dvd.getUserRating().trim().length() == 0) {
                throw new DVDLibraryDataValidationException("ERROR: All fields [Title, MPAA Rating, Director's Name, Studio Name, User Rating] are required");
        }
    }

   
    
}
