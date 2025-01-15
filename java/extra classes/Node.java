/*********************************************************************************************
* @file : Node.java
* @author : Sean Mealey
* @date :7/28/21
**********************************************************************************************/
//imports file package since files will be the data contained
import java.io.File;

public class Node {
    //Establishes the three variables of a node in a DoublyLinkedList
    private File data;
    protected Node next;
    protected Node prev;

    //Constructor
    public Node(File data, Node next, Node prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
    //sets link to next node
    public void setLinkNext(Node n) {
        next = n;
    }
    //sets link to previous node
    public void setLinkPrev(Node p) {
        prev = p;
    }
    //gets link to next node
    public Node getLinkNext() {
        return next;
    }
    //gets link to previous node
    public Node getLinkPrev() {
        return prev;
    }

    //Sets a specific node to the desired File object
    public void setData(File d) {
        data = d;
    }
    //Returns file object
    public File getData() {
        return data;
    }

}