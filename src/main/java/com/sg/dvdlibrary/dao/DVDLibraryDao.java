/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Teresa
 */
public interface DVDLibraryDao {

    List<DVD> getAllDVDs() throws DVDLibraryDaoException;

    DVD getDVD(String dvdID) throws DVDLibraryDaoException;

    DVD addDVD(String dvdID, DVD dvd) throws DVDLibraryDaoException;
    
    DVD editDVD(String dvdID, DVD dvd, String prevDVDTitle) throws DVDLibraryDaoException;
    
    DVD removeDVD(String dvdID) throws DVDLibraryDaoException;

    
    
    Map<String, DVD> findTitle(String title) throws DVDLibraryDaoException;

    Map<String, DVD> findReleaseDate(String releaseDate) throws DVDLibraryDaoException;

    Map<String, DVD> findMpaaRating(String mpaaRating) throws DVDLibraryDaoException;

    Map<String, DVD> findDirectorsName(String directorsName) throws DVDLibraryDaoException;
    
    Map<String, DVD> findStudioName(String studioName) throws DVDLibraryDaoException;
    
    
    
    
    DVD changeTitle(String dvdID, String title) throws DVDLibraryDaoException;
            
    DVD changeReleaseDate(String dvdID, String releaseDate) throws DVDLibraryDaoException;
    
    DVD changeMpaaRating(String dvdID, String mpaaRating) throws DVDLibraryDaoException;
    
    DVD changeDirectorName(String dvdID, String directorName) throws DVDLibraryDaoException;
    
    DVD changeUserRating(String dvdID, String userRating) throws DVDLibraryDaoException;
    
    DVD changeStudioName(String dvdID, String studioName) throws DVDLibraryDaoException;
    
}
