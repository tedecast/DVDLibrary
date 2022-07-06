/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author Teresa
 */
public class DVDLibraryServiceLayerImpl implements DVDLibraryServiceLayer {
    
    private DVDLibraryDao dao;
    
    public DVDLibraryServiceLayerImpl(DVDLibraryDao dao){
        this.dao = dao;
    }

    @Override
    public void createDVD(DVD dvd) throws DVDLibraryDuplicateIdException, DVDLibraryDataValidationException, DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DVD getDVD(String dvdID) throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DVD removeDVD(String dvdID) throws DVDLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
