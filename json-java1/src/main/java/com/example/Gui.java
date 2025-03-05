package com.example;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;


public class Gui{

    private JFrame frame, tableFrame;
    private JPanel panel;
    public JTextField in;
    public JTextField out;
    private JButton goButton;
    private JButton newButt;
    private JButton saveButton;
    private JButton saveCloseButton;
    private JButton tableButton;
    private JTable table;
    public JFrame newItemFrame;
    private final String FONT = "Arial";
    public JPanel newItemPanel;
    private JTextField newId, newName, newSize, newDate, newStatus, newMember;
    public JTextField  viewName, viewSize, viewDate, viewStatus, viewMember, viewClass, viewType;
    private JLabel viewidLabel, viewtypeLabel, viewnameLabel, viewsizeLabel, viewdateLabel, viewstatusLabel, viewmemberLabel, viewclassLabel, viewcommentsLabel;
    private JLabel idLabel,nameLabel, sizeLabel, dateLabel, statusLabel, memberLabel, classLabel, commentsLabel;
    private JComboBox<BoatClass> newClass;
    public JTextArea newComments, viewComments;
    private final BoatClass[] classes = {BoatClass.MKV, BoatClass.HURRICANE, BoatClass.ZODIAKMKII};
    private final int num = 30;
    private final int dif = 55;
    

    public Gui(){
        initializeMain();
        //initializeNew();
        addAll();
        
    }

