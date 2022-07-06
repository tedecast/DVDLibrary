/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.DVDLibraryController;
import com.sg.dvdlibrary.dao.DVDLibraryAuditDao;
import com.sg.dvdlibrary.dao.DVDLibraryAuditDaoFileImpl;
import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.sg.dvdlibrary.service.DVDLibraryServiceLayer;
import com.sg.dvdlibrary.service.DVDLibraryServiceLayerImpl;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;

/**
 *
 * @author Teresa
 */
public class App {
    
    public static void main(String[] args) {
        UserIO myIO = new UserIOConsoleImpl();
        DVDLibraryView myView = new DVDLibraryView(myIO);
        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
        DVDLibraryAuditDao myAuditDao = new DVDLibraryAuditDaoFileImpl();
        DVDLibraryServiceLayer myService = new DVDLibraryServiceLayerImpl(myDao, myAuditDao);
        DVDLibraryController controller = new DVDLibraryController(myService, myView);
        controller.run();
    }
}
