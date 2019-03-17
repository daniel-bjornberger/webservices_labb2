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
                    try {
                        inputAndOutput.printString(httpRequests.createMovie(movieInClient.movieToJsonString()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    try {
                        inputAndOutput.printString(httpRequests.deleteMovie(inputAndOutput.getMovieId()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 5:
                    try {
                        inputAndOutput.printString(httpRequests.changeMovie(inputAndOutput.getMovieId(), inputAndOutput));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 6:

                    inputAndOutput.printString("Swagger Api Documentation is shown in default web browser.");
                    httpRequests.displaySwaggerApiDocumentation();
                    break;

                case 7:
                    inputAndOutput.printString("\nGoodbye!");

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
