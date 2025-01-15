/*********************************************************************************************
 * @file : Deck.java
 * @author : Sean Mealey
 * @date :08/12/21
 **********************************************************************************************/
//Is a queue of cards, necesary for 21 card game

import java.util.Collections;
import java.util.Random;

public class Deck {
    public Queue cardQueue = new Queue();
    //instantiante queue

    //Constructor creates new deck by iterating through all possible cards
    public Deck(){

        for(int i=0;i<13;i++){
            Card newCard = new Card(i+2,"Spades ");
            cardQueue.enqueue(newCard);
        }
        for(int i=0;i<13;i++){
            Card newCard = new Card(i+2,"Hearts ");
            cardQueue.enqueue(newCard);
        }
        for(int i=0;i<13;i++){
            Card newCard = new Card(i+2,"Clubs ");
            cardQueue.enqueue(newCard);
        }
        for(int i=0;i<13;i++){
            Card newCard = new Card(i+2,"Diamonds ");
            cardQueue.enqueue(newCard);
        }
    }
    //Shuffles deck by removing elements in an array, shuffling, then adding back to queue
    public void shuffle(){
        Random rand = new Random();
        Card[] tempDeck = new Card[52];
        for(int i=0;i<52;i++){
            Card topCard = cardQueue.dequeue();
            tempDeck[i] = topCard;
        }
        for(int i=0;i<52;i++){
            int rand1 = rand.nextInt(52);
            int rand2 = rand.nextInt(52);
            Card temp = tempDeck[rand1];
            tempDeck[rand1] = tempDeck[rand2];
            tempDeck[rand2] = temp;
        }
        for(int i=0;i<52;i++){
            cardQueue.enqueue(tempDeck[i]);
        }
    }
    //mimicks act of dealing a card
    public Card topCard(){
        return cardQueue.dequeue();
    }
}
