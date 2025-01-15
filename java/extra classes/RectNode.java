import java.util.*;

public class RectNode implements Comparable<RectNode>{
    private String name;
    private int xCord;
    private int yCord;
    private int width;
    private int height;

    public RectNode(String rectName, int x, int y, int w, int h){
        name = rectName;
        xCord = x;
        yCord = y;
        width = w;
        height = h;
    }
    public int compareTo(RectNode otherRect){
        if(otherRect.getName().compareTo(name) > 0){
            return 1;
        }else if(otherRect.getName().compareTo(name) < 0){
            return -1;
        }else{
            return 0;
        }
    }
    public String getName(){
        return name;
    }
    public void setName(String rectName){
        name = rectName;
    }
    public int getXCord(){
        return xCord;
    }
    public void setXCord(int xCordInt){
        xCord = xCordInt;
    }
    public int getYCord(){
        return yCord;
    }
    public void setYCord(int yCordInt){
        yCord = yCordInt;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    //Checks if two rectangles intersects. Kinda cumbersome but first thing I thought of
    public void intersects(RectNode rect, RectNode otherRect){
        boolean intersection = false;
        //checks if top left corner within rect
        if(otherRect.getXCord() > rect.xCord  && otherRect.getXCord() < rect.xCord + width){
            if(otherRect.getYCord() > rect.yCord  && otherRect.getYCord() < rect.yCord + height){
                intersection = true;
            }
        }
        //checks if top right corner intersects
        if((otherRect.getXCord() + otherRect.getWidth()) > rect.xCord  && (otherRect.getXCord() + otherRect.getWidth()) < rect.xCord + width){
            if(otherRect.getYCord() > rect.yCord  && otherRect.getYCord() < rect.yCord + height){
                intersection = true;
            }
        }
        //checks if bottom left corner intersects
        if(otherRect.getXCord()  > rect.xCord  && otherRect.getXCord() < rect.xCord + width){
            if((otherRect.getYCord()+ otherRect.getHeight()) > rect.yCord  && (otherRect.getYCord()+ otherRect.getHeight()) < rect.yCord + height){
                intersection = true;
            }
        }
        //checks if bottom right corner intersects
        if((otherRect.getXCord() + otherRect.getWidth()) > rect.xCord  && (otherRect.getXCord() + otherRect.getWidth()) < rect.xCord + width){
            if((otherRect.getYCord()+ otherRect.getHeight()) > rect.yCord  && (otherRect.getYCord()+ otherRect.getHeight()) < rect.yCord + height){
                intersection = true;
            }
        }
        //checks if otherRect falls on x-coordinate
        if(otherRect.getYCord() == rect.getYCord() && otherRect.getXCord() > rect.xCord  && otherRect.getXCord() < rect.xCord + width){
            intersection = true;
        }
        //y-coordinate
        if(otherRect.getXCord() == rect.getXCord() && otherRect.getYCord() > rect.yCord  && otherRect.getYCord() < rect.yCord + height){
            intersection = true;
        }
        System.out.println(intersection);
    }
    //Takes bst as parameter and uses iterator to traverse the tree comparing each rectangle to every other rectangle by calling intersections function
    public void reportIntersections(int rectTree){

    }
    public void regionSearch(int x, int y, int w, int h){

    }
}
