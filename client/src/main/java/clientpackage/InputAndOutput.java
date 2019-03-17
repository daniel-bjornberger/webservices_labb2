package clientpackage;

import java.util.Scanner;


public class InputAndOutput {


    private Scanner scanner;



    public InputAndOutput() {
        this.scanner = new Scanner(System.in);
    }



    public void printString(String outString) {
        System.out.println(outString + "\n\n");
    }



    public String getString(){
        return this.scanner.nextLine();
    }



    public String getMovieId() {

        printString("Enter a IMDB movie id, in the format: tt1234567 (the letters 'tt' and 7 digits):");

        String returnString = getString();

        while (!returnString.matches("tt[0-9]{7}")) {

            printString("\nPlease enter a IMDB movie id (format: tt1234567):");
            returnString = getString();
        }

        return returnString;

    }



    public int menuInput() {

        String optionString;
        boolean inputOk;

        do {
            printMenu();
            optionString = getString();

            inputOk = menuChoiceOk(optionString);

            if (!inputOk) {
                printString("Invalid choice.");
            }

        } while (!inputOk);

        return Integer.valueOf(optionString);
    }



    public boolean menuChoiceOk(String inputString) {

        boolean inputOk = false;

        int i = 1;

        while (!inputOk && i <= 7) {

            inputOk = (inputString.equals(String.valueOf(i++)));

        }
        return inputOk;
    }



    private void printMenu() {
        System.out.println("**** Movie Database ****\n\n1. Get All Movies\n2. Get One Movie\n3. Create Movie\n4. Delete Movie\n5. Change Movie\n6. Swagger Api Documentation\n7. Exit\n\nEnter your choice (1 - 7):");
    }



    public String returnNonemptyString() {

        String returnString = getString();

        while (returnString.length() == 0) {

            printString("Please enter a nonempty string.");
            returnString = getString();
        }

        return returnString;
    }



    public boolean returnTrueOrFalse() {

        String inputString;

        do {
            inputString = getString().toLowerCase();

            if ( !(inputString.equals("y") || inputString.equals("n")) ) {
                printString("Enter y/Y or n/N:");
            }

        } while ( !(inputString.equals("y") || inputString.equals("n")) );

        return inputString.equals("y");
    }



    public void printHaveSeenAndWantToSee(MovieInClient movieInClient) {

        String haveSeen = "No";
        String wantToSee = "No";

        if (movieInClient.isHaveSeen()) {
            haveSeen = "Yes";
        }

        if (movieInClient.isWantToSee()) {
            wantToSee = "Yes";
        }

        printString("Current settings:\n\nHave seen:   " + haveSeen + "\nWant to see: " + wantToSee);

    }



    public void close(){
        this.scanner.close();
    }


}
