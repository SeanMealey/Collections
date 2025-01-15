
import java.io.File;
import java.io.IOException;

//Complete
//Calls the parser to parse the file


public class DNAdbase {
    public static void main(String[] args) throws IOException{
        //Instantiate variables from the command line
        String myFile = args[0];

        File newFile = new File(myFile);

        int size = Integer.valueOf(args[1]);

        String outputFile = args[2];


        //Call the parser and pass in variables as parameters
        DNAparser parser = new DNAparser(newFile, size, outputFile);
        parser.parse();
        }
    }

