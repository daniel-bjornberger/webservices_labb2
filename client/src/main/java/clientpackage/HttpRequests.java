package clientpackage;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URISyntaxException;

import static java.awt.Desktop.getDesktop;


public class HttpRequests {


    private final static String awsUrl = "http://moviedatabase-env.ma3bh5uw39.eu-north-1.elasticbeanstalk.com/";

    private final static String apiName = "movies/";

    private final static String swagger = "swagger-ui.html";



    public HttpRequests(){
    }



    public String getAllMovies() throws Exception {

        HttpResponse<JsonNode> response = Unirest.get(awsUrl + apiName)
                .header("accept", "application/json")
                .asJson();

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

                return ("\n\nThere are no movies in the database!");

            }

        }

        else {
            throw new Exception("Invalid response from Unirest.get.");
        }

    }



    public String getOneMovie(String movieId) throws Exception {

        int status = headResponseStatus(movieId);

        if (status == 200) {

            HttpResponse<JsonNode> response = Unirest.get(awsUrl + apiName + movieId)
                    .header("accept", "application/json")
                    .asJson();

            if (response.getStatus() == 200) {

                return "\n\n" + response.getBody().getObject().toString(4);

            }

            else {
                throw new Exception("Invalid response from Unirest.get.");
            }

        }

        else if (status == 404) {

            return "\n\nThere is no movie in the database with the id " + movieId + ".";

        }

        else {
            throw new Exception("Invalid response from Unirest.head.");
        }

    }



    public String createMovie(String jsonString) throws Exception {

        JSONObject jsonObject = new JSONObject(jsonString);

        String movieId = jsonObject.getString("movieId");

        int status = headResponseStatus(movieId);


        if (status == 404) {

            HttpResponse<JsonNode> response = Unirest.post(awsUrl + apiName)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(jsonString)
                    .asJson();

            if (response.getStatus() == 200) {

                return "\n\nThe movie was successfully inserted into the database.";

            }

            else {
                throw new Exception("Invalid response from Unirest.post.");
            }

        }

        else if (status == 200) {

            return "\n\nThere is already a movie in the database with the same id. The database has not been modified.";

        }

        else {
            throw new Exception("Invalid response from Unirest.head.");
        }

    }



    public String deleteMovie(String movieId) throws Exception {

        int status = headResponseStatus(movieId);

        if (status == 200) {

            HttpResponse<JsonNode> response = Unirest.delete(awsUrl + apiName + movieId)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .asJson();

            if (response.getStatus() == 200) {

                return "\n\nThe movie was successfully deleted from the database.";

            }

            else {
                throw new Exception("Invalid response from Unirest.delete.");
            }

        }

        else if (status == 404) {

            return "\n\nThere is no movie in the database with the id " + movieId + ".";

        }

        else {
            throw new Exception("Invalid response from Unirest.head.");
        }

    }



    public String changeMovie(String movieId, InputAndOutput inputAndOutput) throws Exception {

        int status = headResponseStatus(movieId);

        if (status == 200) {

            HttpResponse<JsonNode> response = Unirest.get(awsUrl + apiName + movieId)
                    .header("accept", "application/json")
                    .asJson();

            MovieInClient movieInClient = MovieInClient.jsonStringToMovie(response.getBody()
                    .getObject().toString());

            inputAndOutput.printHaveSeenAndWantToSee(movieInClient);

            movieInClient.setHaveSeen(MovieInClient.enterHaveSeen(inputAndOutput));

            movieInClient.setWantToSee(MovieInClient.enterWantToSee(inputAndOutput));


            HttpResponse<JsonNode> responsePut = Unirest.put(awsUrl + apiName + movieId)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(movieInClient.movieToJsonString())
                    .asJson();

            if (responsePut.getStatus() == 200) {

                return "\n\nThe movie was successfully updated.";

            }

            else {
                throw new Exception("Invalid response from Unirest.put.");
            }



        }

        else if (status == 404) {

            return "\n\nThere is no movie in the database with the id " + movieId + ".";

        }

        else {
            throw new Exception("Invalid response from Unirest.head.");
        }

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

        return Unirest.head(awsUrl + apiName + movieId)
                .header("accept", "application/json")
                .asJson()
                .getStatus();

    }



    public void shutDownUnirest() throws IOException {

        Unirest.shutdown();

    }


}
