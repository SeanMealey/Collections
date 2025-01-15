
/**
 * Make a program that will accept an positive integer and return the factorial of that number.
 * Make a Factorial method recursively (receive one int parameter, return the factorial). 
 * Create a client to demonstrate it works correctly this client will demonstrate all of the recursion projects.
Ex.
Enter a positive number(-1 to end): 5
5! is 120
Enter a positive number(-1 to end): 3
3! is 6
Enter a positive number(-1 to end): -1
Thank you have a nice day:)
 * 
 * @SeanMealey 
 */
public class factorial
{
    private int num;

    public factorial(int num)
    {
        this.num = num;
    }

    public int findFact(int num){
        int count = num;
        if(count!=-1){
            if(count == 1){
                return num;
            }else{
                return num*findFact(num-1);
            }
        }else{
            return -1;
        }
    }
}
