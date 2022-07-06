/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.service.DVDLibraryDataValidationException;
import com.sg.dvdlibrary.service.DVDLibraryDuplicateIdException;
import com.sg.dvdlibrary.service.DVDLibraryServiceLayer;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author Teresa
 */
public class DVDLibraryController {
    
    private DVDLibraryServiceLayer service;
    private DVDLibraryView view;
    
    public DVDLibraryController(DVDLibraryServiceLayer service, DVDLibraryView view){
        this.service = service;
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
            } catch (DVDLibraryPersistenceException e){
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
     
    private void listDVDs() throws DVDLibraryPersistenceException {
        view.displayDVDListBanner();
        List<DVD> dvdList = service.getAllDVDs(); // Gets list from dao
        view.displayDVDList(dvdList); // Displays DVD list from view in : format
    }
    
    private void viewDVDInfo() throws DVDLibraryPersistenceException {
        view.displayDisplayDVDBanner();
        String dvdID = view.getDVDID(); // Gets DVD ID from user
        DVD dvd = service.getDVD(dvdID); // Gets DVD and then
        view.displayDVDInfo(dvd); // displays result whether it exists or not
    }
    
    private void findDVDByTitle() throws DVDLibraryPersistenceException {
        view.displayFindDVDBanner();
        String title = view.getTitle();
        DVD dvd = service.findDVDByTitle(title);
        view.displayDVDByTitle(dvd);
    }
    
    private void addDVD() throws DVDLibraryPersistenceException {
        boolean keepAdding = true;
        while(keepAdding){
            view.displayAddDVDBanner();
            boolean hasErrors = false;
            
            do {
                DVD newDVD = view.getNewDVDInfo(); // asks user for newDVDInfo
                try {
                    service.addDVD(newDVD);
                    view.displayFinishedAddingResult(); // finished adding, please hit enter to continue
                    hasErrors = false;
                } catch(DVDLibraryDuplicateIdException | DVDLibraryDataValidationException e){
                    hasErrors = true;
                    view.displayErrorMessage(e.getMessage());
                }
                
            } while (hasErrors);
            String userResponse = view.displayKeepAddingBanner();
            if(userResponse.equals("n")){
                keepAdding = false;
            } 
        }
        
    }
    
    public void removeDVD() throws DVDLibraryPersistenceException {
        boolean keepRemoving = true;
        while(keepRemoving){
            view.displayRemoveDVDBanner();
            String dvdID = view.getRemoveID();
            DVD removedDVD = service.removeDVD(dvdID); // asks user for DVD ID to remove
            view.displayRemoveResult(removedDVD);
            String userResponse = view.displayKeepRemovingBanner();
            if(userResponse.equals("n")){
                keepRemoving = false;
            } 
        }
        view.displayFinishedRemoveResult();
    }
   
    
    private int getEditMenuSelection() throws DVDLibraryPersistenceException {
        return view.printEditMenu();
    }
    private void editDVD() throws DVDLibraryPersistenceException {
        
        int editMenuSelection = 0;
        String dvdID;
        DVD editedDVD;
        String userResponse = "";
        boolean keepEditing = true;
        boolean preStopEdit = false; //premature stop editing
        while(keepEditing){
            dvdID = view.getEditTitleID(); // Please enter a DVD Title ID to edit
            editedDVD = service.getDVD(dvdID); // Gets DVD ID from user ^
            
            while(editedDVD == null){
                
                view.displayDoesNotExist(); // displays No such dvd Exists, then asks until ID is true
                userResponse = view.displayKeepEditingBanner();
                if(userResponse.equals("n")){
                    preStopEdit = true; //stops editing, goes to Finished editing, please hit enter to continue.
                    break;
                }
                
                dvdID = view.getEditTitleID(); // Please enter a DVD Title ID to edit
                editedDVD = service.getDVD(dvdID); // Gets DVD ID from user ^
                
            }if (preStopEdit){
                keepEditing = false;
                break;
            } 
            view.displayDVDSummary(editedDVD); // Edit Menu with +summary
            editMenuSelection = getEditMenuSelection(); // Edit Menu without the +summary

            switch(editMenuSelection) {
                case 1:
                    editTitle(dvdID);
                    view.displayEditSuccess();
                    userResponse = view.displayKeepEditingBanner(); // Displays DVD edit success
                    break;
                case 2: 
                    editReleaseDate(dvdID);
                    view.displayEditSuccess();
                    userResponse = view.displayKeepEditingBanner();
                    break;
                case 3: 
                    editMpaaRating(dvdID);
                    view.displayEditSuccess();
                    userResponse = view.displayKeepEditingBanner();
                    break;
                case 4:
                    editDirectorName(dvdID);
                    view.displayEditSuccess();
                    userResponse = view.displayKeepEditingBanner();
                    break;
                case 5:
                    editStudioName(dvdID);
                    view.displayEditSuccess();
                    userResponse = view.displayKeepEditingBanner();
                    break;
                case 6:
                    editUserRating(dvdID);
                    view.displayEditSuccess();
                    userResponse = view.displayKeepEditingBanner();
                    break;
                case 7: 
                    editTitle(dvdID);
                    editReleaseDate(dvdID);
                    editMpaaRating(dvdID);
                    editDirectorName(dvdID);
                    editStudioName(dvdID);
                    editUserRating(dvdID);
                    view.displayEditSuccess();
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
             
       
    private void editTitle(String dvdID) throws DVDLibraryPersistenceException {
        String newTitle = view.getTitle(); // Please enter a title
        service.changeTitle(dvdID, newTitle); // changes title 
          
    }
    
    private void editReleaseDate(String dvdID) throws DVDLibraryPersistenceException {
        String newReleaseDate = view.getReleaseDate();
        service.changeReleaseDate(dvdID, newReleaseDate);
    }
    
    private void editMpaaRating(String dvdID) throws DVDLibraryPersistenceException {
        String newMpaaRating = view.getMpaaRating();
        service.changeMpaaRating(dvdID, newMpaaRating);
    }
    
    private void editDirectorName(String dvdID) throws DVDLibraryPersistenceException {
        String newDirectorName = view.getDirectorName();
        service.changeDirectorName(dvdID, newDirectorName);
    }
    
    private void editUserRating(String dvdID) throws DVDLibraryPersistenceException {
        String newUserRating = view.getUserRating();
        service.changeUserRating(dvdID, newUserRating);
    }
    
    private void editStudioName(String dvdID) throws DVDLibraryPersistenceException {
        String newStudioName = view.getStudioName();
        service.changeStudioName(dvdID, newStudioName); 
    }
    
}
