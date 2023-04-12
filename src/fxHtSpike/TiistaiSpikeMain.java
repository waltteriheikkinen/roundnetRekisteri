package fxHtSpike;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author  waltt
 * @version 22.2.2023
 *
 */
public class TiistaiSpikeMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("TiistaiSpikeGUIView.fxml"));
            final Pane root = ldr.load();
            final TiistaiSpikeGUIController tiistaispikeCtrl = (TiistaiSpikeGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("tiistaispike.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("TiistaiSpike");
            primaryStage.show();
            
            TiistaiSpike tiistaispike = new TiistaiSpike();
            tiistaispikeCtrl.setKerho(tiistaispike);
            
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