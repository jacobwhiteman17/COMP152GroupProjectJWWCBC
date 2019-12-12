package bsu.comp152;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class JokeDataHandler {
    private HttpClient dataGrabber;
    private String webLocation;

    public JokeDataHandler(){
        dataGrabber = HttpClient.newHttpClient();
    }

    public responseData getData(String webLocation){//getting the data
        var requestBuilder = HttpRequest.newBuilder();
        var dataRequest = requestBuilder.uri(URI.create(webLocation)).build();
        HttpResponse<String> response = null;
        try {
            response = dataGrabber.send(dataRequest, HttpResponse.BodyHandlers.ofString());
        }catch(IOException e){
            System.out.println("Error connecting to network or site");
        }
        catch (InterruptedException e){
            System.out.println("Connection to site broken");
        }
        if (response == null ){
            System.out.println("Something went terribly wrong, ending program");
            System.exit(-1);
        }
        var goodData = response.body();
        var jsonInterpreter = new Gson();
        var jokeData = jsonInterpreter.fromJson(goodData, responseData.class);
        return jokeData;

    }

}
class responseData{//info from site
    String category;
    String type;
    String joke;
    String setup;
    String delivery;
    int id;
}

