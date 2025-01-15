/*********************************************************************************************
 * @file : LinkedList.java
 * @author : Sean Mealey
 * @date :08/12/21
 **********************************************************************************************/
//Linked list needed for queue class

public class LinkedList {
    private SingleNode head;
    private SingleNode tail;
    //Same as always
    public LinkedList() {
        head = null;
        tail = null;
    }
    //Returns head for peak and dequeue methods
    public SingleNode getHead(){
        return head;
    }
    //adds node to end of list which is the enqueue method
    public void append(SingleNode newNode) {
        if(head==null){
            head = newNode;
            tail = newNode;
        }else{
            tail.right = newNode;
            tail = newNode;
        }
    }
    //needed for dequeue method
    public void removeHead(){
        SingleNode currNode = head;
        head = head.right;
    }
}
