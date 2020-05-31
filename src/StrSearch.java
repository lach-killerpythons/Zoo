
public class StrSearch {

    public static int SpaceCount(String toCount){
        char[] charArray = toCount.toCharArray();
        int gapCount = 0;
        int wordCount = 0;
        for(char C : charArray){
            if(C==' ' &&wordCount>gapCount){gapCount+=1;}
            else if(C!=' ' && C!='\t' &&wordCount==gapCount){wordCount+=1;}
            else{;}
        }
        return gapCount;
    }
    //address: 119 Gibbes St, 		Rockdale, 		NSW 2216
    public static String[] SpaceSlice(String toSlice){
        int wordCount = SpaceCount(toSlice)+1;

        String[] outputArray = new String[wordCount];
        for(int i = 0;i<wordCount;i++){
            outputArray[i] = "";
        }

        char[] charArray = toSlice.toCharArray();
        int wordIndex = 0;
        int gapIndex = 0;
        for(char C : charArray){

            if(C==' ' && wordIndex==gapIndex){gapIndex+=1;}
            //more than one if statement can be true after this -- ORDER IMPORTANT

            if(C!=' ' && C!='\t' &&wordIndex<gapIndex){wordIndex+=1;}
            if(C!=' ' && C!='\t' ){outputArray[wordIndex]+=C;}
        }
        return outputArray;
    }

    public static boolean StrSearch(String findString, String inputString){
        boolean foundStr = false;
        char[] inputCharArray = inputString.toCharArray();
        char[] findCharArray = findString.toCharArray();
        char[] comparisonArray = new char[findCharArray.length];
        int comparisonCounter = 0;
        for(int c = 0; c < inputCharArray.length; c++){
            if(inputCharArray[c]==findCharArray[comparisonCounter]){
                comparisonArray[comparisonCounter]=inputCharArray[c];
                comparisonCounter+=1;
                if(comparisonCounter==findCharArray.length){return true;}
            }  else{comparisonArray=new char[findCharArray.length]; comparisonCounter = 0;}
        }

        return false;
    }

    public static String getSuffix(String toCheck, int numberLetters){
        int myLen = toCheck.length();
        return toCheck.substring(myLen-numberLetters, myLen);
    }

    public static boolean ValidAddress(String toCheck){
        return true;
    }

    public static boolean NoLetters(String toTest){
        char[] myCharArray = toTest.toCharArray();
        for(char C : myCharArray){
            if(Character.isLetter(C)){return false;}
        }
        return true;
    }

    public static boolean ValidEmail(String emailTest){
        int countAtSign = 0;
        char[] myCharArray = emailTest.toCharArray();
        for(char C : myCharArray){
            if(C=='@'){countAtSign+=1;}
        }
        if(countAtSign == 1){return true;}
        else{return  false;} // only valid email if one AtSign present
    }

    // to do : check if year is valid
    public static boolean CheckPostcode(String addressTest){
        String postcodeStr = addressTest.substring(addressTest.length() - 4);
        return  NoLetters(postcodeStr);
    }



    public static boolean ContainsAZ(String toTest){ // returns false if non-letters found
        char[] myCharArray = toTest.toCharArray();
        for(char C : myCharArray){
            if(Character.isDigit(C)){return false;}
        }
        return true;
    }

}
