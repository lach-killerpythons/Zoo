import java.io.FileNotFoundException;
//import java.util.Arrays;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.util.stream.Stream;

public class Zoo2 {
    private int wordCount = 0;
    private boolean slicedWord_outputFile = true;
    private String defaultFilename_slicedOutput = "sliced_output.txt";
    private String defaultFilename_slicedOutput2 = "sliced_output2.txt";
    private File myFile = new File("animals.txt");

    private String[] animalCages;
    public String[] regularCages;
    public String[] exoticCages;

    public String[] cleanWordList;

    private String[] slicedWordList; // large empty list for exotic animal sliced words
    private int[] exoticWordNumber;
    public void printAnimals(){
        for(int a = 0; a < animalCages.length; a++){
            System.out.println(animalCages[a]);
        }
    }


    public int countAnimals(){

        wordCount = 0;
        try {
            Scanner fileReader = new Scanner(myFile);
            while (fileReader.hasNextLine()){
                String myOutput = fileReader.nextLine();
                wordCount+=1;
            }
            System.out.println("the final count is:"+wordCount);
            fileReader.close();
        }   catch (FileNotFoundException error){ // fixes file not found error
            System.out.println("Something went wrong" + error);
        }
        return wordCount;
    }

    public void viewCage(int cageNumber){
        if (animalCages[0]==null){System.out.println("the array of cages are empty!");}
        else {System.out.println("Cage number "+cageNumber+" contains the "+animalCages[cageNumber]);}
    }

    // fill file in public "animalCages"
    public void cageAnimals(){
        int animalNumber = countAnimals();
        String[] myCages = new String[animalNumber];
        try {
            Scanner fileReader = new Scanner(myFile);
            int myCounter = 0;
            while (fileReader.hasNextLine()){
                String myOutput = fileReader.nextLine();
                myCages[myCounter] = myOutput;
                myCounter+=1;
            }

            fileReader.close();
        }   catch (FileNotFoundException error){ // fixes file not found error
            System.out.println("Something went wrong" + error);
        }

        animalCages = myCages;
    }

    private static int[] indexOf(int[] iArray, int element){
        int myLength = iArray.length;
        int myArrayCount = 0;
        int[] myArray = new int[myLength]; // make large empty array
        for(int l = 0; l < myLength; l++){
            int i = iArray[l];
            if(i==element){myArray[myArrayCount]=l; myArrayCount+=1;} // only add to myArray if matches element
        }
        int[] returnmyArray = new int[myArrayCount]; // make correct sized array
        for(int i = 0; i < myArrayCount;i++){
            returnmyArray[i]=myArray[i]; // fill the return array with the contents of myArray
        }
        return returnmyArray; // return array of all elements
    }

    public String RandomExoticAnimal(int wordNumber){ // max 4
        String returnString = "";
        if(wordNumber<5){
            int[] returnArray = indexOf(exoticWordNumber, wordNumber);
            int arrayLength = returnArray.length;
            Random random = new Random();
            int randomInteger = random.nextInt(arrayLength);
            returnString = exoticCages[returnArray[randomInteger]];
        } else {returnString = "Invalid"; System.out.println("maximum 4 words");}
        return returnString;


    }

    public String RandomRegularAnimal(){ // max 4
        String returnString = "";
        int arrayLength = regularCages.length;
        Random random = new Random();
        int randomInteger = random.nextInt(arrayLength);
        returnString = regularCages[randomInteger];
        return returnString;
    }

    private void printExoticWithWords(int element){
        int[] returnArray = indexOf(exoticWordNumber, element);
        int arrayLength = returnArray.length;
        for(int i = 0; i < arrayLength; i ++){
            int returnIndex = returnArray[i];
            String animalName = exoticCages[returnIndex];
            // test 2 -- make sure all the animals are added
            //System.out.println(animalName);
        }
    }


