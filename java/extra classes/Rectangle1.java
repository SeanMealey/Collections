
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;



//Main function
public class Rectangle1 {
    public static void main(String[] args) throws FileNotFoundException {
        //Command line file input
        String myFileName = args[0];
        File myFile = new File(myFileName);
        FileInputStream stream = new FileInputStream(myFile);
        Scanner scanner = new Scanner(stream);

        //All valid rectangles will be contained by
        final Rectangle WORLD_BOX = new Rectangle(0, 0, 1024, 1024);

        //BST
        BST<RectNode> bst = new BST<RectNode>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim().replaceAll("\\s+", " ");
            if (!line.equals("")) {
                String[] parsedLines = line.split(" ");
                //Insert option
                if (parsedLines[0].equals("insert")) {
                    if (parsedLines.length != 6) {
                        System.out.println("Invalid Insert Parameter Length: " + parsedLines.length);
                    }else {
                        String name = parsedLines[1];
                        int x = Integer.parseInt(parsedLines[2]);
                        int y = Integer.parseInt(parsedLines[3]);
                        int w = Integer.parseInt(parsedLines[4]);
                        int h = Integer.parseInt(parsedLines[5]);
                        //insert into world-class (name, x, y, w, h)
                        RectNode r1 = new RectNode(name, x, y, w, h);
                        if (WORLD_BOX.contains(r1.getRectangle())) {
                            bst.insert(r1);
                            System.out.print("Rectangle accepted: ");
                            r1.printInfo();
                        } else {
                            System.out.print("Rectangle rejected: ");
                            r1.printInfo();
                        }
                    }
                } else if (parsedLines[0].equals("remove")) {
                    if (parsedLines.length != 5 && parsedLines.length != 2) {
                        System.out.println("Invalid Remove Parameters Length: " + parsedLines.length );
                    } else if(parsedLines.length == 5){
                        int x = Integer.parseInt(parsedLines[1]);
                        int y = Integer.parseInt(parsedLines[2]);
                        int w = Integer.parseInt(parsedLines[3]);
                        int h = Integer.parseInt(parsedLines[4]);

                        RectNode rectNodeToBeRemoved = search(bst, x, y, w, h);
                        if(rectNodeToBeRemoved!=null){
                            String rectName = rectNodeToBeRemoved.getName();
                            Node<RectNode> nodeToRemove = bst.search(rectName);
                            boolean removed = bst.remove(nodeToRemove);
                        }else{
                            System.out.print("Rectangle rejected: " );
                            System.out.println("("+x+","+y+","+w+","+h+")");
                        }
                    } else if (parsedLines.length == 2) {
                        String name = parsedLines[1];
                        Node<RectNode> nodeToRemove = bst.search(name);
                        boolean removed = bst.remove(nodeToRemove);
                        if(removed==false){
                            System.out.println("Rectangle rejected: "+ name);
                        }
                    }
                } else if (parsedLines[0].equals("regionsearch")) {
                    if(parsedLines.length != 5) {
                        System.out.println("Invalid Region Search Parameters Length: " + parsedLines.length);
                    }else {
                        int x = Integer.parseInt(parsedLines[1]);
                        int y = Integer.parseInt(parsedLines[2]);
                        int w = Integer.parseInt(parsedLines[3]);
                        int h = Integer.parseInt(parsedLines[4]);
                        //use region search method
                        //regionSearch(bst,x,y,w,h);
                        if(w>0 && h>0){
                            regionSearch(bst, x, y, w, h);
                        }else{
                            System.out.println("Rectangle rejected: ("+x+", "+y+", "+w+", "+h+")");
                        }
                    }
                } else if (parsedLines[0].equals("intersections")) {
                    intersections(bst);
                } else if (parsedLines[0].equals("search")) {
                    if (parsedLines.length != 2) {
                        System.out.println("Invalid Search Parameters Length " + parsedLines.length );
                    } else {
                        String name = parsedLines[1];
                        //use search method
                        ArrayList<RectNode> foundRects = searchInstances(bst,name);
                        if(foundRects.size()>0){
                            for(int i=0;i<foundRects.size();i++){
                                System.out.print("Rectangle found: ");
                                foundRects.get(i).printInfo();
                            }
                        }else{
                            System.out.println("Rectangle not found: " + name);
                        }
                    }
                } else if (parsedLines[0].equals("dump")) {
                    dump(bst);
                } else {
                    System.out.println("Invalid Command. Please Check Again");
                }
            }
        }
    }
    //Creates a new rectangle and checks the intersections with the BST rectangles
    public static void regionSearch(BST<RectNode> myTree, int x, int y, int w, int h){
        Rectangle region = new Rectangle(x,y,w,h);
        Iterator<RectNode> bstIterator = myTree.iterator();
        System.out.println("Rectangles intersecting region ("+x+", "+y+", "+w+", "+h+")");
        for(RectNode root: myTree){
            RectNode data = bstIterator.next();
            if( region.contains(data.getRectangle())){
                String name = data.getName();
                double xCord = data.getRectangle().getX();
                double yCord = data.getRectangle().getY();
                System.out.print(name + ": ");
                System.out.println(xCord + "," + yCord);
            }
        }
    }
    //Reports all intersections on the rectangles in the bst
    //Uses iterator and a nested for each loop to do so
    public static void intersections(BST<RectNode> myTree){
        //Standard output
        System.out.println("Intersection pairs:");
        ArrayList<Rectangle> combos = new ArrayList<>();
        //Nested for loop
        for(Iterator<RectNode> bstIterator = myTree.iterator(); bstIterator.hasNext();){
            RectNode outerRectNode = bstIterator.next();
            Rectangle outerRect = outerRectNode.getRectangle();
            for(Iterator<RectNode> innerIterator = myTree.iterator(); innerIterator.hasNext();){
                RectNode innerRectNode = innerIterator.next();
                Rectangle innerRect = innerRectNode.getRectangle();
                if(outerRect.intersects(innerRect) && outerRectNode.compareTo(innerRectNode)!=0){
                    Rectangle intersection = outerRect.intersection(innerRect);
                    boolean alreadyAddressed = false;
                    //Checks if combo has already been accounted for
                    for(int i=0;i<combos.size();i++){
                        if( combos.get(i).equals(intersection) ){
                            alreadyAddressed = true;
                        }
                    }
                    combos.add(intersection);
                    if(alreadyAddressed==false) {
                        outerRectNode.printInfo();
                        System.out.print(" : ");
                        innerRectNode.printInfo();
                    }
                }
            }
        }
    }
    //Prints out all the bst's information using an iterator
    public static void dump(BST<RectNode> bst){
        System.out.println("BST dump:");
        Iterator<RectNode> bstIterator = bst.iterator();
        int count = 0;
        for(RectNode root: bst) {
            RectNode nextNode = bstIterator.next();
            if(nextNode!=null){
                int depth = bst.findDepth(nextNode.getName());
                System.out.print("Node has depth " + depth + ", Value ");
                nextNode.printInfo();
                count++;
            }else{
                System.out.println("Node has depth 0, Value (null)");
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("BST size is: " + count);


    }
    //Search method for when the dimensions are the parameters
    public static RectNode search(BST<RectNode> bst,int x,int y,int w,int h){
        RectNode foundNode = null;
        for(Iterator<RectNode> bstIterator = bst.iterator(); bstIterator.hasNext();){
            RectNode outerRectNode = bstIterator.next();
            Rectangle outerRect = outerRectNode.getRectangle();
            double xCord = outerRect.getX();
            double yCord = outerRect.getY();
            double width = outerRect.getWidth();
            double height = outerRect.getHeight();
            if(xCord==x && yCord == y && width == w && height == h){
                foundNode = outerRectNode;
            }
        }
        return foundNode;
    }
    //Search method that returns all instances of RectNodes with the searched for name
    public static ArrayList<RectNode> searchInstances(BST<RectNode> bst,String name){
        ArrayList<RectNode> foundRects = new ArrayList<>();
        RectNode foundNode = null;
        for(Iterator<RectNode> bstIterator = bst.iterator(); bstIterator.hasNext();){
            RectNode outerRectNode = bstIterator.next();
            if(outerRectNode.getName().equals(name)){
                foundNode = outerRectNode;
                foundRects.add(foundNode);
            }
        }
        return foundRects;
    }
}
