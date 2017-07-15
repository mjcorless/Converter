package gui;

import conversions.Result;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class ShowMeWindow extends Stage
{
	private GridPane gridpane;
	private Result result;
	private WebView view;
	private WebEngine engine;
	private static double xLast; // remember x location
	private static double yLast; // remember y location
	private static double xCurrent; // current x if there is a pre-existing showMeWindow
	private static double yCurrent; // current y if there is a pre-existing showMeWindow
	private static boolean firstIteration = true;

	public void start(String input, Result conversion)
	{
		this.setTitle("Show Me!");
		this.getIcons().addAll(new Image("/resources/images/calculator64.png"),
				new Image("/resources/images/calculator32.png"), new Image("/resources/images/calculator64.png"));

		result = conversion;

		gridpane = new GridPane();

		// swing label for html content
		Label inputLabel = new Label(
				ConvertWindow.getLastInputType().toString() + " Input: " + ConvertWindow.getInput());
		gridpane.add(inputLabel, 0, 0, 4, 1);
		GridPane.setHalignment(inputLabel, HPos.CENTER);

		view = new WebView();
		engine = view.getEngine();
		engine.loadContent(String.format(
				"<body bgcolor=\"#9FB8C5\" ><div style=\"text-align:center\" valign=\"center\">%s</div></body>",
				inputLabel.getText()));
		//view.setPrefHeight(440);
		//view.setPrefWidth(450);
		gridpane.add(initTabs(), 0, 1, 4, 1);

		gridpane.add(view, 0, 2, 4, 1);
		GridPane.setHalignment(view, HPos.CENTER);

		gridpane.setAlignment(Pos.TOP_CENTER);
		gridpane.setHgap(5);
		gridpane.setVgap(10);
		Scene scene = new Scene(gridpane, 550, 550);
		scene.getStylesheets().add("/resources/css/showMe.css");
		this.setScene(scene);

		double x = 0;
		double y = 0;
		if (!firstIteration)
		{
			x = (xLast != 0.0) ? xLast : xCurrent + 100;
			y = (yLast != 0.0) ? yLast : yCurrent + 50;
		}

		this.setX(x);
		this.setY(y);
		xCurrent = x;
		yCurrent = y;
		// remember closing location
		this.setOnCloseRequest(e ->
		{
			ShowMeWindow.xLast = this.getX();
			ShowMeWindow.yLast = this.getY();
			xCurrent = 0.0;
			yCurrent = 0.0;
		});
		this.xProperty().addListener((obs, oldVal, newVal) ->
		{
			xCurrent = (double) newVal;
		});
		this.yProperty().addListener((obs, oldVal, newVal) ->
		{
			yCurrent = (double) newVal;
		});

		firstIteration = false;

		this.setMinWidth(450);
		//this.setResizable(false);
		this.show();
	}

	private HBox initTabs()
	{
		HBox hbox = new HBox();
		hbox.setSpacing(10);

		// Convert convert = Convert.getInstance();

		Button toDecimalBtn = new Button("To Decimal");
		toDecimalBtn.setOnMouseClicked(e ->
		{
			showConversion(result.getShowDecimal());
		});

		Button toBinaryBtn = new Button("To Binary");
		toBinaryBtn.setOnMouseClicked(e ->
		{
			showConversion(result.getShowBinary());
		});

		Button toHexadecimalBtn = new Button("To Hexadecimal");
		toHexadecimalBtn.setOnMouseClicked(e ->
		{
			showConversion(result.getShowHexadecimal());
		});

		Button toOctalBtn = new Button("To Octal");
		toOctalBtn.setOnMouseClicked(e ->
		{
			showConversion(result.getShowOctal());
		});

		hbox.getChildren().addAll(toDecimalBtn, toBinaryBtn, toHexadecimalBtn, toOctalBtn);

		switch (ConvertWindow.getLastInputType())
		{
			// disable the button for conversion to input
			// show that conversion as default webview
			case Decimal:
				toDecimalBtn.setDisable(true);
				showConversion(result.getShowDecimal());
				break;
			case Binary:
				toBinaryBtn.setDisable(true);
				showConversion(result.getShowBinary());
				break;
			case Hexadecimal:
				toHexadecimalBtn.setDisable(true);
				showConversion(result.getShowHexadecimal());
				break;
			case Octal:
				toOctalBtn.setDisable(true);
				showConversion(result.getShowOctal());
				break;
		}

		return hbox;
	}

	private void showConversion(String msg)
	{
		// Document doc = engine.getDocument();
		// Element el = doc.getElementById("content");
		// String s = el.getTextContent();
		engine.loadContent(String.format("<body bgcolor=\"#9FB8C5\"><div id='content'>%s</div></body>", msg));
		// el.setTextContent(msg);
	}
}
