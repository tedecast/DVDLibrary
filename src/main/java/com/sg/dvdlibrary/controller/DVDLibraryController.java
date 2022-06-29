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
    
    private DVDLibraryView view;
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
                        findDVDByTitle();
                        break;
                    case 4: 
                        addDVD();
                        break;
                    case 5:
                        removeDVD();
                        break;
                    case 6:
                        editDVD();
                        break;
                    case 7:
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
    private void unknownCommand(){
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage(){
        view.displayExitBanner();
    }
     
    private void listDVDs() throws DVDLibraryDaoException {
        view.displayDVDListBanner();
        List<DVD> dvdList = dao.getAllDVDs(); // Gets list from dao
        view.displayDVDList(dvdList); // Displays DVD list from view in : format
    }
    
    private void viewDVDInfo() throws DVDLibraryDaoException {
        view.displayDisplayDVDBanner();
        String dvdID = view.getDVDID(); // Gets DVD ID from user
        DVD dvd = dao.getDVD(dvdID); // Gets DVD and then
        view.displayDVDInfo(dvd); // displays result whether it exists or not
    }
    
    private void findDVDByTitle() throws DVDLibraryDaoException {
        String title = view.getTitle();
        DVD dvd = dao.findDVDByTitle(title);
        view.displayDVDByTitle(dvd);
    }
    
    private void addDVD() throws DVDLibraryDaoException {
        boolean keepAdding = true;
        while(keepAdding){
            view.displayAddDVDBanner();
            DVD newDVD = view.getNewDVDInfo(); // asks user for newDVDInfo
            dao.addDVD(newDVD.getDVDID(), newDVD);
            String userResponse = view.displayKeepAddingBanner();
            if(userResponse.equals("n")){
                keepAdding = false;
            } 
        }
        view.displayFinishedAddingResult(); // finished adding, please hit enter to continue
    }
    
    public void removeDVD() throws DVDLibraryDaoException {
        boolean keepRemoving = true;
        while(keepRemoving){
            view.displayRemoveDVDBanner();
            String dvdID = view.getRemoveID();
            DVD removedDVD = dao.removeDVD(dvdID); // asks user for DVD ID to remove
            view.displayRemoveResult(removedDVD);
            String userResponse = view.displayKeepRemovingBanner();
            if(userResponse.equals("n")){
                keepRemoving = false;
            } 
        }
        view.displayFinishedRemoveResult();
    }
   
    
    private int getEditMenuSelection() throws DVDLibraryDaoException {
        return view.printEditMenu();
    }
    private void editDVD() throws DVDLibraryDaoException {
        
        int editMenuSelection = 0;
        String dvdID;
        DVD editedDVD;
        String userResponse = "";
        boolean keepEditing = true;
        boolean preStopEdit = false; //premature stop editing
        while(keepEditing){
            dvdID = view.getEditTitleID(); // Please enter a DVD Title ID to edit
            editedDVD = dao.getDVD(dvdID); // Gets DVD ID from user ^
            
            while(editedDVD == null){
                
                view.displayDoesNotExist(); // displays No such dvd Exists, then asks until ID is true
                userResponse = view.displayKeepEditingBanner();
                if(userResponse.equals("n")){
                    preStopEdit = true;
                    break;
                }
                
                dvdID = view.getEditTitleID(); // Please enter a DVD Title ID to edit
                editedDVD = dao.getDVD(dvdID); // Gets DVD ID from user ^
                
            }if (preStopEdit){
                keepEditing = false;
                break;
            } 
            view.displayDVDSummary(editedDVD);
            editMenuSelection = getEditMenuSelection(); // Edit Menu without the +summary

            switch(editMenuSelection) {
                case 1:
                    editTitle(dvdID);
                    userResponse = view.displayKeepEditingBanner();
                    break;
                case 2: 
                    editReleaseDate(dvdID);
                    userResponse = view.displayKeepEditingBanner();
                    break;
                case 3: 
                    editMpaaRating(dvdID);
                    userResponse = view.displayKeepEditingBanner();
                    break;
                case 4:
                    editDirectorName(dvdID);
                    userResponse = view.displayKeepEditingBanner();
                    break;
                case 5:
                    editStudioName(dvdID);
                    userResponse = view.displayKeepEditingBanner();
                    break;
                case 6:
                    editUserRating(dvdID);
                    userResponse = view.displayKeepEditingBanner();
                    break;
                case 7: 
                    editReleaseDate(dvdID);
                    editMpaaRating(dvdID);
                    editDirectorName(dvdID);
                    editStudioName(dvdID);
                    editUserRating(dvdID);
                    userResponse = view.displayKeepEditingBanner();
                    break;
                case 8:
                    keepEditing = false;
                    break;
                default:
                    unknownCommand();
            }
            if(userResponse.equals("n")){
                keepEditing = false;
            }
        }
        view.displayFinishedEditingResult(); // Finished editing DVDs. Please hit enter to continue. 
    }           
             
       
    private void editTitle(String dvdID) throws DVDLibraryDaoException {
        String newTitle = view.getTitle(); // Please enter a title
        dao.changeTitle(dvdID, newTitle); // changes title 
        view.displayEditSuccess(); // displays edit success
          
    }
    
    private void editReleaseDate(String dvdID) throws DVDLibraryDaoException {
        String newReleaseDate = view.getReleaseDate();
        dao.changeReleaseDate(dvdID, newReleaseDate);
        view.displayEditSuccess(); 
    }
    
    private void editMpaaRating(String dvdID) throws DVDLibraryDaoException {
        String newMpaaRating = view.getMpaaRating();
        dao.changeMpaaRating(dvdID, newMpaaRating);
        view.displayEditSuccess(); 
    }
    
    private void editDirectorName(String dvdID) throws DVDLibraryDaoException {
        String newDirectorName = view.getDirectorName();
        dao.changeDirectorName(dvdID, newDirectorName);
        view.displayEditSuccess(); 
    }
    
    private void editUserRating(String dvdID) throws DVDLibraryDaoException {
        String newUserRating = view.getUserRating();
        dao.changeUserRating(dvdID, newUserRating);
        view.displayEditSuccess(); 
    }
    
    private void editStudioName(String dvdID) throws DVDLibraryDaoException {
        String newStudioName = view.getStudioName();
        dao.changeStudioName(dvdID, newStudioName);
        view.displayEditSuccess(); 
    }
    
}
