import java.io.File;
import java.util.Scanner;

public class File2Array {
    public static String[] File2Array(String myFilename){
        final int MAX_ARRAY_SIZE = 1000;
        String[] rawArray = new String[MAX_ARRAY_SIZE];
        String[] outArray = null;
        int count = 0;

        //try opening and reading file and output trimmed array
        try {
            //open myFilename
            File colorFile = new File(myFilename);
            Scanner fileReader = new Scanner(colorFile);
            //scan over the lines add to rawArray
            while (fileReader.hasNextLine()){
                String myOutput = fileReader.nextLine();
                rawArray[count]=myOutput;
                count+=1;
            }
            System.out.println("the final line count is:"+count);
            outArray = new String[count];
            for(int i = 0; i < count; i++){
                outArray[i]=rawArray[i];
            }

            fileReader.close();
        }   catch (Exception error){ // fixes file not found error
            System.out.println("Something went wrong" + error);
        }

        return outArray;
    }
}
