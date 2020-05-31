import java.io.*;
//read a textfile using :
// FileInputStream
// InputStreamReader
// BufferedReader
public class Zoo {
    public String myString = "Welcome to the Zoo";
    private String myFilename = "animals.txt";

    //("D:\\testout.txt")
    public Zoo(){
        System.out.println(myString);
        File myFile = new File(myFilename);
        try {
            FileInputStream fis = new FileInputStream(myFile);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String fileNextline;
            while((fileNextline=br.readLine())!=null){
                System.out.println(fileNextline);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //
        String myName = myFile.getName();
        if(myName!=null){}
        System.out.println("file:" + myName);
    }
}