    public void separateCages(){ // separate Strings into single and doubles/tuples
        String[] fillRegularCages = new String[wordCount]; // oversized empty array to fill with single name animals
        String[] fillExoticCages = new String[wordCount]; // oversized empty array to fill with double and tuple name animals
        String[] fillSlicedWordList = new String[2000];
        int[] fillExoticWordNumber = new int[wordCount];
        int highestWordCount = 0;
        int wordNumberHolder=0;
        int numberRegular =0; // number of animals with 1 word names
        int numberExotic =0; // number of animals with more than 1 word names
        int slicedWords =0;
        for(int i = 0; i < wordCount; i ++){
            String nextAnimal = animalCages[i];
            int strLength = nextAnimal.length();
            String[] charArray = new String[strLength];
            int[] spaceIndex = new int[4];
            int numberSpaces = 0;
            String nextFragment = ""; // reset nextFragment for new line

            if(strLength > 1){
                for(int c = 0; c < strLength; c++){
                    try{
                        String CC = nextAnimal.substring(c, c+1);
                        if(CC.equals(" ")){
                            fillSlicedWordList[slicedWords]=nextFragment;
                            //tester 1 - why were regular animal fragments not being added?
                            //System.out.println("hey the next split word is '" + nextFragment + "' next animal is " +nextAnimal);
                            nextFragment="";
                            slicedWords+=1;
                            spaceIndex[numberSpaces]=c;
                            numberSpaces+=1;
                        }
                        else if(CC.equals("'") || CC.equals(",") || CC.equals(".")){;}//do not add apostophe or comma
                        else {
                            if (nextFragment.equals("")){
                                CC=CC.toUpperCase();

                            } nextFragment+=CC;} //add char to next word fragment
                        charArray[c] = CC; // random diy char array

                    }catch (Exception e){;}

                }
                if (numberSpaces>0){

                    for(int s = 0; s < numberSpaces; s++){
                        ;
                        //System.out.print(spaceIndex[s]+", ");
                    }
                    wordNumberHolder = numberSpaces+1;
                    //System.out.println(nextAnimal + " - Number of words: " + wordNumberHolder);

                }

                //System.out.println(spaceIndex[0] + ", " + spaceIndex[1]);
                if(numberSpaces==0){fillRegularCages[numberRegular]=nextAnimal; numberRegular+=1;}
                else {
                    fillExoticCages[numberExotic]=nextAnimal;
                    fillExoticWordNumber[numberExotic]=wordNumberHolder;
                    numberExotic+=1;
                    if(wordNumberHolder>highestWordCount){highestWordCount=wordNumberHolder;}
                }
            }

            if(numberSpaces<1 && nextAnimal.length()>2){ //if there are no spaces add the fragment still which should be capitalized
                fillSlicedWordList[slicedWords]=nextFragment;
                slicedWords+=1;
            }

        }


        regularCages = new String[numberRegular]; // resize regular cages array to the correct size
        exoticCages = new String[numberExotic]; // same for exotic cages array
        exoticWordNumber = new int[numberExotic];
        slicedWordList = new String[slicedWords];
        for(int r = 0; r < numberRegular; r++){
            regularCages[r] = fillRegularCages[r];
        } // fill regular cages
        for(int e = 0; e < numberExotic; e++){
            exoticCages[e] = fillExoticCages[e];
        } //fill exotic cages
        for(int n = 0; n < numberExotic; n++){
            exoticWordNumber[n] = fillExoticWordNumber[n];
        } //fill word number arrays
        for(int s = 0; s < slicedWords; s++){
            slicedWordList[s] = fillSlicedWordList[s];
        } //fill sliced word list

        //System.out.println("highest word number is: " + highestWordCount);
        //int longestNameIndex = indexOf(exoticWordNumber, 2);
        //System.out.println("index isss : " + longestNameIndex +" "+ exoticCages[longestNameIndex]);
        //System.out.println("this is regular cage number 5" + regularCages[5]);
        //System.out.println("this is exotic cage number 6" + exoticCages[6]);

    }

    // public string array which returns all words in the text document in caps
    public String[] getAllWords(){

        String[] strArray = Stream.of(slicedWordList).sorted().toArray(String[]::new);
                String[] dedupArray = new String[strArray.length];
        String placeholderString = "placeholder";
        int newWords = 0;
        for(int a = 0; a < strArray.length; a++){
            if(!strArray[a].equals(placeholderString)){
                dedupArray[newWords] = strArray[a];
                newWords+=1;
                placeholderString=strArray[a];
            } ;
        }
        cleanWordList = new String[newWords];
        for(int c = 0; c < newWords; c++){
            String myCleanWord = dedupArray[c];
            cleanWordList[c]=myCleanWord;
        }
        return cleanWordList;
    }
    public Zoo2() throws IOException {
        //printAnimals();
        //countAnimals();
        cageAnimals();
        //viewCage(55);
        separateCages();
        //printExoticWithWords(2);
        //System.out.println("Random 3 word animal:  "+ RandomExoticAnimal(3));
        //System.out.println("Random regular animal:  "+ RandomRegularAnimal());

        //System.out.println("The number of exotic animals is: " + exoticCages.length*3);



        String[] AllWordArray = getAllWords();

        System.out.println("getting all the words ..");

        String[] ClipedWordArray = new String[AllWordArray.length];
        int ClipedWordCount = 0;
        for(int a = 0; a <  AllWordArray.length; a++){
            String nextStr = AllWordArray[a];
            String mySuffix = null;
            try{mySuffix = StrSearch.getSuffix(nextStr, 3);}
            catch (Exception e){;}
            if(mySuffix!=null &&
                    (  StrSearch.StrSearch("ing",mySuffix)
                    || StrSearch.StrSearch("ed", StrSearch.getSuffix(nextStr, 2) )
                    || StrSearch.StrSearch("en", StrSearch.getSuffix(nextStr, 2) )
                    )
            ) {
                System.out.println("REMOVED: "+ nextStr);
            } else {ClipedWordArray[ClipedWordCount]=nextStr;ClipedWordCount++;}
        }
        if(slicedWord_outputFile == true){
            System.out.println("saving AllTheWords to output file " + defaultFilename_slicedOutput2);
            CreateOutputFile newFile = new CreateOutputFile();
            newFile.CreateOutputFile(defaultFilename_slicedOutput2, ClipedWordArray);
        }
    }
}
