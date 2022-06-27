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
    public DVD getDVD(String title) throws DVDLibraryDaoException {
        loadLibrary();
        return dvds.get(title);
    }

    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException {
        loadLibrary();
        DVD prevDVD = dvds.put(title, dvd);
        writeLibrary();
        return prevDVD;
    }

    @Override
    public DVD editDVD(String title, DVD dvd, String prevDVDTitle) throws DVDLibraryDaoException {
        loadLibrary();
        prevDVDTitle = dvd.getTitle();
        DVD editDVD = dvds.remove(prevDVDTitle);
        editDVD = dvds.put(title, dvd);
        writeLibrary();
        return editDVD;
    }

    @Override
    public DVD removeDVD(String title) throws DVDLibraryDaoException {
        loadLibrary();
        DVD removedDVD = dvds.remove(title);
        writeLibrary();
        return removedDVD;
    }

//    @Override
//    public DVD viewDVD(String dvdLibrary, DVD dvd) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
    private DVD unmarshallDVD(String dvdAsText){
        
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String title = dvdTokens[0];
        DVD dvdFromFile = new DVD(title);
        dvdFromFile.setReleaseDate(dvdTokens[1]);
        dvdFromFile.setMpaaRating(dvdTokens[2]);
        dvdFromFile.setDirectorsName(dvdTokens[3]);
        dvdFromFile.setStudioName(dvdTokens[4]);
        dvdFromFile.setUserRating(dvdTokens[5]);
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
            
            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        scanner.close();
    }
    
    private String marshallDVD(DVD aDVD){
        String dvdAsText = aDVD.getTitle() + DELIMITER;
        
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
