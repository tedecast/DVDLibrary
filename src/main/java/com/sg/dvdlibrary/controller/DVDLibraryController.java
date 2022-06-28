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
                    removeDVD();
                    break;
                case 5:
                    editDVD();
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
    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
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
//                List<DVD> dvdList = dao.getAllDVDs();
        while(keepAdding){
            view.displayAddDVDBanner();
            DVD newDVD = view.getNewDVDInfo();
            dao.addDVD(newDVD.getTitle(), newDVD);
            String userResponse = view.displayKeepAddingBanner();
            if(userResponse.equals("n")){
                keepAdding = false;
            } 
        }
        view.displayFinishedAddingResult();
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
    private int getEditMenuSelection() throws DVDLibraryDaoException {
//        view.displayEditDVDBanner();
//        String title = view.getEditTitle();
//        DVD dvd = dao.getDVD(title);
        return view.printEditMenu();
    }
    private void editDVD() throws DVDLibraryDaoException {
//        view.displayEditDVDBanner();
        String dvdID = view.getEditTitle();
        DVD dvd = dao.getDVD(dvdID); 
        
        if (dvd == null){
            view.displayDoesNotExist();
            } else {
                int editMenuSelection = 0;
                boolean keepEditing = true;
                while (keepEditing){
                    editMenuSelection = getEditMenuSelection();
                    
                switch(editMenuSelection) {
                    case 1:
                        editTitle(dvdID);
                        break;
                    case 2: 
                        editReleaseDate(dvdID);
                        break;
                    case 3: 
                        editMpaaRating(dvdID);
                        break;
                    case 4:
                        editDirectorName(dvdID);
                        break;
                    case 5:
                        editStudioName(dvdID);
                        break;
                    case 6:
                        editUserRating(dvdID);
                        break;
                    case 7: 
                        editReleaseDate(dvdID);
                        editMpaaRating(dvdID);
                        editDirectorName(dvdID);
                        editStudioName(dvdID);
                        editUserRating(dvdID);
                        break;
                    case 8:
                        keepEditing = false;
                        break;
                    default:
                        unknownCommand();
                } 
                 String userResponse = view.displayKeepEditingBanner();
                    if(userResponse.equals("n")){
                        keepEditing = false;
                    }
            }
            view.displayFinishedEditingResult();
        }
    }
    
    private void editTitle(String dvdID) throws DVDLibraryDaoException {
          String newTitle = view.getTitle();
          dao.changeTitle(dvdID, newTitle);
//        String title = getTitle();
//        DVD editedDVD = dao.changeTitle(title);
//        view.displayEditResult();
   }
    
    private void editReleaseDate(String dvdID) throws DVDLibraryDaoException {
        String newReleaseDate = view.getReleaseDate();
        dao.changeReleaseDate(dvdID, newReleaseDate);
//        view.displayKeepEditingBanner();
    }
    private void editMpaaRating(String dvdID) throws DVDLibraryDaoException {
        String newMpaaRating = view.getMpaaRating();
        dao.changeMpaaRating(dvdID, newMpaaRating);
//        view.displayKeepEditingBanner();
    }
    private void editDirectorName(String dvdID) throws DVDLibraryDaoException {
        String newDirectorName = view.getDirectorName();
        dao.changeDirectorName(dvdID, newDirectorName);
//        view.displayKeepEditingBanner();
    }
    private void editUserRating(String dvdID) throws DVDLibraryDaoException {
        String newUserRating = view.getUserRating();
        dao.changeUserRating(dvdID, newUserRating);
//        view.displayKeepEditingBanner();
    }
    private void editStudioName(String dvdID) throws DVDLibraryDaoException {
        String newStudioName = view.getStudioName();
        dao.changeStudioName(dvdID, newStudioName);
//        view.displayKeepEditingBanner();
    }
    
}
