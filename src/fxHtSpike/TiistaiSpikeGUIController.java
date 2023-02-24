package fxHtSpike;



import fi.jyu.mit.fxgui.*;
import javafx.fxml.FXML;




/**
 * @author waltt
 * @version 22.2.2023
 *
 */
public class TiistaiSpikeGUIController {
    @FXML void HandleEteenPain() {
        ModalController.showModal(TiistaiSpikeGUIController.class.getResource("ParitSkabat.fxml"), "Parien valinta", null, "");
        }

    @FXML
    void HandlePoista() {
        ModalController.showModal(TiistaiSpikeGUIController.class.getResource("PoistaPelaaja.fxml"), "Pelaajan poisto", null, "");
        }

    @FXML
    void HandlePoistaPelaaja() {
        Dialogs.showMessageDialog("Vielä ei osata poistaa pelaajaa");
    }

    @FXML
    void HandleTallenna() {
        Dialogs.showMessageDialog("Vielä ei osata tallentaa pelaajaa");
    }

    @FXML
    void HandleUusiPelaaja() {
        ModalController.showModal(TiistaiSpikeGUIController.class.getResource("uusipelaaja.fxml"), "Uusi pelaaja", null, "");
    }

    @FXML
    void HandleValitsePelaaja() {
        Dialogs.showMessageDialog("Vielä ei osata valita pelaajaa");
    }

}