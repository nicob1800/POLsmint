package com.example;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class SaveAndClose implements ActionListener {
    private final Gui gui;
    private final Item item;
    private int[] ids;
    public boolean close;
    
    public SaveAndClose(Gui gui, Item item){
        this.gui = gui;
        this.item = item;
    }
    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public void actionPerformed(ActionEvent e){
        
        
        try {
            print(
            item.getID(),
            item.getName(),
            item.getBoatClass().toString(),
            item.getMaterial().toString(),
            item.getStatus(),
            item.getDate(),
            item.getSize(),
            item.getMember(),
            item.getComments()
            );
                
            } catch (IOException ex) {
                ex.printStackTrace();
                //System.out.println("ERRORERROERROERROE"); // DEBUG LINE, COMMENT OUT
            }if(close){
                //System.out.println(item.getItem()); // DEBUG LINE, COMMENT OUT
                JFrame saved = gui.createErrorMess("Saved!", "Saved!", 100, 100);
            saved.setVisible(true);
                gui.newItemFrame.dispose();
            }
        
    }
    public void print(int id, String name, String boatClass, String material, double status, String date, double size, String member, String comments) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(Launcher.PATH);
        ArrayNode arrayNode;
        
        if(file.exists()){
            arrayNode = (ArrayNode) objectMapper.readTree(file);
        }else{
            arrayNode = objectMapper.createArrayNode();
        }

        ids = new int[arrayNode.size()];

        for (int i = 0; i < arrayNode.size(); i++){
            JsonNode item = arrayNode.get(i);
            ids[i] = item.get("ID").asInt();
            //System.out.println("THIS IS THE ARRAY: " + ids[i]); // DEBUG LINE. COMMENT OUT
        }

        for (int j = 0; j < arrayNode.size(); j++){
            //System.out.println("THIS IS THE ID: " + id);// DEBUG LINE COMMENT OUT
            if (id == ids[j]){
                JFrame alreadyError = gui.createErrorMess("ID Error", "This ID is already in use. Item not saved", 350, 350);
                alreadyError.setVisible(true);
                close = false;
                return;
            }
        }
        
        ObjectNode jsonNode1 = objectMapper.createObjectNode();
        jsonNode1.put("ID", id);
        jsonNode1.put("Name", name);
        jsonNode1.put("Boat Class", boatClass);
        jsonNode1.put("Material", material);
        jsonNode1.put("Status", status);
        jsonNode1.put("Date", date);
        jsonNode1.put("Size", size);
        jsonNode1.put("Member", member);
        jsonNode1.put("Comments", comments);

        arrayNode.add(jsonNode1);
        
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, arrayNode);
        close = true;
    }
}