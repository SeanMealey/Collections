/*********************************************************************************************
* @file : OverlappingCircles.java
* @description : A program that places alternating red and blue circles on the grid square the user clicks on.
* @author : Sean Mealey
* @date : 4/5/21
* @acknowledgement : none
**********************************************************************************************/

public class OverlappingCircles{


//Standard draw grid function we've used previously
    public static void drawGrid(){
        int x = 0;
        int y = 0;

        for(int i=0;i<30;i++){
            StdDraw.line(x,y,x,y+30);
            x += 10;
         }

         //resets x cordinate
         x = 0;

         //Draws all the vertical lines
         for(int i=0;i<=30;i++){
            StdDraw.line(x,y,x+30,y);
            y+=10;
         }




    }


//Main function
    public static void main(String[] args) {
        final int NUM_CLICKS = 9;
        final int CELL_SIZE = 10;
        final int GRID_SIZE = 30;
        StdDraw.setScale(-1, GRID_SIZE+1);
        StdDraw.setPenRadius(0.005);

//Calls draw grid
        drawGrid();

//Count and clicks to zero for counting purposes
        int clicks = 0;
        int count = 0;
        while(clicks < NUM_CLICKS) {
            if (StdDraw.isMousePressed()) {
                clicks++;
                // get coordinates of mouse click
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                System.out.println(x+","+y);
                // pause drawing to regain control of the mouse
                StdDraw.pause(300);
                // adjust coordinates based on the cell size
                // draw circle

                //Just using even numbers and count as an alternating switch
                if(count % 2 == 0){
                    StdDraw.setPenColor(StdDraw.RED);
                }else{
                    StdDraw.setPenColor(StdDraw.BLUE);
                }

                //Finding the correct location for the circle based off user click
                //Not the most efficient to use if statements but was able to do reasonably
                //Similar if statement repeated for the 9 different squares
                if(x < 10 && y < 10){
                    StdDraw.circle(5,5,5);
                    System.out.println("1");
                }
                if(x < 20 && x >= 10 && y < 10){
                    StdDraw.circle(15,5,5);
                    System.out.println("2");
                }
                if(x > 20 && y < 10){
                    StdDraw.circle(25,5,5);
                    System.out.println("3");
                }
                if((x < 10 && y < 20) && y >= 10 ){
                    StdDraw.circle(5,15,5);
                    System.out.println("4");
                }
                if(x < 10 && y > 20){
                    StdDraw.circle(5,25,5);
                    System.out.println("7");
                }
                if( (x < 20 && y < 20) && y >= 10 && x >= 10){
                    StdDraw.circle(15,15,5);
                    System.out.println("5");
                }
                if( (x < 20 && y > 20) && x >= 10){
                    StdDraw.circle(15,25,5);
                    System.out.println("8");
                }
                if(x > 20 && y > 10 && y <= 20){
                    StdDraw.circle(25,15,5);
                    System.out.println("6");
                }
                if(x > 20 && y > 20){
                    StdDraw.circle(25,25,5);
                    System.out.println("9");
                }
                //Increments count so the color can switch
                count++;
            }
        }
        //Print statement
        System.out.println("Done");
    }
}