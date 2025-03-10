package com.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Table implements ActionListener{
    private final Gui gui;
    
    public Table(Gui gui){
        this.gui = gui;
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println("HELLO");
        gui.initializeTable();
    }
}