package bsu.comp152;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        var loc = getClass().getResource("Main.fxml");
        try {
            root = FXMLLoader.load(loc);
        }catch (IOException e) {
            System.out.println("Need the FXML file");
        }
        Scene windowContents = new Scene(root, 600,450);
        primaryStage.setScene(windowContents);
        primaryStage.setTitle("COMP-152 Group Project");
        primaryStage.show();
    }
}
