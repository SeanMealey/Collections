/*********************************************************************************************
 * @file : Card.java
 * @author : Sean Mealey
 * @date :08/12/21
 **********************************************************************************************/
//Card class needed to create Deck for 21 card game

public class Card {
    private int value;
    private String suit;
    private int idNum;
    private int suitVal;
    //Basic aspects of a card as well as a numerical value for suit and id number

    //constructor
    public Card(){
        value = 0;
        suit = "";
        idNum = 0;
        suitVal = 0;
    }
    //parameterized constructor
    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
        if(suit.equals("Spades ")){
            suitVal = 0;
        }
        if(suit.equals("Hearts ")){
            suitVal = 1;
        }
        if(suit.equals("Clubs ")){
            suitVal = 2;
        }
        if(suit.equals("Diamonds ")){
            suitVal = 3;
        }
        idNum = (10 * suitVal) + value;
    }
    //prints card with correct syntax
    public void printCard(){
        if(value > 10){
            if(value == 11){
                System.out.print("Jack of " + suit);
            }
            if(value == 12){
                System.out.print("Queen of " + suit);
            }
            if(value == 13){
                System.out.print("King of " + suit);
            }
            if(value == 14){
                System.out.print("Ace of " + suit);
            }
        }else{
            System.out.print(value + " of " + suit);
        }
    }
    //returns the value of cards in 21 which is different cause face cards always equal 10 in 21
    public int get21Value(){
        if(value<=10){
            return value;
        }else if(value<14){
            return 10;
        }else{
            return 11;
        }
    }
}
