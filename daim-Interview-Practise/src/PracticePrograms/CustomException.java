package PracticePrograms;


// create a checked exception class
class CustomException extends Exception {
    public CustomException(String message) {
        // call the constructor of Exception class
        super(message);
    }
}
