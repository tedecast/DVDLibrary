/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Teresa
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    private Map<String, DVD> dvds = new HashMap<>();
    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";

    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryDaoException {
        loadLibrary();
        return new ArrayList<DVD>(dvds.values());
    }

    @Override
    public DVD getDVD(String dvdID) throws DVDLibraryDaoException {
        loadLibrary();
        return dvds.get(dvdID);
    }
    
    

    @Override
    public Map<String, DVD> getDVDTitle(String title) throws DVDLibraryDaoException {
        loadLibrary();
        Map<String, DVD> dvdTitle = dvds
                .entrySet()
                .stream()
                .filter((dvd) -> dvd.getValue().getMpaaRating().equalsIgnoreCase(title))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return dvdTitle;
    }

//    
//    @Override
//    public DVD getDVDTitle(String title) throws DVDLibraryDaoException {
//        loadLibrary();
//        DVD dvdTitle = dvds.get(title);
//        writeLibrary();
//        return dvdTitle;
//    }

    @Override
    public DVD addDVD(String dvdID, DVD dvd) throws DVDLibraryDaoException {
        loadLibrary();
        DVD prevDVD = dvds.put(dvdID, dvd);
        writeLibrary();
        return prevDVD;
    }
    
    @Override
    public DVD removeDVD(String dvdID) throws DVDLibraryDaoException {
        loadLibrary();
        DVD removedDVD = dvds.remove(dvdID);
        writeLibrary();
        return removedDVD;
    }
    
    @Override
    public DVD editDVD(String dvdID, DVD dvd, String prevDVDTitle) throws DVDLibraryDaoException {
        loadLibrary();
        prevDVDTitle = dvd.getDVDID(); // Gets DVD ID
        DVD editDVD = dvds.remove(prevDVDTitle); // removes ^
        editDVD = dvds.put(dvdID, dvd); // puts new DVD ID
        writeLibrary();
        return editDVD;
    }
    
    

    @Override
    public DVD changeTitle(String dvdID, String dvd) throws DVDLibraryDaoException {
        loadLibrary();
        DVD dvdToEdit = dvds.get(dvdID); // Gets DVD ID
        dvdToEdit.setTitle(dvd); // Sets new DVD Title
        writeLibrary();
        return dvdToEdit;
    }
    
    @Override
    public DVD changeReleaseDate(String dvdID, String releaseDate) throws DVDLibraryDaoException {
        loadLibrary();
        DVD dvdToEdit = dvds.get(dvdID);
        dvdToEdit.setReleaseDate(releaseDate);
        writeLibrary();
        return dvdToEdit;
    }

    @Override
    public DVD changeMpaaRating(String dvdID, String mpaaRating) throws DVDLibraryDaoException {
        loadLibrary();
        DVD dvdToEdit = dvds.get(dvdID);
        dvdToEdit.setMpaaRating(mpaaRating);
        writeLibrary();
        return dvdToEdit;
    }

    @Override
    public DVD changeDirectorName(String dvdID, String directorName) throws DVDLibraryDaoException {
        loadLibrary();
        DVD dvdToEdit = dvds.get(dvdID);
        dvdToEdit.setDirectorsName(directorName);
        writeLibrary();
        return dvdToEdit;
    }

    @Override
    public DVD changeUserRating(String dvdID, String userRating) throws DVDLibraryDaoException {
        loadLibrary();
        DVD dvdToEdit = dvds.get(dvdID);
        dvdToEdit.setUserRating(userRating);
        writeLibrary();
        return dvdToEdit;
    }

    @Override
    public DVD changeStudioName(String dvdID, String studioName) throws DVDLibraryDaoException {
        loadLibrary();
        DVD dvdToEdit = dvds.get(dvdID);
        dvdToEdit.setStudioName(studioName);
        writeLibrary();
        return dvdToEdit;
    }

    private DVD unmarshallDVD(String dvdAsText){
        
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String dvdID = dvdTokens[0];
        
        DVD dvdFromFile = new DVD(dvdID);
        dvdFromFile.setTitle(dvdTokens[1]);
        dvdFromFile.setReleaseDate(dvdTokens[2]);
        dvdFromFile.setMpaaRating(dvdTokens[3]);
        dvdFromFile.setDirectorsName(dvdTokens[4]);
        dvdFromFile.setStudioName(dvdTokens[5]);
        dvdFromFile.setUserRating(dvdTokens[6]);
        return dvdFromFile;
        
    }
    
    private void loadLibrary() throws DVDLibraryDaoException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(
            new BufferedReader(
                new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException(
                "-_- Could not load library data into memory.", e);
        }
        String currentLine;
        DVD currentDVD;
        
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentDVD = unmarshallDVD(currentLine);
            
            dvds.put(currentDVD.getDVDID(), currentDVD);
        }
        scanner.close();
    }
    
    private String marshallDVD(DVD aDVD){
        String dvdAsText = aDVD.getDVDID() + DELIMITER;
        
        dvdAsText += aDVD.getTitle() + DELIMITER;
        dvdAsText += aDVD.getReleaseDate() + DELIMITER;
        dvdAsText += aDVD.getMpaaRating() + DELIMITER;
        dvdAsText += aDVD.getDirectorsName() + DELIMITER;
        dvdAsText += aDVD.getStudioName() + DELIMITER;
        dvdAsText += aDVD.getUserRating() + DELIMITER;
        
        return dvdAsText;
    }
    
    private void writeLibrary() throws DVDLibraryDaoException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e){
            throw new DVDLibraryDaoException(
                "Could not save DVD data.", e);   
        }
        String dvdAsText;
        List<DVD> dvdList = this.getAllDVDs();
        for (DVD currentDVD : dvdList) {
            dvdAsText = marshallDVD(currentDVD);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }
}