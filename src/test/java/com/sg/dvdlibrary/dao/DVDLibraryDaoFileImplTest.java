/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.io.FileWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Teresa
 */
public class DVDLibraryDaoFileImplTest {
    
    DVDLibraryDao testDao;
    
    public DVDLibraryDaoFileImplTest() {
    }
    
    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testlibrary.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new DVDLibraryDaoFileImpl(testFile);
    }

    @Test
    public void testAddGetDVD() throws Exception {
       // Create our method test inputs
       String dvdID = "0001";
       DVD dvd = new DVD(dvdID);
       dvd.setTitle("Edward Scissorhands");
       dvd.setReleaseDate("December 7, 1990");
       dvd.setMpaaRating("PG-13");
       dvd.setDirectorsName("Tim Burton");
       dvd.setStudioName("20th Century Fox");
       dvd.setUserRating("One of a kind!");
       
       // Add the DVD to the DAO
       testDao.addDVD(dvdID, dvd);
       // Get the DVD from the DAO
       DVD retrievedDVD = testDao.getDVD(dvdID);
       
       // Check the data is equal
       assertEquals(dvd.getDVDID(), 
               retrievedDVD.getDVDID(),
               "Checking DVD ID.");
       
       assertEquals(dvd.getTitle(), 
               retrievedDVD.getTitle(),
               "Checking DVD Title.");
       
       assertEquals(dvd.getReleaseDate(), 
               retrievedDVD.getReleaseDate(), 
               "Checking DVD Release Date.");
       
       assertEquals(dvd.getMpaaRating(), 
               retrievedDVD.getMpaaRating(), 
               "Checking DVD MPAA Rating.");
       
       assertEquals(dvd.getDirectorsName(), 
               retrievedDVD.getDirectorsName(), 
               "Checking DVD Directors's Name.");
       
       assertEquals(dvd.getStudioName(), 
               retrievedDVD.getStudioName(), 
               "Checking DVD Studio Name");
       
       assertEquals(dvd.getUserRating(), 
               retrievedDVD.getUserRating(), 
               "Checking DVD comments.");    }
    
}
