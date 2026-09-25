package PracticePrograms;


import java.util.ArrayList;
import java.util.Arrays;

class CEwithTryCatch {
    ArrayList<String> languages = new ArrayList<>(Arrays.asList("Java", "Python", "JavaScript"));
    // check the exception condition
    public void checkLanguage(String language) throws CustomException {
        // throw exception if language already present in ArrayList
        if(languages.contains(language)) {
            throw new CustomException(language + " already exists");
        }
        else {
            // insert language to ArrayList
            languages.add(language);
            System.out.println(language + " is added to the ArrayList");
        }
    }
    public static void main(String[] args) {
        // create object of Main class
        CEwithTryCatch obj = new CEwithTryCatch();
        // exception is handled using try...catch
        try {
            obj.checkLanguage("Swift");
            obj.checkLanguage("C");
            obj.checkLanguage("Java");
        }
        catch(CustomException e) {
            System.out.println("[" + e + "] Exception Occured");
        }
        System.out.println();
    }
}