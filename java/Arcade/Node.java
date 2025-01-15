/*********************************************************************************************
 * @file : Node.java
 * @author : Sean Mealey
 * @date :08/12/21
 **********************************************************************************************/
//Node class for doubly linked list for the prizes

public class Node {
    public Prize key;
    public Node prev;
    public Node next;

    //constructor
    public Node(Prize prize) {
        key = prize;
        prev = null;
        next = null;
    }
}