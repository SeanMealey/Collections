/*********************************************************************************************
 * @file : BinarySearchTree.java
 * @author : Sean Mealey
 * @date :08/12/21
 **********************************************************************************************/

import java.io.File;
import java.awt.Desktop;
import java.io.*;

public class BinarySearchTree {
    private Node root;
    //constructor
    public BinarySearchTree(){
        root = null;
    }
    public BinarySearchTree(Node root){
        this.root = root;
    }
    //binary search tree search algorithm finds by string name alphabetically
    public Node search(String name){
        Node currNode = root;
        while(currNode!=null){
            if(currNode.key.getName().equalsIgnoreCase(name) ){
                return currNode;
            } else if(name.compareTo(currNode.key.getName() ) < 0){
                currNode = currNode.prev;
            }else{
                currNode = currNode.next;
            }
        }
        return null;
    }
    public void insert(Node node){
        if(root == null){
            root = node;
        }else{
            Node currNode = root;
            while(currNode!=null){
                if(node.key.getName().compareTo( currNode.key.getName() ) < 0 ){
                    if(currNode.prev == null){
                        currNode.prev = node;
                        currNode = null;
                    }else{
                        currNode = currNode.prev;
                    }
                }else{
                    if(currNode.next == null){
                        currNode.next = node;
                        currNode = null;
                    }else{
                        currNode = currNode.next;
                    }
                }
            }
        }
    }
    //prints all available prizes
    public void printCatalogue (){
        Node currNode = root;
        while(currNode!=null) {
            System.out.println(currNode.key.getName() + ": $" + currNode.key.getPrice() );
            currNode = currNode.next;
        }
    }
    //opens files for prize
    public void buyPrize (String name){
        Node currNode = search(name);;
        File art = currNode.key.getArt();
        try {
            Desktop desktop = Desktop.getDesktop();
            if (art.exists()) {
                desktop.open(art);
            }
        } catch(Exception e){
            System.out.println("Cannot display file");
        }
    }
    //returns false when node cant be removed/found
    public boolean remove(String name){
        Node parent = root;
        Node currNode = root;

        boolean hasLeft = false;

        while( !(currNode.key.getName().equals(name))) {
            parent = currNode;
            if (currNode == null) {
                return false;
            }
            if (name.compareTo(currNode.key.getName()) < 0) {
                currNode = currNode.prev;
                hasLeft = true;
            } else {
                hasLeft = false;
                currNode = currNode.next;
            }
        }
        //Deletes a leaf node
        if(currNode.prev == null && currNode.next == null){
            System.out.println("Leaf node deletion case");
            // if root node is to be deleted
            if(currNode == root){
                root = null;
            }
            else if(hasLeft){
                parent.prev = null;
            }
            else{
                parent.next = null;
            }
        }else if(currNode.prev == null){
            //Node has just right child
            System.out.println("One right child deletion case");
            if(currNode == root){
                root = currNode.next;
            }
            else if(hasLeft){
                parent.prev = currNode.next;
            }
            else{
                parent.next = currNode.next;
            }
        }else if(currNode.next == null) {
            //Just right child
            System.out.println("One left child deletion case");
            if (currNode == root) {
                root = currNode.prev;
            } else if (hasLeft) {
                parent.prev = currNode.prev;
            } else {
                parent.next = currNode.prev;
            }
        }else{
            //Node has two children so gotta find the successor node
            Node sucNode = currNode;
            Node sucNodeParent = currNode;
            Node current = currNode.next;
            while(current != null){
                sucNodeParent = sucNode;
                sucNode = current;
                current = current.prev;
            }
            if(sucNode != currNode.next){
                sucNodeParent.prev = sucNode.next;
                sucNode.next = currNode.next;
            }

            if(currNode == root){
                root = sucNode;
            }
            else if(hasLeft){
                parent.prev = sucNode;
            }
            else{
                parent.next = sucNode;
            }
            sucNode.prev = currNode.prev;
        }
        return true;
        }
    }

