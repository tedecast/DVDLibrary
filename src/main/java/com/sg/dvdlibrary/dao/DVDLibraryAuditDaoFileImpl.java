/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Teresa
 */
public class DVDLibraryAuditDaoFileImpl implements DVDLibraryAuditDao {
    
    public static final String AUDIT_FILE = "audit.txt";

    @Override
    public void writeAuditEntry(String entry) throws DVDLibraryPersistenceException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException ex) {
           throw new DVDLibraryPersistenceException("Could not persist audit information.", ex);
        }
        
        LocalDateTime timeStamp = LocalDateTime.now();
        out.println(timeStamp.toString() + " : " + entry);
        out.flush();
    }
    
}
