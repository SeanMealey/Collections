/*********************************************************************************************
* @file : SumRandomIntegers.java
* @description : A program that creates a 100 random numbers (1,1000] and prints out the sum, max, and average.
* @author : Sean Mealey
* @date : 3/18/21
* @acknowledgement : none
**********************************************************************************************/

public class SumRandomIntegers {
    public static void main(String[] args){
        int sum = 0;
        int average = 0;
        int max = 0;
        //Defining variables outside of for loop for correct scoop

        for(int i=0;i<100;i++){
            int newNumb = (int)(Math.random()*1000);
            sum += newNumb;
            if(newNumb>max){
                max = newNumb;
            }
        }
        //loops through all the numbers adding each for sum, and checking if the number is larger to find the max
        //then just dividing the sum by 100 to find average

        average = sum / 100;

        System.out.println("The sum of the random 100 integers was " + sum);
        System.out.println("The average of the random 100 integers was " + average);
        System.out.println("The largest of the random 100 integers was " + max);
        //prints the outputs

    }
}
