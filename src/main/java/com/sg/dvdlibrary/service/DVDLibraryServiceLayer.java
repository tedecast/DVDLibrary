/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author Teresa
 */
public interface DVDLibraryServiceLayer {
    
    public void addDVD(DVD dvd) throws DVDLibraryDuplicateIdException, 
            DVDLibraryDataValidationException, 
            DVDLibraryPersistenceException;

    List<DVD> getAllDVDs() throws DVDLibraryPersistenceException;

    DVD getDVD(String dvdID) throws DVDLibraryPersistenceException;
    
    DVD findDVDByTitle(String title) throws DVDLibraryPersistenceException;

//    DVD addDVD(String dvdID, DVD dvd) throws DVDLibraryPersistenceException;
    
    DVD editDVD(String dvdID, DVD dvd, String prevDVDTitle) throws DVDLibraryPersistenceException;
    
    DVD removeDVD(String dvdID) throws DVDLibraryPersistenceException;

    
    DVD changeTitle(String dvdID, String title) throws DVDLibraryPersistenceException;
            
    DVD changeReleaseDate(String dvdID, String releaseDate) throws DVDLibraryPersistenceException;
    
    DVD changeMpaaRating(String dvdID, String mpaaRating) throws DVDLibraryPersistenceException;
    
    DVD changeDirectorName(String dvdID, String directorName) throws DVDLibraryPersistenceException;
    
    DVD changeUserRating(String dvdID, String userRating) throws DVDLibraryPersistenceException;
    
    DVD changeStudioName(String dvdID, String studioName) throws DVDLibraryPersistenceException;
}
