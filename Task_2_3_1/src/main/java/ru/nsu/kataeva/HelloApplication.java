package ru.nsu.kataeva;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

@Generated
public class HelloApplication extends Application {
    private static final int WINDOW_WIDTH = 620;
    private static final int WINDOW_HEIGHT = 480;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                HelloApplication.class.getResource("hello-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setTitle("Snake");
        stage.setScene(scene);
        stage.show();
        scene.getRoot().requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}