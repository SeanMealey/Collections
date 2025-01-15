/*********************************************************************************************
 * @file : SingleNode.java
 * @author : Sean Mealey
 * @date :08/12/21
 **********************************************************************************************/
//SingleNode is a node of a singly linked list which I needed for the queue for the 21 card game

public class SingleNode {
    public Card data;
    public SingleNode right;
    //standard business
    public SingleNode(Card initialData) {
        data = initialData;
        right = null;
    }
    //Had to change name because already had a doublylinkedlist
    public SingleNode() {
        data = null;
        right = null;
    }
    //returns card data
    public Card getData(){
        return data;
    }
}
