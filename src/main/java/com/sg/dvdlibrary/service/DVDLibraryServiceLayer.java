/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author Teresa
 */
public interface DVDLibraryServiceLayer {
    
    public void createDVD(DVD dvd) throws DVDLibraryDuplicateIdException, 
            DVDLibraryDataValidationException, 
            DVDLibraryPersistenceException;
    
    public List<DVD> getAllDVDs() throws DVDLibraryPersistenceException;
    
    public DVD getDVD(String dvdID) throws DVDLibraryPersistenceException;
    
    public DVD removeDVD(String dvdID) throws DVDLibraryPersistenceException;
}
