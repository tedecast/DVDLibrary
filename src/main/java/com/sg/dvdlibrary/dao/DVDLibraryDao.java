/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author Teresa
 */
public interface DVDLibraryDao {

    List<DVD> getAllDVDs() throws DVDLibraryDaoException;

    DVD getDVD(String title) throws DVDLibraryDaoException;

    DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException;
    
    DVD editDVD(String title, DVD dvd, String prevDVDTitle) throws DVDLibraryDaoException;
    
    DVD removeDVD(String title) throws DVDLibraryDaoException;
}
