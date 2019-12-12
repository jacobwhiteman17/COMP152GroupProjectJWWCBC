package bsu.comp152;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    public void openJokes(){
        Parent root = null;
        var loc = getClass().getResource("/bsu/comp152/Joke.fxml");
        try {
            root = FXMLLoader.load(loc);
        }catch (IOException e){
            System.out.println("Couldn't Find FXML file!!!!!!");
        }
        Scene windowContents = new Scene(root, 600,400);
        Stage jokeWindow = new Stage();
        jokeWindow.setScene(windowContents);
        jokeWindow.setTitle("Jokes");
        jokeWindow.show();
    }
}
