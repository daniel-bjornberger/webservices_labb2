package clientpackage;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;


class MovieInClient {

    private String movieId;
    private boolean haveSeen;
    private boolean wantToSee;
    private String title;
    private String director;
    private String actors;
    private String runTime;
    private String genre;
    private String releaseDate;
    private String country;
    private String imdbRating;

    private final static String url = "http://www.omdbapi.com/?apikey=43576692&i=";
    private final static RestTemplate restTemplate = new RestTemplate();


    private MovieInClient(String movieId, boolean haveSeen, boolean wantToSee, String title, String director, String actors, String runTime, String genre, String releaseDate, String country, String imdbRating) {

        this.movieId = movieId;
        this.haveSeen = haveSeen;
        this.wantToSee = wantToSee;
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.runTime = runTime;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.country = country;
        this.imdbRating = imdbRating;

    }



    static MovieInClient createMovieBrief(InputAndOutput inputAndOutput) {

        String movieId;

        JSONObject obj;

        do {

            movieId = inputAndOutput.getMovieId();

            obj = new JSONObject(restTemplate.getForObject(url + movieId, String.class));

            if (!obj.getBoolean("Response")) {

                inputAndOutput.printString("There is no movie on IMDB with the id " + movieId + ". Please try again.");

            }

        } while (!obj.getBoolean("Response"));


        boolean haveSeen = enterHaveSeen(inputAndOutput);

        boolean wantToSee = enterWantToSee(inputAndOutput);


        return new MovieInClient(obj.getString("imdbID"), haveSeen, wantToSee, obj.getString("Title"),
                obj.getString("Director"), obj.getString("Actors"), obj.getString("Runtime"),
                obj.getString("Genre"), obj.getString("Released"), obj.getString("Country"),
                obj.getString("imdbRating"));

    }



    String movieToJsonString() {

        return new Gson().toJson(this);

    }

    static MovieInClient jsonStringToMovie(String jsonString){

        return new Gson().fromJson(jsonString, MovieInClient.class);

    }



    boolean isHaveSeen() {
        return haveSeen;
    }



    void setHaveSeen(boolean haveSeen) {
        this.haveSeen = haveSeen;
    }



    boolean isWantToSee() {
        return wantToSee;
    }



    void setWantToSee(boolean wantToSee) {
        this.wantToSee = wantToSee;
    }



    static boolean enterHaveSeen(InputAndOutput inputAndOutput) {

        inputAndOutput.printString("\nHave you seen this movie? Enter y/Y or n/N:");

        return inputAndOutput.returnTrueOrFalse();

    }



    static boolean enterWantToSee(InputAndOutput inputAndOutput) {

        inputAndOutput.printString("\nDo you want to see this movie? Enter y/Y or n/N:");

        return inputAndOutput.returnTrueOrFalse();

    }


}