    private void initializeMain(){

        frame = createFrame("POLsmint", 450, 650, true);
        
        //-------Main Frame Panel----------
        panel = new JPanel();
        panel.setLayout(null);
        in = createTextField(100, Color.BLACK, Color.WHITE, 5, 50, 100, 30, true, JTextField.LEFT);//Input


        //-------Buttons-------

        goButton = createButton("Go", 115, 50, 50, 30);
        goButton.addActionListener(new Listener(this));
        newButt = createButton("New", 185, 50, 60, 30);
        newButt.addActionListener(new NewItemButton(this));
        tableButton = createButton("Table", 255, 50, 75, 30);
        tableButton.addActionListener(new Table(this));
        

        //-----Labels-----

        
        viewidLabel = createLabel("ID:", 5, 25, 50, 30);
        viewnameLabel = createLabel("Name:", 5, 2 + dif, 50, 30);
        viewtypeLabel = createLabel("Type", 5, 2 + 2 * dif, 100, 30);
        viewclassLabel = createLabel("Boat Class:", 5, 2 + 3 * dif, 100, 30);
        viewsizeLabel = createLabel("Size (Litres):", 5, 2 + 4 * dif, 150, 30);
        viewdateLabel = createLabel("Date:", 5, 2 + 5 * dif, 50, 30);
        viewstatusLabel = createLabel("Status (Do not include %):", 5, 2 + 6 * dif, 300, 30);
        viewmemberLabel = createLabel("Member:", 5, 2 + 7 * dif, 100, 30);
        viewcommentsLabel = createLabel("Comments:", 5, 2 + 8 * dif, 150, 30);
        

        //------Text Boxes-------

        viewName = createTextField(150, Color.BLACK, Color.WHITE, 5, num + dif, 300, 30, false, JTextField.LEFT);
        viewType = createTextField(150, Color.BLACK, Color.WHITE, 5, num + 2 * dif, 200, 30, false, JTextField.LEFT);
        viewClass = createTextField(140, Color.BLACK, Color.WHITE, 5, num + 3 * dif, 100, 30, false, JTextField.LEFT);
        viewSize = createTextField(100, Color.BLACK, Color.WHITE, 5, num + 4 * dif, 100, 30, false, JTextField.LEFT);
        viewDate = createTextField(100, Color.BLACK, Color.WHITE, 5, num + 5 * dif, 300, 30, false, JTextField.LEFT);
        viewStatus = createTextField(100, Color.BLACK, Color.WHITE, 5, num + 6 * dif, 200, 30, false, JTextField.LEFT);
        viewMember = createTextField(100, Color.BLACK, Color.WHITE, 5, num + 7 * dif, 350, 30, false, JTextField.LEFT);
        viewComments = createTextArea(200, 200, Color.BLACK, Color.WHITE, 5, num + 8 * dif, 150, 120);
        viewComments.setEditable(false);
        
    }
    public void initializeNew(){
        newItemFrame = createFrame("New Item", 500, 600, false);
        
        //-------NEW ITEM FRAME PANEL----------
        newItemPanel = new JPanel();
        newItemPanel.setLayout(null);
        newId = createTextField(100, Color.BLACK, Color.WHITE, 5, num, 100, 30, true, JTextField.LEFT);
        newName = createTextField(150, Color.BLACK, Color.WHITE, 5, num + dif, 300, 30, true, JTextField.LEFT);
        newClass = createDropDown(classes, 5, num + 2 * dif, 150, 30);
        newSize = createTextField(100, Color.BLACK, Color.WHITE, 5, num + 3 * dif, 100, 30, true, JTextField.LEFT);
        newDate = createTextField(100, Color.BLACK, Color.WHITE, 5, num + 4 * dif, 300, 30, true, JTextField.LEFT);
        newStatus = createTextField(100, Color.BLACK, Color.WHITE, 5, num + 5 * dif, 100, 30, true, JTextField.LEFT);
        newMember = createTextField(100, Color.BLACK, Color.WHITE, 5, num + 6 * dif, 350, 30, true, JTextField.LEFT);
        newComments = createTextArea(200, 200, Color.BLACK, Color.WHITE, 5, num + 7 * dif, 150, 120);
        idLabel = createLabel("ID:", 5, 2, 50, 30);
        nameLabel = createLabel("Name:", 5, 2 + dif, 50, 30);
        classLabel = createLabel("Boat Class:", 5, 2 + 2 * dif, 100, 30);
        sizeLabel = createLabel("Size (Litres):", 5, 2 + 3 * dif, 150, 30);
        dateLabel = createLabel("Date:", 5, 2 + 4 * dif, 50, 30);
        statusLabel = createLabel("Status (Do not include %):", 5, 2 + 5 * dif, 300, 30);
        memberLabel = createLabel("Member:", 5, 2 + 6 * dif, 100, 30);
        commentsLabel = createLabel("Comments:", 5, 2 + 7 * dif, 150, 30);
        saveButton = createButton("Save", 175, 2 + 9 * dif, 80, 30);
        saveButton.addActionListener(e -> {
            try{Item item = new Item(
                Integer.parseInt(newId.getText()),
                newName.getText(),
                (BoatClass) newClass.getSelectedItem(),
                Double.parseDouble(newSize.getText()),
                Double.parseDouble(newStatus.getText()),
                newDate.getText(),
                newMember.getText(),
                newComments.getText()
            );
            new Save(Gui.this, item).actionPerformed(e);
        }catch(NumberFormatException ex){
            JFrame inputError = createErrorMess("Input Error", "Wrong input type. Remember: Number, String, Number, String, Number, String, String!", 700, 600);
            inputError.setVisible(true);
        }
        });
        saveCloseButton = createButton("Save and Close", 275, 2 + 9 * dif, 150, 30);
        saveCloseButton.addActionListener(e -> {
            try{
            Item item = new Item(
                Integer.parseInt(newId.getText()),
                newName.getText(),
                (BoatClass) newClass.getSelectedItem(),
                Double.parseDouble(newSize.getText()),
                Double.parseDouble(newStatus.getText()),
                newDate.getText(),
                newMember.getText(),
                newComments.getText()
            );
            new SaveAndClose(Gui.this, item).actionPerformed(e);
        }catch(NumberFormatException ex){
            JFrame inputErrorSE = createErrorMess("Input Error", "Wrong input type. Remember: Number, String, Number, String, Number, String, String!", 700, 600);
            inputErrorSE.setVisible(true);        }
        });


    }
    public void initializeTable(){
        tableFrame = createFrame("Items Table", 1000, 600, false);
        tableFrame.setVisible(true);
        
        
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File jsonFile = new File("C:/Users/Nicol/OneDrive/Desktop/Work/POLSMINT/Java Program/json-java1/src/main/java/com/example/Items.json");
            ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(jsonFile);

            // Create table model
            String[] columnNames = {"ID", "Name", "Boat Class", "Material", "Status", "Date", "Size", "Member", "Comments"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            // Populate table model with JSON data
            Iterator<JsonNode> iterator = arrayNode.elements();
            while (iterator.hasNext()) {
                JsonNode jsonNode = iterator.next();
                Object[] rowData = {
                    jsonNode.get("ID").asText(),
                    jsonNode.get("Name").asText(),
                    jsonNode.get("Boat Class").asText(),
                    jsonNode.get("Material").asText(),
                    jsonNode.get("Status").asDouble(),
                    jsonNode.get("Date").asText(),
                    jsonNode.get("Size").asDouble(),
                    jsonNode.get("Member").asText(),
                    jsonNode.get("Comments").asText()
                };
                tableModel.addRow(rowData);
            }

            // Create table with model
            table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            tableFrame.add(scrollPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//--------CREATION METHODS--------

    public JFrame createErrorMess(String name, String errorMessage, int width, int messWidth){
        JFrame error = createFrame(name, width, 90, false);
        error.setLayout(null);
        JLabel message = createLabel(errorMessage, 5, 5, messWidth, 30); 
        error.add(message);
        error.setVisible(true);
        return error;
    }

    public JComboBox<BoatClass> createDropDown(BoatClass[] items, int x, int y, int width, int height){
        JComboBox<BoatClass> dropDown = new JComboBox<>(items);
        dropDown.setBounds(x, y, width, height);
        return dropDown;
    }

    public JTextArea createTextArea(int rows, int cols, Color fore, Color back, int x, int y, int width, int height){
        JTextArea textArea = new JTextArea();
        textArea.setColumns(cols);
        textArea.setRows(rows);
        textArea.setBounds(x, y, width, height);
        textArea.setForeground(fore);
        textArea.setBackground(back);
        textArea.setLineWrap(true);
        return textArea;
    }

    public JFrame createFrame(String title, int width, int height, boolean close){
        JFrame framename = new JFrame();
        framename.setTitle(title);
        if(close){
        framename.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }else{
        framename.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        }
        framename.setSize(width, height);
        return framename;
    }
    public void show(){
        this.frame.setVisible(true);
    }
    
    public JButton createButton(String title, int x, int y, int width, int height){
        JButton newButton = new JButton(title);
        newButton.setBounds(x, y, width, height);
        panel.add(newButton);
        return newButton;
    }

    public JTextField createTextField(int cols, Color fore, Color back, int x, int y, int width, int height, boolean editable, int horAl){
        JTextField tf = new JTextField(cols);
        tf.setBounds(x, y, width, height);
        tf.setBackground(back);
        tf.setForeground(fore);
        tf.setFont(new Font(FONT, Font.BOLD, height - 5));
        tf.setEditable(editable);
        tf.setHorizontalAlignment(horAl);
        
        return tf;
    }
    public JLabel createLabel(String title, int x, int y, int width, int height){
        JLabel thisLabel = new JLabel(title);
        thisLabel.setBounds(x, y, width, height);
        thisLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        return thisLabel;
    }

    public final void addAll(){
        
        panel.add(in);
        panel.add(goButton);
        panel.add(newButt);

        if (viewName != null) panel.add(viewName); else System.out.println("BAZINGA");
        panel.add(viewType);
        if (viewClass != null) panel.add(viewClass); else System.out.println("BAZINGA");
        if (viewSize != null) panel.add(viewSize); else System.out.println("BAZINGA");
        if (viewDate != null) panel.add(viewDate); else System.out.println("BAZINGA");
        if (viewStatus != null) panel.add(viewStatus); else System.out.println("BAZINGA");
        if (viewMember != null) panel.add(viewMember); else System.out.println("BAZINGA");
        if (viewComments != null) panel.add(viewComments); else System.out.println("BAZINGA");
        if (viewidLabel != null) panel.add(viewidLabel); else System.out.println("BAZINGA");
        if (viewnameLabel != null) panel.add(viewnameLabel); else System.out.println("BAZINGA");
        panel.add(viewtypeLabel);
        if (viewclassLabel != null) panel.add(viewclassLabel); else System.out.println("BAZINGA");
        if (viewsizeLabel != null) panel.add(viewsizeLabel); else System.out.println("BAZINGA");
        if (viewdateLabel != null) panel.add(viewdateLabel); else System.out.println("BAZINGA");
        if (viewstatusLabel != null) panel.add(viewstatusLabel); else System.out.println("BAZINGA");
        if (viewmemberLabel != null) panel.add(viewmemberLabel); else System.out.println("BAZINGA");
        if (viewcommentsLabel != null) panel.add(viewcommentsLabel); else System.out.println("BAZINGA");
        frame.add(panel);
    }
    public void addNewItemComponents(){
        newItemPanel.add(newId);
        newItemPanel.add(newName);
        newItemPanel.add(newClass);
        newItemPanel.add(newSize);
        newItemPanel.add(newDate);
        newItemPanel.add(newStatus);
        newItemPanel.add(newMember);
        newItemPanel.add(newComments);
        newItemPanel.add(idLabel);
        newItemPanel.add(nameLabel);
        newItemPanel.add(classLabel);
        newItemPanel.add(sizeLabel);
        newItemPanel.add(dateLabel);
        newItemPanel.add(statusLabel);
        newItemPanel.add(memberLabel);
        newItemPanel.add(commentsLabel);
        newItemPanel.add(saveButton);
        newItemPanel.add(saveCloseButton);
        newItemFrame.add(newItemPanel);
    }
    

    
}
