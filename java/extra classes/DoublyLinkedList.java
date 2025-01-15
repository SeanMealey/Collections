/*********************************************************************************************
* @file : DoublyLinkedList.java
* @author : Sean Mealey
* @date :7/28/21
**********************************************************************************************/

import java.io.*;
import java.io.File;
import java.util.Scanner;

public class DoublyLinkedList {
    //establishes node variables for first and last element and creates size;
    protected Node head;
    protected Node tail;
    private int size;

    //Constructor
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    //Inserts node to list by adding it after the tail
    public void append(File data){
        Node endNode = new Node(data, null, null);
        endNode.prev=tail;
        if(tail!=null)
            tail.next=endNode;
        tail = endNode;
        if(head==null)
            head=endNode;
        size++;
    }
    //Prints directory, utilizes printFile() method
    public void printList(){
        Node current = head;
        if (size == 0) {
            System.out.print("Directory is empty");
        }else{
            while (current.next != null) {
                System.out.print("");
                System.out.print(current.getData().getName() + ": ");
                printFile(current.getData());
                current = current.next;
            }
            System.out.print(current.getData().getName() + ": ");
            printFile(current.getData());
        }
    }
    //Takes in the title and binary searches for it and returns the file object from the node
    //utilizes getChar() method
    public File binarySearch(String title){
        Node middle = head;
        Node current = head;
        Node left = head;
        Node right = tail;

        while (left!=right){
            for (int i = 0; i < size / 2; i++) {
                middle = head.next;
            }
            if (title.charAt(0) > getChar( current.getData() )  ) {
                left = middle.next;
            } else if (title.charAt(0) < getChar( current.getData() ) ) {
                right = middle.prev;
            } else if (title.charAt(0) == getChar( current.getData() )){
                if(title.equalsIgnoreCase(current.getData().getName() ) ){
                    return middle.getData();
                }
            }
        }
        return null;

    }
    //QuickSort algorithm
    public void sort(Node head, Node tail){
        if(head != tail && head != null && tail != null){
            Node partitionNode = partition(head, tail);

            sort( head, partitionNode.prev);
            sort( partitionNode.next, tail);
        }
    }
    //Partitions the directory by those bigger than pivot and those smaller
    public Node partition( Node left, Node right){
        Node current = left;

        Node pivot = head;
        for(int i=0;i<size/2;i++){
            pivot = head.next;
        }

        char pivotChar = getChar(pivot.getData() );
        char leftChar = getChar(head.getData() );
        char rightChar = getChar(right.getData() );

        current = left;
        int count = 0;

        Node swapA = head.prev;
        Node swapB = tail.next;

        while(left!=pivot && right != pivot){
            char currentChar = getChar(current.getData() );
            while(left!=pivot && left != null) {
                if (getChar(left.getData()) > pivotChar) {
                    swapA = left;
                }
                left = left.next;
            }
            while(right != pivot && right != null){
                if(getChar(right.getData())  < pivotChar){
                    swapB = right;
                }
                right = right.prev;
            }
            swap(swapA,swapB);
            current = current.next;
            count++;
        }

        for(int i=0;i<size/2;i++){
            current = head.next;
        }
        return current;

    }
    //Returns last Node of directory
    public Node lastNode(Node node){
        while(node.next != null ){
            node = node.next;
        }
        return node;
    }
    //Returns first node
    public Node getFirstNode(){
        return head;
    }
    //Swaps two nodes by assigning each others data to each other
    public void swap(Node a, Node b){
        if(a != null && b != null) {
            File aData = a.getData();
            File bData = b.getData();
            a.setData(bData);
            b.setData(aData);
        }
    }
    //Linear search that compares file names, returns index of node
    public int search(String fileName){
        Node current = head;
        int count = 0;
        while(current.next!=null){
            File currentFile = head.getData();
            if(fileName.equals(currentFile.getName() ) ){
                return count;
            }
            current = current.next;
            count++;
        }
        return -1;
    }
    //Removes node as specified index
    public void remove(int index){
        Node current = head;
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException ("Index out of bounds");
        }
        if(index == 0){
            Node first = head;
            Node end = tail;
            if(first.next == null){
                end = null;
            }else{
                first.next.prev = null;
            }
            first = first.next;
        } else if(index == size-1){
            Node end = tail;
            Node first = head;
            if(first.next == null){
                first = null;
            }else{
                end.prev.next = null;
            }
                end = tail.prev;
        }else {
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        size--;
    }
    //Returns node at specified index
    public File getNode(int index)
    {
        Node current = head;
        int count = 0;

        while (current != null)
        {
            if (count == index)
                return current.getData();
            count++;
            current = current.next;
        }

        assert (false);
        return null;
    }
    //Prints the contents of a file
    public void printFile(File file){
        FileInputStream fileByteStream = null;
        Scanner inFS = null;

        try {
            fileByteStream = new FileInputStream(file);
            inFS = new Scanner(fileByteStream);

            while (inFS.hasNextLine()) {
                String line = inFS.nextLine();
                System.out.println(line);
            }
            fileByteStream.close();
        } catch (Exception e ){
            System.out.println("File not found");
        }
    }
    //Returns first letter of file name as a lowercase char
    public char getChar(File file){
        String title = file.getName();
        char lowerCaseInitial = title.charAt(0);
        if(lowerCaseInitial > 90){
            lowerCaseInitial = (char)(lowerCaseInitial - 32);
        }
        return lowerCaseInitial;
    }
}
