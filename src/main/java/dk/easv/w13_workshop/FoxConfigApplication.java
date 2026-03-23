package dk.easv.w13_workshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FoxConfigApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FoxConfigApplication.class.getResource("fox-config-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 1000);

        // Load CSS stylesheet for modern styling
        scene.getStylesheets().add(
            FoxConfigApplication.class.getResource("styles.css").toExternalForm()
        );

        stage.setTitle("Fox Configurator - Professional Orienteering Equipment Setup");
        stage.setScene(scene);
        stage.setMinWidth(800);
        stage.setMinHeight(900);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
