import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ColorWheel {

    private String[] rawColorArray = null;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public int colorCount = 0;
    public String colorwheelFile = "color957.txt";

    public ColorWheel(){
        rawColorArray = File2Array.File2Array(colorwheelFile);
        colorCount = rawColorArray.length;
    }

    public static void main(String args[]){
        ColorWheel Spoke957 = new ColorWheel();
        String[] myWheel = Spoke957.rawColorArray;
        for(String color : myWheel){System.out.println(color);}// +" "+StrSearch.SpaceSlice(color));}
        System.out.println(ANSI_RED + "This text is red!" + ANSI_RESET);
    }
}
