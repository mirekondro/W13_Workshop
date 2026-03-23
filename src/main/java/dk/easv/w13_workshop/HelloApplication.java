package dk.easv.w13_workshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX entry point.
 */
public class FoxConfigApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/foxconfig/ui/MainView.fxml"));
        Scene scene = new Scene(loader.load(), 820, 700);
        scene.getStylesheets().add(
                getClass().getResource("/com/foxconfig/ui/styles.css").toExternalForm());

        stage.setTitle("🦊 Fox Configurator");
        stage.setScene(scene);
        stage.setMinWidth(700);
        stage.setMinHeight(600);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

