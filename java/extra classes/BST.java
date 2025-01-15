
import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;
import java.util.*;


// sorted list i need compareTo() in the class E
public class BST<E extends Comparable> implements Iterable<E> {

    private Node<E> root;   // root of tree

    // default constructor
    public BST(){
        root = null;
    }
    public Node<E> getRoot(){
        return root;
    }

    //insert method
    public void insert(E item){
        Node<E> node = new Node<>(item);
        if(root == null){
            root = node;
        }else{
            Node<E> currNode = root;
            while(currNode!=null){
                if(node.getData().compareTo( currNode.getData() ) < 0 ){
                    if(currNode.leftchild == null){
                        currNode.leftchild = node;
                        currNode = null;
                    }else{
                        currNode = currNode.leftchild;
                    }
                }else{
                    if(currNode.rightchild == null){
                        currNode.rightchild = node;
                        currNode = null;
                    }else{
                        currNode = currNode.rightchild;
                    }
                }
            }
        }
    }

    //method used to search the bst
    public Node<E> search(String name){
        Node currNode = root;
        while(currNode!=null){
            if(currNode.getData().toString().compareTo(name) == 0){
                return currNode;
            } else if(currNode.getData().toString().compareTo(name) < 0){
                currNode = currNode.getLeft();
            }else{
                currNode = currNode.getRight();
            }
        }
        return null;
    }
    //Find depth method that adds up the depth of the tree using search
    public int findDepth(String name){
        int depth = 0;
        Node currNode = root;
        while(currNode!=null){
            if(currNode.getData().toString().compareTo(name) == 0){
                return depth;
            } else if(currNode.getData().toString().compareTo(name) < 0){
                currNode = currNode.getLeft();
            }else{
                currNode = currNode.getRight();
            }
            depth++;
        }
        return -1;
    }


    // print tree in first order
    public String toString(){

        Queue<Node<E>> myQueue = new LinkedList<Node<E>>();
        String Result = "";

        if (root == null) return Result;

        myQueue.add(root);
        while (!myQueue.isEmpty()){
            Node<E> current = myQueue.remove();
            Result = Result + " " + current.getData();
            if (!current.isLeftChildEmpty()){
                myQueue.add(current.getLeft());
            }
            if (!current.isRightChildEmpty()){
                myQueue.add(current.getRight());
            }
        }

        return Result;
    }




    //rewrite, works tho
    public boolean remove(Node<E> nodeToRemove){
        if(nodeToRemove==null){
            return false;
        }
        String name = nodeToRemove.getData().toString();
        String nodeToRemoveString = "";
        if(nodeToRemove!=null){
            nodeToRemoveString = nodeToRemove.getData().toString();
        }
        Node<E> parent = root;
        Node<E> currNode = root;

        boolean hasLeft = false;
        String currString = currNode.getData().toString();
        while(currString.compareTo(name) != 0) {
            parent = currNode;
            if (currNode == null) {
                return false;
            }
            if (nodeToRemoveString.compareTo(currString) > 0) {
                currNode = currNode.getLeft();
                hasLeft = true;
            } else if(nodeToRemoveString.compareTo(currString) < 0){
                hasLeft = false;
                currNode = currNode.getRight();
            }
            if(currNode!=null){
                currString = currNode.toString();
            }
        }

        //Deletes a leaf node
        if(currNode!=null && currNode.getLeft() == null && currNode.getRight() == null){
            // if root node is to be deleted
            if(currNode == root){
                root = null;
            }
            else if(hasLeft){
                parent.leftchild = null;
            }
            else{
                parent.rightchild = null;
            }
        }else if(currNode!= null && currNode.getLeft() == null){
            //Node has just right child
            if(currNode == root){
                root = currNode.getRight();
            }
            else if(hasLeft){
                parent.leftchild = currNode.getRight();
            }
            else{
                parent.rightchild = currNode.getRight();
            }
        }else if(currNode!=null && currNode.getRight() == null) {
            //Just right child
            if (currNode == root) {
                root = currNode.getLeft();
            } else if (hasLeft) {
                parent.leftchild = currNode.getLeft();
            } else {
                parent.rightchild = currNode.getLeft();
            }
        }else{
            //Node has two children so gotta find the successor node
            Node sucNode = currNode;
            Node sucNodeParent = currNode;
            Node current = new Node();
            if(currNode!=null){
                current = currNode.getRight();
            }
            while(current != null){
                sucNodeParent = sucNode;
                sucNode = current;
                current = current.getLeft();
            }
            if(currNode!=null && sucNode != currNode.getRight()){
                sucNodeParent.leftchild = sucNode.getRight();
                sucNode.rightchild = currNode.getRight();
            }

            if(currNode == root){
                root = sucNode;
            }
            else if(hasLeft){
                parent.leftchild = sucNode;
            }
            else{
                parent.rightchild = sucNode;
            }
            if(sucNode!=null && currNode!=null){
                sucNode.leftchild = currNode.getLeft();
            }
        }
        return true;
    }

    //BST iterator implementation
    @Override
    public Iterator<E> iterator() {
        return new bstIterator();
    }

    private class bstIterator implements Iterator<E>{
        public Stack<Node<E>> treeStack;
        private Node<E> curr =  root;
        public bstIterator() {
            builtStack(curr);
        }
        public void builtStack(Node<E> curr){
            treeStack = new Stack<Node<E>>();
            if (curr != null) {
                builtStack(curr.getLeft());
                if(curr!=null){
                    treeStack.push(curr);
                }
                builtStack(curr.getRight());
            }

        }

        public boolean hasNext(){
            return (!treeStack.empty()||curr!=null);
        }

        public E next(){
            if(!hasNext()) {
                return null;
            }
            while(!treeStack.empty() || curr!= null) {
                if(curr!=null) {
                    treeStack.push(curr);
                    curr = curr.getLeft();
                } else {
                    Node<E> top = treeStack.pop();
                    curr = top.getRight();
                    return top.getData();
                }
            }
            return null;
        }
    }

}

