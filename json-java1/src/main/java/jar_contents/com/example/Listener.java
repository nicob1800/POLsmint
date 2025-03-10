package com.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class Listener implements ActionListener{
    private final Gui gui;
    private int ids[];
    private String bclass, bmat;

    public Listener(Gui gui){
        this.gui = gui;
    }
    @Override
    public void actionPerformed(ActionEvent e){

        String input = gui.in.getText();
        Item retItem;
        
        try {
            if(check(input)){
                retItem = getItemById(Integer.parseInt(input));
                if(retItem == null){
                    System.out.println("IT IS NULL");//Need to switch for an error box. Just change name to null
                }else{
                    gui.viewName.setText(retItem.getName());
                    gui.viewType.setText(bmat);
                    gui.viewClass.setText(bclass);
                    gui.viewSize.setText(String.valueOf(retItem.getSize())); //Need to figure out
                    gui.viewDate.setText(retItem.getDate());
                    gui.viewStatus.setText(String.valueOf(retItem.getStatus()));
                    gui.viewMember.setText(retItem.getMember());
                    gui.viewComments.setText(retItem.getComments());
                }
                

            }
        } catch (IOException e1) {
            System.out.println("ERROR");

        }
        
        
    
    }

    private Item getItemById(int id) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(Launcher.PATH);
        ArrayNode arrayNode;

        if (file.exists()) {
            arrayNode = (ArrayNode) objectMapper.readTree(file);
        } else {
            return null;
        }

        for (JsonNode jsonNode : arrayNode) {
            if (jsonNode.get("ID").asInt() == id) {
                
                bclass = (String) BoatClass.valueOf(jsonNode.get("Boat Class").asText()).toString();
                bmat = (String) ItemType.valueOf(jsonNode.get("Material").asText()).toString();
                return new Item(
                    jsonNode.get("ID").asInt(),
                    jsonNode.get("Name").asText(),
                    BoatClass.valueOf(jsonNode.get("Boat Class").asText()),
                    jsonNode.get("Size").asDouble(),
                    jsonNode.get("Status").asDouble(),
                    jsonNode.get("Date").asText(),
                    jsonNode.get("Member").asText(),
                    jsonNode.get("Comments").asText()
                );
                
                
            }
        }
        return null;
    }

    public boolean check(String input) throws IOException{
        
        int id = Integer.parseInt(input);
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(Launcher.PATH);
        ArrayNode arrayNode;
        boolean status = false;
        
        if(file.exists()){
            arrayNode = (ArrayNode) objectMapper.readTree(file);
        }else{
            arrayNode = objectMapper.createArrayNode();
        }

        ids = new int[arrayNode.size()];

        for (int i = 0; i < arrayNode.size(); i++){
            JsonNode item = arrayNode.get(i);
            ids[i] = item.get("ID").asInt();
            //System.out.println("THIS IS THE ARRAY: " + ids[i]); // DEBUG LINE, COMMENT OUT
        }

        for (int j = 0; j < arrayNode.size(); j++){
            //System.out.println("THIS IS THE ID: " + id); // DEBUG LINE, COMMENT OUT
            if (id == ids[j]){
                status = true;
            }
            
        }
        if (status == false){
            JFrame alreadyError = gui.createErrorMess("ID Error", "No Item with this ID", 350, 350);
            alreadyError.setVisible(true);
            return false;
        }
        return true;
    }
}