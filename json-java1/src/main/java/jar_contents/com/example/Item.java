package com.example;

public class Item{
    
    //Item properties
    private int id; //ID
    private String name; //Name
    private ItemType material; // This can be Oil, Petroleum(Gas), or Lubrication
    private double status; //Fill status %
    private String date; // Date 05 Feb 25
    private double size; // Size in litres, 0.5
    private String member; // Member who modified the item
    private String comments; // Comments on the item
    private BoatClass boatClass; // Boat class

    

    public void setName(String name){
        this.name = name;
    }

    public void setStatus(double status){
        this.status = status;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setSize(double size){
        this.size = size;
    }

    public void setMember(String member){
        this.member = member;
    }
    
    public void setComments(String comments){
        this.comments = comments;
    }
    
    public void setBoatClass(BoatClass boatClass){
        this.boatClass = boatClass;
    }

    private ItemType setMaterial(){
        
        if(id < 1000 || id >= 4000 ){
            this.material = ItemType.OTHER;
        }
        else if (id >= 1000 && id < 2000){
            this.material = ItemType.PETROLEUM;
        }else if (id >= 2000 && id < 3000){
            this.material = ItemType.OIL;
        }else if(id >= 3000 && id< 4000){
            this.material = ItemType.LUBRICATION;
        }
        return material;
    }


    public void setId(int id){
        this.id = id;
        this.material = setMaterial();
    }

    public int getID(){
        return id;
    }

    public ItemType getMaterial(){
        return material;
    }

    public String getName(){
        return name;
    }
    
    public double getStatus(){
        return status;
    }

    public String getDate(){
        return date;
    }

    public double getSize(){
        return size;
    }
    
    public String getMember(){
        return member;
    }

    public String getComments(){
        return comments;
    }

    public BoatClass getBoatClass(){
        return boatClass;
    }

    

    //Constructor for item

    public Item(int id, String name, BoatClass boatClass, double size, double status, String date, String member,String comments){
        this.id = id;
        this.name = name;
        this.boatClass = boatClass;
        this.size = size;
        this.status = status;
        this.date = date;
        this.member = member;
        this.comments = comments;
        this.material = setMaterial();
    }

    //return String rep of item
    public String getItem(){
        return id + " " + name + " BOAT CLASS: " + boatClass + " Substance: " + material + " ID: " + id + " SIZE: " + size + " STATUS: " + status + " modified on: " + date + " BY " + member;
    }

    

}