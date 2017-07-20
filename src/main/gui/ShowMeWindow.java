package gui;

import conversions.Result;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
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

		Label inputLabel = new Label(ConvertWindow.getLastInputType().toString() + " Input: " + input);

		view = new WebView();
		engine = view.getEngine();
		engine.loadContent(String.format("<body><div style=\"text-align:center\" valign=\"center\">%s</div></body>",
				inputLabel.getText()));
		engine.setUserStyleSheetLocation(getClass().getResource("/resources/css/webview.css").toString());

		gridpane.setHgap(5);
		gridpane.setVgap(10);
		initTabs();
		gridpane.add(inputLabel, 0, 0, 4, 1);
		gridpane.add(view, 0, 2, 4, 1);
		GridPane.setHalignment(inputLabel, HPos.CENTER);
		GridPane.setHalignment(view, HPos.CENTER);

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

	/**
	 * Initialize buttons to switch between each conversion type.
	 */
	private void initTabs()
	{
		ConvertWindow.InputType[] enumArray = ConvertWindow.InputType.values();

		// Create a button for every possible input type
		for (int column = 0; column < enumArray.length; column++)
		{
			// set columns to resize as window gets resized
			ColumnConstraints cc = new ColumnConstraints();
			cc.setFillWidth(true);
			cc.setHgrow(Priority.ALWAYS);
			gridpane.getColumnConstraints().add(cc);

			// create buttons based on possible conversion types
			Button button = createButton(enumArray[column].toString());
			// disable button if the input type is the enum this button is for
			if (enumArray[column] == ConvertWindow.getLastInputType())
			{
				button.setDisable(true);
				showConversion(enumArray[column].toString());
			}

			// add button and center it inside its column
			gridpane.add(button, column, 1);
			GridPane.setHalignment(button, HPos.CENTER);
		}
	}

	/**
	 * Creates and returns a button with the text 'To' plus the string given in the
	 * parameter. Sets the onMouseClick call showConversion() with the string as the
	 * parameter.
	 * 
	 * @param string
	 * @return
	 */
	private Button createButton(String string)
	{
		Button button = new Button("To " + string);
		button.setOnMouseClicked(e ->
		{
			showConversion(string);
		});

		return button;
	}

	/**
	 * Converts the string parameter to a ConvertWindow.InputType enum. Then sets the
	 * webview content to the conversion from the user input to that enum.
	 * 
	 * @param type
	 */
	private void showConversion(String type)
	{
		String msg = "";
		switch (ConvertWindow.InputType.valueOf(type))
		{
			case Decimal:
				msg = result.getShowDecimal();
				break;
			case Binary:
				msg = result.getShowBinary();
				break;
			case Hexadecimal:
				msg = result.getShowHexadecimal();
				break;
			case Octal:
				msg = result.getShowOctal();
				break;
		}

		// put the conversion inside of the an HTML <body> and <p> so that
		// it can be formatted in CSS.
		engine.loadContent(String.format("<body><p>%s</p></body>", msg));
	}
}
