package bsu.comp152;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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
    public void openWeather() {
        Parent root = null;
        var loc = getClass().getResource("Weather.fxml");
        try {
            root = FXMLLoader.load(loc);
        }catch (IOException e) {
            System.out.println("Could not find FXML file!!!");
        }
        Scene windowContents = new Scene(root, 600, 400); //sets scene
        Stage primaryStage = new Stage();
        primaryStage.setScene(windowContents); //sets the scene in the window
        primaryStage.setTitle("Wally's Weather Report"); //sets window title
        primaryStage.getIcons().add(new Image("https://pngriver.com/wp-content/uploads/2018/04/" +
                "Download-Walrus-PNG-Free-Download-For-Designing-Projects.png")); //sets app icon
        primaryStage.setResizable(false); //prevents resizing
        primaryStage.show(); //displays window
    }
    public void openCurrency() {
        Parent root = null;
        var loc = getClass().getResource("/bsu/comp152/CurrencyWindow.fxml");
        try {
            root = FXMLLoader.load(loc);
        }catch (IOException e){
            System.out.println("The FXML file could not be found.");
        }
        Scene windowContents = new Scene(root, 900,400);
        Stage recipeWindow = new Stage();
        recipeWindow.setScene(windowContents);
        recipeWindow.setTitle("Currency Exchange window");
        recipeWindow.show();
    }
}
