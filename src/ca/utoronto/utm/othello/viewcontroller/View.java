package ca.utoronto.utm.othello.viewcontroller;

import java.util.Optional;

import ca.utoronto.utm.othello.model.OthelloModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View {
	private OthelloModel model;
	private Scene sceneIntro, scene;

	public View(OthelloModel othelloModel, Stage stage) {
		this.model = othelloModel;
		initUI(stage);

	}

	private void initUI(Stage stage) {
		GridPane introGrid;
		GridPane grid;
		final int initialMins = 5;
		final int initialSec = 0;
		final Spinner<Integer> spinner = new Spinner<Integer>();
		final Spinner<Integer> spinner2 = new Spinner<Integer>();
		ComboBox<String> comboBox = new ComboBox<String>();
		ComboBox<String> playerBox = new ComboBox<String>();
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20,initialMins);
		SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59,initialSec);
		
		// CONTROLLER
		// CONTROLLER->MODEL hookup
		ControllerHandler controller = new ControllerHandler(model);

		// VIEW LAYOUT
		introGrid = new GridPane();
		introGrid.setPadding(new Insets(360, 20, 20, 40));
		introGrid.setMinWidth(800);
		introGrid.setMaxWidth(700);
		introGrid.setId("welcome");
		introGrid.setAlignment(Pos.CENTER);
		
		grid = new GridPane();
		grid.setPadding(new Insets(55, 20, 20, 42));
		grid.setMinWidth(700);
		grid.setMaxWidth(700);
		grid.setId("grid");
		grid.setAlignment(Pos.CENTER);
		
		// VIEW COMPONENTS
		Label welcome_msg = new Label();
		Button Launch = new Button();
		Label choose_opponent = new Label();
		Label choose_starter = new Label();
		Label choose_minute = new Label();
		Label choose_second = new Label();
		
		CurrentPlayerLabel nextPlayer = new CurrentPlayerLabel();
		TokenLabelX tokenX = new TokenLabelX();
		TokenLabelO tokenO = new TokenLabelO();
		WinnerLabel winner = new WinnerLabel();
		Player1Label p1 = new Player1Label();
		Player2Label p2 = new Player2Label();
		TimeLabelX time = new TimeLabelX();
		TimeLabelO time2 = new TimeLabelO();
		Button restartBtn = new Button();
		Button undoBtn = new Button();
		Button HvHBtn = new Button();
		Button HvGBtn = new Button();
		Button HvRBtn = new Button();
		Button HvBBtn = new Button();
		Label Prompt = new Label();
		BetterHint Better = new BetterHint();
		SingleHint Hint = new SingleHint();
		GreedyHint GreedyHint = new GreedyHint();
		RandomHint RandomHint = new RandomHint();
		
		spinner.setValueFactory(valueFactory);
		spinner.setStyle("-fx-font-weight:bold;-fx-background-color:#0039e6;-fx-padding:10px;");
		spinner.setMaxWidth(Double.MAX_VALUE);
		spinner.setMaxHeight(Double.MAX_VALUE);
		
		spinner2.setValueFactory(valueFactory2);
		spinner2.setStyle("-fx-font-weight:bold;-fx-background-color:#0039e6;-fx-padding:10px;");
		spinner2.setMaxWidth(Double.MAX_VALUE);
		spinner2.setMaxHeight(Double.MAX_VALUE);

		comboBox.getSelectionModel().select("Human");
		comboBox.getItems().add("Human");
		comboBox.getItems().add("Greedy");
		comboBox.getItems().add("Random");
		comboBox.getItems().add("Better");
		comboBox.setStyle("-fx-font-weight:bold;-fx-background-color:#038a47;-fx-padding:10px;");
		comboBox.setMaxWidth(Double.MAX_VALUE);
		comboBox.setMaxHeight(Double.MAX_VALUE);

		
		playerBox.getSelectionModel().select("Yes");
		playerBox.getItems().add("Yes");
		playerBox.getItems().add("No");
		playerBox.setStyle("-fx-font-weight:bold;-fx-background-color:#038a47;-fx-padding:10px;");
		playerBox.setMaxWidth(Double.MAX_VALUE);
		playerBox.setMaxHeight(Double.MAX_VALUE);

		
		welcome_msg.setText("Welcome to our first ever group project in UTM Computer Science Program. \nHope you have a seemless experience "
				+ "playing this game made by TheNerds :) \n");
		welcome_msg.setTextFill(Color.web("#ffffff"));
		welcome_msg.setStyle("-fx-font-weight:bold;-fx-font-size:16px;-fx-padding: 0 0 20 0;");
		welcome_msg.setMaxWidth(Double.MAX_VALUE);
		welcome_msg.setMaxHeight(Double.MAX_VALUE);

		Launch.setText("START GAME");
		Launch.setOnAction(e -> {
			new ControllerHandler(model, spinner, spinner2, comboBox.getValue(), playerBox.getValue());
			stage.setScene(scene);
			});
		Launch.setTextFill(Color.web("#ffffff"));
		Launch.setStyle(
				"-fx-background-color: #000; -fx-border-color: #000; -fx-border-width: 0px; -fx-font-weight:bold;-fx-padding:15px;-fx-font-size:18px;");
		Launch.setMaxWidth(Double.MAX_VALUE);
		Launch.setMaxHeight(Double.MAX_VALUE);

		
		choose_opponent.setText("CHOOSE YOUR OPPONENT");
		choose_opponent.setTextFill(Color.web("#ffffff"));
		choose_opponent.setStyle("-fx-font-size:12px;-fx-font-weight:bold;-fx-background-color:#038a47;-fx-padding:10px;");
		choose_opponent.setMaxWidth(Double.MAX_VALUE);
		choose_opponent.setMaxHeight(Double.MAX_VALUE);

		
		choose_starter.setText("DO YOU WANT TO GO FIRST?");
		choose_starter.setTextFill(Color.web("#ffffff"));
		choose_starter.setStyle("-fx-font-size:12px;-fx-font-weight:bold;-fx-background-color:#038a47;-fx-padding:10px;");
		choose_starter.setMaxWidth(Double.MAX_VALUE);
		choose_starter.setMaxHeight(Double.MAX_VALUE);

		
		choose_minute.setText("GAME DURATION:   MINUTES:");
		choose_minute.setTextFill(Color.web("#ffffff"));
		choose_minute.setStyle("-fx-font-weight:bold;-fx-background-color:#0039e6;-fx-padding:10px;");
		choose_minute.setMaxWidth(Double.MAX_VALUE);
		choose_minute.setMaxHeight(Double.MAX_VALUE);

		
		choose_second.setText("          SECONDS:");
		choose_second.setTextFill(Color.web("#ffffff"));
		choose_second.setStyle("-fx-font-weight:bold;-fx-background-color:#0039e6;-fx-padding:10px;");
		choose_second.setMaxWidth(Double.MAX_VALUE);
		choose_second.setMaxHeight(Double.MAX_VALUE);

		for (int i = 0; i < 15; i++) {
			Label label = new Label();
			label.setMinWidth(40);
			label.setMinHeight(40);
			introGrid.add(label, i, 0, 1, 1);
		}

		ButtonLabel btn;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				btn = new ButtonLabel();
				btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				grid.add(btn, col, row);
				if (model.othello.othello.getToken(row, col) != ' ') {
					ImageView imageView = new ImageView(
							new Image("res/" + model.othello.othello.getToken(row, col) + ".png"));
					imageView.setFitHeight(30);
					imageView.setFitWidth(30);
					btn.setGraphic(imageView);
				} else {
					ImageView imageView = new ImageView(new Image("res/empty.png"));
					imageView.setFitHeight(30);
					imageView.setFitWidth(30);
					btn.setGraphic(imageView);
				}
				if (model.AvailableExists(row, col)) {
					ImageView availableView = new ImageView(new Image("res/available.png"));
					availableView.setFitHeight(30);
					availableView.setFitWidth(30);
					btn.setGraphic(availableView);
					btn.setStyle(
							"-fx-background-color: transparent; -fx-border-color: #000000; -fx-border-width: 1px;");
				} else {
					btn.setId("unavailable");
				}

				btn.setPadding(new Insets(5));
				model.setGrid(grid);
				btn.setOnAction(controller);
				model.attach(btn);
			}
		}
	
		ImageView imageView = new ImageView(new Image("res/Steve.png"));
		imageView.setFitHeight(30);
		imageView.setFitWidth(30);
		Better.setGraphic(imageView);
		Better.setText("STEVE JOB'S HINT  ");
		Better.setMaxWidth(Double.MAX_VALUE);
		Better.setMaxHeight(Double.MAX_VALUE);
		Better.setTextFill(Color.web("#ffffff"));
		Better.setStyle("-fx-background-color: #000; -fx-border-color: #000; -fx-border-width: 0px; -fx-font-weight:bold;");
		
		Hint.setText("GET HINT");
		Hint.setMaxWidth(Double.MAX_VALUE);
		Hint.setMaxHeight(Double.MAX_VALUE);
		Hint.setTextFill(Color.web("#ffffff"));
		Hint.setMaxWidth(Double.MAX_VALUE);
		Hint.setStyle("-fx-background-color: #000; -fx-border-color: #000; -fx-border-width: 0px; -fx-font-weight:bold;");
		
		GreedyHint.setText("WINNING HINTS");
		GreedyHint.setMaxWidth(Double.MAX_VALUE);
		GreedyHint.setMaxHeight(Double.MAX_VALUE);
		GreedyHint.setTextFill(Color.web("#ffffff"));
		GreedyHint.setStyle("-fx-background-color: #0039e6; -fx-border-color: #0039e6; -fx-border-width: 0px; -fx-font-weight:bold;");

		RandomHint.setText("RANDOM HINTS");
		RandomHint.setMaxWidth(Double.MAX_VALUE);
		RandomHint.setMaxHeight(Double.MAX_VALUE);
		RandomHint.setTextFill(Color.web("#ffffff"));
		RandomHint.setStyle("-fx-background-color: #0039e6; -fx-border-color: #0039e6; -fx-border-width: 0px; -fx-font-weight:bold;");

		HvHBtn.setText("Human");
		HvHBtn.setTextFill(Color.web("#ffffff"));
		HvHBtn.setStyle("-fx-background-color: #038a47; -fx-border-color: #038a47 #000000 #000000 #038a47; -fx-font-weight:bold; -fx-padding:5px;");
		HvHBtn.setMaxWidth(Double.MAX_VALUE);
		HvHBtn.setMaxHeight(Double.MAX_VALUE);

		HvGBtn.setText("Greedy");
		HvGBtn.setTextFill(Color.web("#ffffff"));
		HvGBtn.setStyle("-fx-background-color: #038a47; -fx-border-color: #038a47; -fx-font-weight:bold; -fx-padding:5px;");
		HvGBtn.setMaxWidth(Double.MAX_VALUE);
		HvGBtn.setMaxHeight(Double.MAX_VALUE);

		HvRBtn.setText("Random");
		HvRBtn.setTextFill(Color.web("#ffffff"));
		HvRBtn.setStyle("-fx-background-color: #038a47; -fx-border-color: #038a47; -fx-font-weight:bold; -fx-padding:5px;");
		HvRBtn.setMaxWidth(Double.MAX_VALUE);
		HvRBtn.setMaxHeight(Double.MAX_VALUE);

		HvBBtn.setText("Better");
		HvBBtn.setTextFill(Color.web("#ffffff"));
		HvBBtn.setStyle("-fx-background-color: #038a47; -fx-border-color: #000000 #038a47 #038a47 #000000; -fx-font-weight:bold; -fx-padding:5px;");
		HvBBtn.setMaxWidth(Double.MAX_VALUE);
		HvBBtn.setMaxHeight(Double.MAX_VALUE);


		restartBtn.setText("RESTART GAME");
		restartBtn.setOnAction(e -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Warning Dialog");
			alert.setHeaderText("Are you sure you want to restart the game?");
			ButtonType buttonYes = new ButtonType("Yes, I'm Sure");
			ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(buttonYes, buttonTypeCancel);
			alert.setResizable(false);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonYes) {
				model.restart();
				model.setHasStarted(false);
				stage.setScene(sceneIntro);
			}
		});
		restartBtn.setTextFill(Color.web("#ffffff"));
		restartBtn.setMaxWidth(Double.MAX_VALUE);
		restartBtn.setMaxHeight(Double.MAX_VALUE);
		restartBtn.setStyle("-fx-background-color: #b30000; -fx-border-color: #b30000; -fx-border-width: 7px; -fx-font-weight:bold;");

		Image undo = new Image("res/undo.png");
		ImageView UndoimageView = new ImageView(undo);
		UndoimageView.setFitHeight(18);
		UndoimageView.setFitWidth(20);
		undoBtn.setText("U");
		undoBtn.setTextFill(Color.web("#000000"));
		undoBtn.setGraphic(UndoimageView);
		undoBtn.setMaxWidth(Double.MAX_VALUE);
		undoBtn.setMaxHeight(Double.MAX_VALUE);
		undoBtn.setStyle("-fx-background-color: #b30000; -fx-border-color: #b30000; -fx-border-width: 7px; -fx-font-size:1px;");
		

		Image x = new Image("res/x.png");
		ImageView XimageView = new ImageView(x);
		XimageView.setFitHeight(30);
		XimageView.setFitWidth(30);
		nextPlayer.setText(" is now playing! ");
		nextPlayer.setTextFill(Color.web("#ffffff"));
		nextPlayer.setMaxWidth(Double.MAX_VALUE);
		nextPlayer.setMaxHeight(Double.MAX_VALUE);
		nextPlayer.setStyle("-fx-background-color: #b30000; -fx-border-color: #b30000; -fx-border-width: 7px; -fx-font-weight:bold;");

		nextPlayer.setGraphic(XimageView);
		tokenX.setText("2");
		tokenX.setTextFill(Color.web("#ffffff"));
		tokenX.setMaxWidth(Double.MAX_VALUE);
		tokenX.setMaxHeight(Double.MAX_VALUE);
		tokenX.setStyle("-fx-background-image: url('res/x.png'); -fx-background-position: center, center; -fx-background-repeat: no-repeat; "
				+ "-fx-background-size: 30px; -fx-background-color: #b30000; -fx-border-color: #b30000; -fx-padding: 12px; -fx-font-weight:bold;");

		Image o = new Image("res/o.png");
		ImageView OimageView = new ImageView(o);
		OimageView.setFitHeight(30);
		OimageView.setFitWidth(30);
		tokenO.setText("2");
		tokenO.setTextFill(Color.web("#000000"));
		tokenO.setMaxWidth(Double.MAX_VALUE);
		tokenO.setMaxHeight(Double.MAX_VALUE);
		tokenO.setStyle("-fx-background-image: url('res/o.png'); -fx-background-position: center, center; -fx-background-repeat: no-repeat; "
				+ "-fx-background-size: 30px; -fx-background-color: #b30000; -fx-border-color: #b30000; -fx-padding: 12px; -fx-font-weight:bold;");

		for (int i = 0; i < 8; i++) {
			Label label = new Label();
			label.setMinWidth(38);
			label.setMinHeight(38);
			grid.add(label, 9 + i, 0, 1, 1);
		}

		Prompt.setText("\n CHANGE YOUR OPPONENT");
		Prompt.setTextFill(Color.web("#ffffff"));
		Prompt.setStyle(
				"-fx-border-color: transparent #ffffff #ffffff #ffffff; -fx-font-weight:bold; -fx-padding:5px 5px 90px 5px;");
		Prompt.setMaxWidth(Double.MAX_VALUE);
		
		winner.setText("Game in Progress");
		winner.setTextFill(Color.web("#000"));
		winner.setStyle(
				"-fx-background-color: #b2b2b2; -fx-border-color: #b2b2b2; -fx-font-weight:bold; -fx-padding:5px;");
		winner.setMaxWidth(Double.MAX_VALUE);
		winner.setMaxHeight(Double.MAX_VALUE);
	
		
		introGrid.add(welcome_msg, 0, 1, 15, 3);
		introGrid.add(choose_starter, 0, 4, 5, 1);
		introGrid.add(playerBox, 5, 4, 2, 1);
		introGrid.add(choose_opponent, 8, 4, 4, 1);
		introGrid.add(comboBox, 12, 4, 3, 1);
		introGrid.add(choose_minute, 0, 5, 5, 1);
		introGrid.add(spinner, 5, 5, 4, 1);
		introGrid.add(choose_second, 9, 5, 3, 1);
		introGrid.add(spinner2, 12, 5, 3, 1);
		introGrid.add(Launch, 10, 7, 5, 1);

		grid.add(Prompt, 0, 11, 6, 5);
		grid.add(undoBtn, 0, 10, 1, 1);
		grid.add(nextPlayer, 2, 10, 4, 1);
		grid.add(tokenX, 7, 10, 1, 1);
		grid.add(tokenO, 7, 11, 1, 1);
		grid.add(winner, 10, 8, 7, 1);
		grid.add(HvHBtn, 1, 13, 2, 1);
		grid.add(HvGBtn, 3, 13, 2, 1);
		grid.add(HvRBtn, 1, 14, 2, 1);
		grid.add(HvBBtn, 3, 14, 2, 1);
		grid.add(Hint, 10, 0, 2, 1);
		grid.add(Better, 13, 0, 4, 1);
		grid.add(GreedyHint, 10, 2, 3, 1);
		grid.add(RandomHint, 14, 2, 3, 1);
		grid.add(time, 10, 5, 3, 1);
		grid.add(time2, 14, 5, 3, 1);
		grid.add(p1, 10, 4, 3, 1);
		grid.add(p2, 14, 4, 3, 1);
		grid.add(restartBtn, 10, 7, 7, 1);

		model.setGrid(grid);
		
		// VIEW->CONTROLLER hookup
		GreedyHint.setOnAction(controller);
		RandomHint.setOnAction(controller);
		HvHBtn.setOnAction(controller);
		HvGBtn.setOnAction(controller);
		HvRBtn.setOnAction(controller);
		HvBBtn.setOnAction(controller);
		Hint.setOnAction(controller);
		Better.setOnAction(controller);
		undoBtn.setOnAction(controller);

		// MODEL->VIEW hookup
		model.attach(nextPlayer);
		model.attach(tokenX);
		model.attach(tokenO);
		model.attach(winner);
		model.attach(p1);
		model.attach(p2);
		model.attach(time);
		model.attach(time2);
		model.attach(GreedyHint);
		model.attach(RandomHint);
		model.attach(Better);
		model.attach(Hint);
		
		// SCENE
		sceneIntro = new Scene(introGrid);
		sceneIntro.getStylesheets().add("res/style.css");
		scene = new Scene(grid);
		scene.getStylesheets().add("res/style.css");

		// STAGE
		stage.setTitle("Othello");
		stage.setScene(sceneIntro);
		stage.setResizable(false);
		stage.setWidth(800);
		stage.setHeight(700);

		// LAUNCH THE GUI
		stage.show();
	}
}