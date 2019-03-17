package clientpackage;

import java.io.IOException;

public class ClientMain {


    public static void main(String[] args) {


        InputAndOutput inputAndOutput = new InputAndOutput();

        HttpRequests httpRequests = new HttpRequests();

        int option;


        do {

            option = inputAndOutput.menuInput();

            switch (option) {

                case 1:
                    try {
                        inputAndOutput.printString(httpRequests.getAllMovies());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    try {
                        inputAndOutput.printString(httpRequests.getOneMovie(inputAndOutput.getMovieId()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    MovieInClient movieInClient = MovieInClient.createMovieBrief(inputAndOutput);
                    httpRequests.createMovie();
                    break;

                case 4:
                    System.out.println("delete movie");
                    break;

                case 5:
                    System.out.println("change movie");
                    break;

                case 6:

                    inputAndOutput.printString("Swagger Api Documentation is shown in default web browser.");
                    httpRequests.displaySwaggerApiDocumentation();
                    break;

                case 7:
                    inputAndOutput.printString("Goodbye!");

                    inputAndOutput.close();

                    try {
                        httpRequests.shutDownUnirest();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;

            }

        } while (option != 7);

    }


}
