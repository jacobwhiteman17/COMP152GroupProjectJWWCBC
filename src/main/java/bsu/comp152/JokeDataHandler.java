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

    public JokeDataHandler(String webLocation){
        dataGrabber = HttpClient.newHttpClient();
        this.webLocation = webLocation;
    }

    public ArrayList<jokeDataType> getData(){
        var requestBuilder = HttpRequest.newBuilder();
        var dataRequest = requestBuilder.uri(URI.create(webLocation)).build();
        HttpResponse<String> response = null;
        try{
            response = dataGrabber.send(dataRequest, HttpResponse.BodyHandlers.ofString());
        }
        catch(IOException e){
            System.out.println("Cannot connect to site");
        }
        catch(InterruptedException e){
            System.out.println("Eh, broke connection");
        }
        if (response == null){
            System.out.println("UHHH");
            System.exit(-1);
        }
        var goodData = response.body();
        //var jsonInterpreter = new Gson();
        var array = new ArrayList<jokeDataType>();
        return array;
    }

    class responseDataType{
        String title;
        float version;
        String href;
        ArrayList<jokeDataType> results;
    }

    class jokeDataType{
        String category;
        String type;
        String joke;
        String setup;
        String delivery;
    }
}
