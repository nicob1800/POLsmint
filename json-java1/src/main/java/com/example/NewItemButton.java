package com.example;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewItemButton implements ActionListener{
    private final Gui gui;

    public NewItemButton(Gui gui){
        this.gui = gui;
    }
    @Override
    public void actionPerformed(ActionEvent e) {       
        gui.initializeNew();
        gui.addNewItemComponents();
        gui.newItemFrame.setVisible(true);
    }
}