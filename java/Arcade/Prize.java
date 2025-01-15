/*********************************************************************************************
 * @file : Prize.java
 * @author : Sean Mealey
 * @date :08/12/21
 **********************************************************************************************/
//Prize class for the prizes people can buy on their way out of the arcarde. Stored in a binary search tree using a doubly linked list.

import java.io.File;
//File package for the artwork aspect of prize

public class Prize {
    int price;
    String name;
    File art;
    //Variables/aspects of a prize

    //constructor
    public Prize(){
        price = 0;
        name = "";
        art = null;
    }
    //parameterized constructor
    public Prize(int price, String name, File art){
        this.price = price;
        this.name = name;
        this.art = art;
    }
    //Needed to display price
    public int getPrice(){
        return price;
    }
    //Needed for prize searches
    public String getName(){
        return name;
    }
    //Needed to open artwork upon purchase
    public File getArt(){
        return art;
    }
}
