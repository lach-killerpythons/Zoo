import java.io.FileWriter;
import java.io.IOException;

public class CreateOutputFile {
    public static void CreateOutputFile(String fileName, String[] inputArray) throws IOException {
        FileWriter myFWText = new FileWriter(fileName);

        for (int i = 0; i < inputArray.length; i++) {
            myFWText.write(inputArray[i]+"\r\n");
        }

        myFWText.close();
    }

}
