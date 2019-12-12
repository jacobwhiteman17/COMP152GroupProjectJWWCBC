//Jacob Whiteman
package bsu.comp152;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Joke extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        var loc = getClass().getResource("/bsu/comp152/Joke.fxml");//this was a PAIN to figure out. couldn't find the file for a long time
        System.out.println(loc);

        try {
            root = FXMLLoader.load(loc);
        }catch (IOException e){
            System.out.println("Couldn't Find FXML file!!!!!!");
        }
        Scene windowContents = new Scene(root, 600,400);
        primaryStage.setScene(windowContents);
        primaryStage.setTitle("Jokes");
        primaryStage.show();//setting the stage
    }
}
