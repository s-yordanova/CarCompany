package Automobile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainPanelController {


    @FXML
    private AnchorPane ap;

    @FXML
    private Pane pane;

    @FXML
    void AddBrand(ActionEvent event) {
        loadPage("/AddBrand");
    }

    @FXML
    void AddExtra(ActionEvent event) {
        loadPage("/AddExtra");
    }

    @FXML
    void AddModel(ActionEvent event) {
        loadPage("/AddModel");
    }

    @FXML
    void AddType(ActionEvent event) {
        loadPage("/AddType");
    }

    @FXML
    void Catalog(ActionEvent event) {
        loadPage("/AddCatalog");
    }

    @FXML
    void Exit(ActionEvent event) {
System.exit(0);
    }
    private void loadPage(String page) {
        Parent root = null;
        try {
            root= FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pane.getChildren().add(root);

    }
}
