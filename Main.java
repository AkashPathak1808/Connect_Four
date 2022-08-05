package com.akash.connectfour;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class Main extends Application {

	private Controller controller;

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
		GridPane rootGridPane = loader.load();
		
		MenuBar menuBar = createMenu();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		Pane menuPane = (Pane) rootGridPane.getChildren().get(0);
		menuPane.getChildren().add(menuBar);
		
		controller = loader.getController();
		controller.createPlayGround();
		controller.setInput();

		Scene scene = new Scene(rootGridPane);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Connect Four");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	private MenuBar createMenu() {
		Menu fileMenu = new Menu("File");
		
		MenuItem newGame = new MenuItem("New Game");
		newGame.setOnAction(event-> controller.resetGame());
		
		MenuItem resetGame = new MenuItem("Reset Game");
		resetGame.setOnAction(event -> controller.resetGame());
		
		SeparatorMenuItem seperatorMenuItem = new SeparatorMenuItem();
		
		MenuItem exitGame = new MenuItem("Exit Game");
		exitGame.setOnAction(event -> {
			Platform.exit();
			System.exit(0);
		});
		
		fileMenu.getItems().addAll(newGame, resetGame, seperatorMenuItem, exitGame);
		
		Menu helpMenu = new Menu("Help");
		
		MenuItem aboutGame = new MenuItem("About Connect 4");
		aboutGame.setOnAction(event -> aboutGame());
		
		SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
		
		MenuItem aboutMe = new MenuItem("About Me");
		aboutMe.setOnAction(event -> aboutMe());
		
		helpMenu.getItems().addAll(aboutGame, separatorMenuItem, aboutMe);
		
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, helpMenu);
		
		return menuBar;
	}

	private void aboutMe() {
		Alert alertDialog = new Alert(AlertType.INFORMATION);
		alertDialog.setTitle("About The Developer");
		alertDialog.setHeaderText("Akash Pathak");
		alertDialog.setContentText("I love to play games");
		alertDialog.show();
	}

	private void aboutGame() {
		Alert alertDialog = new Alert(AlertType.INFORMATION);
		alertDialog.setTitle("About Connect 4");
		alertDialog.setHeaderText("How to play?");
		alertDialog.setContentText("Connect Four is a two-player connction game in which the "
									+"players first choose a color and then take turns dropping colored discs "
									+"from the top into a seven-column, six-row vertically suspended grid."
									+" The pieces fall straight down, occupying the next available space within the column. "
									+"or diagonal line of four of one's own discs. Connect Four is a solve game. "
									+"The first player can always win by playing the right moves.");
		alertDialog.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
