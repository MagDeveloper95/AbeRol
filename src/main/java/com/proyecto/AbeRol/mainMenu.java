package com.proyecto.AbeRol;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.proyecto.AbeRol.Model.MasterDAO;
import com.proyecto.AbeRol.Model.PlayerDAO;
import com.proyecto.AbeRol.Model.Rol;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class mainMenu {

	@FXML
	private Button buttEditMaster;

	@FXML
	private Button buttEditRol;

	@FXML
	private Button buttEditPlayer;

	@FXML
	private Button buttExit;

	@FXML
	private ComboBox<Rol> choiceRol;

	@FXML
	private GridPane gridPlayer;

	@FXML
	public void initialize() {
		MasterDAO user = new MasterDAO(1);
		PlayerDAO p1 = new PlayerDAO(1);
		PlayerDAO p2 = new PlayerDAO(2);
		
		gridPlayer.setGridLinesVisible(true);
		this.choiceRol.setItems(user.getRol());
		
	}

	@FXML
	protected void editMaster(ActionEvent Event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("editMaster.fxml"));
		Parent modal;
		try {
			modal = fxmlLoader.load();
			Stage modalStage = new Stage();
			modalStage.initModality(Modality.APPLICATION_MODAL);
			modalStage.initOwner(App.rootstage);
			Scene modalScene = new Scene(modal);
			modalStage.setScene(modalScene);
			modalStage.showAndWait();
			modalStage.setResizable(false);

		} catch (IOException ex) {
			Logger.getLogger(mainMenu.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	protected void editRol(ActionEvent Event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("editRol.fxml"));
		Parent modal;
		try {
			modal = fxmlLoader.load();
			Stage modalStage = new Stage();
			modalStage.initModality(Modality.APPLICATION_MODAL);
			modalStage.initOwner(App.rootstage);
			Scene modalScene = new Scene(modal);
			modalStage.setScene(modalScene);
			modalStage.showAndWait();
			modalStage.setResizable(false);

		} catch (IOException ex) {
			Logger.getLogger(mainMenu.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	protected void EditPlayer(ActionEvent Event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("playerController.fxml"));
		Parent modal;
		try {
			modal = fxmlLoader.load();
			Stage modalStage = new Stage();
			modalStage.initModality(Modality.APPLICATION_MODAL);
			modalStage.initOwner(App.rootstage);
			Scene modalScene = new Scene(modal);
			modalStage.setScene(modalScene);
			modalStage.showAndWait();
			modalStage.setResizable(false);

		} catch (IOException ex) {
			Logger.getLogger(mainMenu.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void exit(ActionEvent event) {
		Stage stage = (Stage) this.buttExit.getScene().getWindow();
		stage.close();
	}

}
