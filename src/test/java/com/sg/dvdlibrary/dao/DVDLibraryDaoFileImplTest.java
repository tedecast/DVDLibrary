/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.io.FileWriter;
import java.util.List;
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
               "Checking DVD comments.");    
    }
    
    @Test
    public void testAddGetAllDVDs() throws Exception {
        // Create our first DVD
        DVD dvd1 = new DVD("0001");
        dvd1.setTitle("Edward Scissorhands");
        dvd1.setReleaseDate("December 7, 1990");
        dvd1.setMpaaRating("PG-13");
        dvd1.setDirectorsName("Tim Burton");
        dvd1.setStudioName("20th Century Fox");
        dvd1.setUserRating("One of a kind!");
        
        // Create our second DVD
        DVD dvd2 = new DVD ("0002");
        dvd2.setTitle("Beetlejuice");
        dvd2.setReleaseDate("March 30, 1988");
        dvd2.setMpaaRating("PG");
        dvd2.setDirectorsName("Tim Burton");
        dvd2.setStudioName("Warner Bros.");
        dvd2.setUserRating("Spooky OOky!");
        
        // Add both our DVDs to the DAO
        testDao.addDVD(dvd1.getDVDID(), dvd1);
        testDao.addDVD(dvd2.getDVDID(), dvd2);
        
        // Retrieve the list of all DVDs within the DAO
        List<DVD> allDVDs = testDao.getAllDVDs();
        
        // First check the general contents of the list
        assertNotNull(allDVDs, "The lsit of dvds must not null");
        assertEquals(2, allDVDs.size(), "List of DVDs should have 2 students.");
        
        // Then the specifics
        assertTrue(testDao.getAllDVDs().contains(dvd1),
                "The list of DVDs should include Edward Scissorhands.");
        
        assertTrue(testDao.getAllDVDs(). contains(dvd2),
                "The list of DVDs should include Beetlejuice.");
    }
    
}
