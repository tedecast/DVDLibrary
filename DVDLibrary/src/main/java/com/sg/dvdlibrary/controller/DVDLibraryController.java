/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoException;
import com.sg.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author Teresa
 */
public class DVDLibraryController {
    
    private DVDLibraryView view; //= new DVDLibraryView();
    private DVDLibraryDao dao = new DVDLibraryDaoFileImpl();
    private UserIO io = new UserIOConsoleImpl();
    
    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view){
        this.dao = dao;
        this.view = view;
    }
     
    private void listDVDs() throws DVDLibraryDaoException {
        view.displayDVDListBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }
    
    private void viewDVDInfo() throws DVDLibraryDaoException {
        view.displayDisplayDVDBanner();
        String title = view.getTitle();
        DVD dvd = dao.getDVD(title);
        view.displayDVDInfo(dvd);
    }
    
    private void addDVD() throws DVDLibraryDaoException {
        boolean keepAdding = true;
        while(keepAdding){
            view.displayAddDVDBanner();
            DVD newDVD = view.getNewDVDInfo();
            dao.addDVD(newDVD.getTitle(), newDVD);
            String userResponse = view.displayKeepAddingBanner();
            if(userResponse.equals("n")){
                keepAdding = false;
            } 
        }
        view.displayAddSuccessBanner();
    }
    
    private void editDVD() throws DVDLibraryDaoException {
        String title = view.getEditTitle();
        DVD dvd = dao.getDVD(title);
        boolean keepEditing = true;
        int choice;
        
//        DVD newDVD = view.getEditedDVDInfo();
//        dao.editDVD(newDVD.getTitle(), newDVD);

        if (dvd != null){
            while (keepEditing){
                view.displayEditDVDBanner();
                choice = view.displayEditDVDChoices(dvd);
                switch(choice) {
                    case 1:
                        dvd.setTitle(view.getTitle());
                        break;
                    case 2: 
                        dvd.setReleaseDate(view.getReleaseDate());
                        break;
                    case 3: 
                        dvd.setMpaaRating(view.getMpaaRating());
                        break;
                    case 4:
                        dvd.setDirectorsName(view.getDirectorName());
                        break;
                    case 5:
                        dvd.setStudioName(view.getStudioName());
                        break;
                    case 6:
                        dvd.setUserRating(view.getUserRating());
                        break;
                    case 7: 
                        dvd.setTitle(view.getTitle());
                        dvd.setReleaseDate(view.getReleaseDate());
                        dvd.setMpaaRating(view.getMpaaRating());
                        dvd.setDirectorsName(view.getDirectorName());
                        dvd.setStudioName(view.getStudioName());
                        dvd.setUserRating(view.getUserRating());
                        break;
                    case 8:
                        keepEditing = false;
                        break;
                }
            }
        }
    }
    
    public void removeDVD() throws DVDLibraryDaoException {
        boolean keepRemoving = true;
        while(keepRemoving){
            view.displayRemoveDVDBanner();
            String title = view.getTitle();
            DVD removedDVD = dao.removeDVD(title);
             view.displayRemoveResult(removedDVD);
            String userResponse = view.displayKeepRemovingBanner();
            if(userResponse.equals("n")){
                keepRemoving = false;
            } 
        }
        view.displayFinishedRemoveResult();
    }
    
    private void unknownCommand(){
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage(){
        view.displayExitBanner();
    }
    
    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }
    
    public void run(){
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
        while (keepGoing){
            
            menuSelection = getMenuSelection();
            
            switch (menuSelection){
                case 1:
                    listDVDs();
                    break;
                case 2:
                    viewDVDInfo();
                    break;
                case 3: 
                    addDVD();
                    break;
                case 4:
                    editDVD();
                    break;
                case 5:
                   removeDVD();
                   break;
                case 6:
                    keepGoing = false;
                    break;
                default: 
                    unknownCommand();
            }
            
        }
        exitMessage();
    } catch (DVDLibraryDaoException e){
        view.displayErrorMessage(e.getMessage());
    }
}
}
