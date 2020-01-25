package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class OthelloApplication extends Application {

	OthelloModel model;
	View view;

	@Override
	public void start(Stage stage) throws Exception {
		// Create and hook up the Model, View and the controller

		// MODEL
		this.model = new OthelloModel();
		this.view = new View(model, stage);

	}

	public static void main(String[] args) {
		launch(args);
	}
}
