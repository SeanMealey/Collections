/*********************************************************************************************
 * @file : Queue.java
 * @author : Sean Mealey
 * @date :08/12/21
 **********************************************************************************************/
//Used to create the deck of cards

public class Queue {
    private LinkedList list;
    //done through singly linked list

    //constructor
    public Queue() {
        list = new LinkedList();
    }
    //adds to end
    public void enqueue(Card newCard){
        SingleNode newNode = new SingleNode(newCard);
        list.append(newNode);
    }
    //removes from front
    public Card dequeue(){
        SingleNode currNode = list.getHead();
        list.removeHead();
        return currNode.getData();
    }
    //returns but doesnt remove front
    public Card peek(){
        return list.getHead().getData();
    }
}
