
/**
 * SORT
 * 
 * @SeanMealey 
 */
import java.util.ArrayList;
public class sorting
{
    public static void main(String args[]){
        ArrayList<Integer> x = new ArrayList<Integer>();
        for(int i=0;i<50;i++){
            int temp = (int)(Math.random()*50)+1;
            x.add(temp);
        }
        System.out.println("Original Array");
        for(int i=0;i<x.size();i++){
            System.out.print(x.get(i)+" ");
        }
        System.out.println("");
        System.out.println("");

        System.out.println("Bubble Sort");
        ArrayList<Integer> bubble = bubbleSort(x);
        for(int i=0;i<bubble.size();i++){
            System.out.print(bubble.get(i)+" ");
        }
        System.out.println("");
        System.out.println("");

        int[] y = new int[50];
        for(int i=0;i<50;i++){
            int temp = (int)(Math.random()*50)+1;
            y[i] = temp;
        }
        System.out.println("Original Array");
        for(int d=0;d<y.length;d++){
            System.out.print(y[d]+" ");
        }
        System.out.println("");
        System.out.println("");

        System.out.println("Insertion Sort");
        int[] insertion = insertionSort(y);
        for(int t=0;t<insertion.length;t++){
            System.out.print(insertion[t]+" ");
        }
        System.out.println("");
        System.out.println("");

        int[] z = new int[50];
        for(int i=0;i<50;i++){
            int temp = (int)(Math.random()*50)+1;
            z[i] = temp;
        }

        System.out.println("Original Array");
        for(int c=0;c<z.length;c++){
            System.out.print(z[c]+" ");
        }
        System.out.println("");
        System.out.println("");

        System.out.println("Selection Sort");
        int[] selection = selectionSort(z);
        for(int c2=0;c2<selection.length;c2++){
            System.out.print(selection[c2]+" ");
        }
        System.out.println("");
        System.out.println("");

        int[] h = new int[50];
        for(int i=0;i<50;i++){
            int temp = (int)(Math.random()*50)+1;
            h[i] = temp;
        }

        System.out.println("Original Array");
        for(int v=0;v<h.length;v++){
            System.out.print(h[v]+" ");
        }
        System.out.println("");
        System.out.println("");

        System.out.println("Merge Sort");
        sort(h,0,h.length-1);
        for(int u=0;u<h.length;u++){
            System.out.print(h[u]+" ");
        }

        int[] b = new int[50];
        for(int i=0;i<50;i++){
            int temp = (int)(Math.random()*50)+1;
            b[i] = temp;
        }
        System.out.println("");
        System.out.println("");
        System.out.println("Original Array");
        for(int g = 0; g<b.length;g++){
            System.out.print(b[g]+" ");
        }
        System.out.println("");
        System.out.println("");

        System.out.println("Quick Sort");
        b = quickSort(b,0,b.length-1);
        for(int f = 0; f<b.length;f++){
            System.out.print(b[f]+" ");
        }

    }

    public static void printArray(ArrayList<Integer> x){
        for(int i=0;i<x.size();i++){
            System.out.print(x.get(i)+" ");
        }
    }

    public static ArrayList bubbleSort(ArrayList<Integer> x){
        for(int i=0;i<x.size()-1;i++){
            for(int k=0;k<x.size()-i-1;k++){
                if(x.get(k)>x.get(k+1)){
                    int small = x.get(k+1);
                    int big = x.get(k);
                    x.set(k,small);
                    x.set(k+1,big);
                }
            }
        }
        return x;
    }

    public static int[] insertionSort(int[] x){
        for (int i = 1; i < x.length; ++i) { 
            int num = x[i]; 
            int r = i - 1; 
            while (r >= 0 && x[r] > num) { 
                x[r + 1] = x[r]; 
                r = r - 1; 
            } 
            x[r + 1] = num;
            for(int t=0;t<insertion.length;t++){
            System.out.print(insertion[t]+" ");
            }
        } 
        return x;
    }

    public static int[] selectionSort(int[] x){
        int l = x.length;
        for(int i=0;i<l-1;i++){
            int minIndex = i;
            for(int k=i+1;k<l;k++){
                if (x[k] < x[minIndex]){
                    minIndex = k; 
                }
            }
            int temp = x[minIndex]; 
            x[minIndex] = x[i]; 
            x[i] = temp; 
        }
        return x;
    }

    public static void merge(int x[], int low, int mid, int high) { 
        int num1 = mid - low + 1; 
        int num2 = high - mid; 
        int temp1[] = new int [num1]; 
        int temp2[] = new int [num2];
        for (int y=0; y<num2; ++y){
            temp2[y] = x[mid+1+y]; 
        }
        for (int i=0; i<num1; ++i){
            temp1[i] = x[low+i]; 
        }
        int i = 0;
        int y = 0; 
        int z = low; 
        while (i < num1 && y < num2){ 
            if (temp1[i] <= temp2[y]) 
            { 
                x[z] = temp1[i]; 
                i++; 
            } 
            else
            { 
                x[z] = temp2[y]; 
                y++; 
            } 
            z++; 
        } 
        while (i < num1){ 
            x[z] = temp1[i]; 
            i++; 
            z++; 
        } 
        while (y < num2){ 
            x[z] = temp2[y]; 
            y++; 
            z++; 
        } 
    } 

    public static void sort(int x[], int low, int high){ 
        if (low < high){ 
            int mid = (low+high)/2; 
            sort(x, low, mid); 
            sort(x , mid+1, high); 
            merge(x, low, mid, high); 
        } 
    } 

    public static int[] quickSort(int[] array, int bottom,int top){      
        if (bottom < top){
            int temp = 0;
            int mid = array[top];  
            int i = (bottom - 1);
            int c=0;
            if (bottom < top){
                c = partition(array,bottom,top);
                quickSort(array, bottom, c - 1); 
                quickSort(array, c + 1, top); 
            }
        }
        return array;
    }

    public static int partition(int array[], int bottom,int top){
        int pivot = array[top];  
        int i = (bottom - 1)  ;
        int temp = 0;
        for (int j = bottom; j <= top- 1; j++){
            if (array[j] < pivot){
                i++;  
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        temp = array[i+1];
        array[i+1] = array[top];
        array[top] = temp;
        return (i + 1);
    }

    // public static void quickSort(ArrayList<Integer> x, int begin, int end) {
    // if (begin < end) {
    // int partitionIndex = partition(x, begin, end);

    // quickSort(x, begin, partitionIndex-1);
    // quickSort(x, partitionIndex+1, end);
    // }
    // }

    // public static int partition(ArrayList<Integer> x, int begin, int end) {
    // int pivot = x.get(end);
    // int i = (begin-1);

    // for (int j = begin; j < end-1; j++) {
    // if (x.get(j) <= pivot) {
    // i++;

    // int swapTemp = x.get(i);
    // x.set(i,j);
    // x.set(j,swapTemp);
    // }
    // }

    // int swapTemp = x.get(i+1);
    // x.set(i+1,x.get(end));
    // x.set(x.get(end),swapTemp);

    // return i+1;
    // }

}
