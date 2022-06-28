/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author Teresa
 */
public class DVDLibraryView {
    
    private UserIO io; //= new UserIOConsoleImpl();
    
    public DVDLibraryView(UserIO io){
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Display DVD List");
        io.print("2. View DVD Information");
        io.print("3. Add DVD");
        io.print("4. Remove DVD");
        io.print("5. Edit DVD");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }
    
    public String getTitle(){
        return io.readString("Please enter a DVD title.");
    }
    public String getReleaseDate() {
        return io.readString("Please enter a DVD release date.");
    }
    public String getMpaaRating() {
        return io.readString("Please enter the MPAA rating.");
    }
    public String getDirectorName(){
        return io.readString("Please enter the director's name.");
    }
    public String getStudioName() {
        return io.readString("Please enter the studio.");
    }
    public String getUserRating() {
        return io.readString("Please enter your comments.");
    }
    
    public String getEditTitle(){
        return io.readString("Please enter a DVD title to edit.");
    }
    
    public void getHitEnter() {
        io.readString("Please hit enter to continue.");
    }
    
    public void displayDoesNotExist() {
        io.print("No such DVD exists.");
    }
     
    public void displayDVDListBanner() {
        io.print("=== Display DVD List ===");
    }
    public void displayDVDList(List<DVD> dvdList){
        for (DVD currentDVD : dvdList) {
            String dvdInfo = String.format("%s : %s : %s : %s : %s : %s",
                   currentDVD.getTitle(),                   
                   currentDVD.getReleaseDate(),
                   currentDVD.getMpaaRating(),
                   currentDVD.getDirectorsName(),
                   currentDVD.getStudioName(),
                   currentDVD.getUserRating());
            io.print(dvdInfo);
        }
        getHitEnter();
    }
    
    public void displayDisplayDVDBanner() {
        io.print("=== Display DVD Information ===");
    }
    public void displayDVDInfo(DVD dvd){
        if (dvd != null){
            io.print("Title: " + dvd.getTitle());
            io.print("Release Date: " + dvd.getReleaseDate());
            io.print("MPAA Rating: " + dvd.getMpaaRating());
            io.print("Director: " + dvd.getDirectorsName());
            io.print("Studio: " + dvd.getStudioName());
            io.print("Rating: " + dvd.getUserRating());
            io.print("");
        } else {
           displayDoesNotExist();
        }
        getHitEnter();
    }
    
    
    public void displayAddDVDBanner() {
        io.print("=== Add DVD ===");
    }
   
    public DVD getNewDVDInfo() {
        
        String title = getTitle();
        String releaseDate = getReleaseDate();
        String mpaaRating = getMpaaRating();
        String directorsName = getDirectorName();
        String studioName = getStudioName();
        String userRating = getUserRating();
        
        DVD currentDVD = new DVD(title); //id instead
        currentDVD.setTitle(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMpaaRating(mpaaRating);
        currentDVD.setDirectorsName(directorsName);
        currentDVD.setStudioName(studioName);
        currentDVD.setUserRating(userRating);
        return currentDVD;
            
    }
    public String displayKeepAddingBanner() {
       return io.readString("DVD successfully added. Keep adding DVDs? (y/n)");
    }
    public void displayFinishedAddingResult(){
        io.readString("Finished adding DVDs. Please hit enter to continue.");
    }
    
    public void displayFinishedRemoveResult(){
        io.readString("Finished removing DVDs. Please hit enter to continue.");
    }
    
    public void displayFinishedEditingResult(){
        io.readString("Finished editing DVDs. Please hit enter to continue.");
    }
//    public String displayKeepEditingBanner(){
//        return io.readString("Keep removing DVDs? (y/n)");
//    } 
    
    
    public void displayRemoveDVDBanner(){
        io.print("=== Remove DVD ===");
    }
    
    public String displayKeepRemovingBanner(){
        return io.readString("Keep removing DVDs? (y/n)");
    }       
    public void displayRemoveResult(DVD dvdRecord){
        if(dvdRecord != null){
            io.print("DVD successfully removed.");
        }else{
            displayDoesNotExist();
        }
        
    }   
    
    public void displayEditDVDBanner(){
        io.print("=== Edit DVD ===");
    }
    public int printEditMenuAndGetSelection(DVD dvd){
        if (dvd != null){
            io.print("=== Edit DVD ===");
            io.print("1. Title: " + dvd.getTitle());
            io.print("2. Release Date: " + dvd.getReleaseDate());
            io.print("3. MPAA rating: " + dvd.getMpaaRating());
            io.print("4. Director's name: " + dvd.getDirectorsName());
            io.print("5. Studio: " + dvd.getStudioName());
            io.print("6. Comments: " + dvd.getUserRating());
            io.print("7. Edit All");
            io.print("8. Exit");
            
        }else{
            displayDoesNotExist();
        }
        return io.readInt("Please select from the above choices.", 1, 8);
    }
//    public String displayKeepEditingBanner(){
//        return io.readString("Keep editing DVDs? (y/n)");
//    }
    public String displayKeepEditingBanner(){
        return io.readString("DVD successfully edited. Keep editing DVDs? (y/n)");
    }
    public void displayEditDVDResult(DVD dvdRecord){
        if(dvdRecord != null){
            io.print("DVD successfully edited.");
        }else{
//            displayDoesNotExist();
        io.print("No such DVD exists.");
        }
        getHitEnter();
    }
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    
    public void displayUnknownCommandBanner(){
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg){
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
