package clientpackage;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import java.io.IOException;
import java.net.URISyntaxException;

import static java.awt.Desktop.getDesktop;


public class HttpRequests {


    private final static String omdbUrl = "http://www.omdbapi.com/?apikey=43576692&i=";

    private final static String awsUrl = "http://moviedatabase-env.ma3bh5uw39.eu-north-1.elasticbeanstalk.com/";

    private final static String apiName = "movies/";

    private final static String swagger = "swagger-ui.html";



    public HttpRequests(){
    }



    public String getAllMovies() throws Exception {

        HttpResponse<JsonNode> response = Unirest.get(awsUrl + apiName).header("accept", "application/json").asJson();

        int status = response.getStatus();

        JSONArray jsonArray = response.getBody().getArray();

        StringBuilder builder = new StringBuilder();

        if (status == 200) {

            if (jsonArray.length() > 0) {

                for (int i = 0; i < jsonArray.length(); i++) {

                    builder.append("\n\n").append(jsonArray.getJSONObject(i).toString(4));

                }

                return builder.toString();
            }

            else {

                return ("\nThere are no movies in the database!");

            }

        }

        else {
            throw new Exception("Invalid response from Unirest.get.");
        }

    }



    public String getOneMovie(String movieId) throws Exception {

        int status = headResponseStatus(movieId);

        if (status == 200) {

            HttpResponse<JsonNode> response = Unirest.get(awsUrl + apiName + movieId).header("accept", "application/json").asJson();

            return "\n\n" + response.getBody().getObject().toString(4);

        }

        else if (status == 404) {

            return "\n\nThere is no movie in the database with the id " + movieId + ".";

        }

        else {
            throw new Exception("Invalid response from Unirest.get.");
        }

    }



    public void createMovie(String movieId, boolean haveSeen, boolean wantToSee) {



    }



    public void displaySwaggerApiDocumentation() {

        try {

            getDesktop().browse(new java.net.URI(awsUrl + swagger));

        } catch (IOException e) {

            e.printStackTrace();

        } catch (URISyntaxException e) {

            e.printStackTrace();

        }

    }



    private int headResponseStatus(String movieId) throws UnirestException {

        return Unirest.head(awsUrl + apiName + movieId).header("accept", "application/json").asJson().getStatus();

    }



    public void shutDownUnirest() throws IOException {

        Unirest.shutdown();

    }


}
