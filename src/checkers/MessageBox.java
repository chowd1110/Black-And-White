package checkers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MessageBox {
	
	public static void createMessageBox(String title, String message) {
		Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		
		window.setMinWidth(300);
		window.setMinHeight(100);
		
		Label label = new Label();
		label.setText(message);
		
		VBox layout = new VBox(10);
		layout.getChildren().add(label);
		
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		
		window.setScene(scene);
		window.showAndWait();
		
	
		
	}

}
