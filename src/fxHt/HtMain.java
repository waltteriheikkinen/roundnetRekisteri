package fxHt;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author waltt
 * @version 2.2.2023
 *
 */
public class HtMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("HtGUIView.fxml"));
            final Pane root = ldr.load();
            //final HtGUIController htCtrl = (HtGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("ht.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Ht");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei kaytossa
     */
    public static void main(String[] args) {
        launch(args);
    }
}