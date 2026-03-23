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
        Scene scene = new Scene(fxmlLoader.load(), 800, 900);
        stage.setTitle("Fox Configurator - Orienteering Equipment Setup");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
