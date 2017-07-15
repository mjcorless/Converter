package gui;

import conversions.Binary;
import conversions.Decimal;
import conversions.Hexadecimal;
import conversions.Numerical;
import conversions.Octal;
import conversions.Result;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * ConvertWindow creates the scene and action events for the convert window.
 * 
 * @author Matthew Corless
 */
public class ConvertWindow extends Stage
{
	// protected Stage convertWindow;
	// private Scene scene;
	private GridPane gridpane;
	private TextField decField, binField, octField, hexField;
	private Button showBtn;
	private Result result = null;
	private Label errorLbl;
	private Group decClearBtn, binClearBtn, hexClearBtn, octClearBtn;

	public enum InputType
	{
		Decimal, Binary, Hexadecimal, Octal;
	}

	public static InputType getLastInputType()
	{
		return lastInput;
	}

	private static InputType lastInput;

	public static String getInput()
	{
		return input;
	}

	private static String input;

	public ConvertWindow()
	{
		this.setTitle("Converter");
		/*
		 * I still don't understand how the icons in javafx work. This is from
		 * the swing implementation where I read to use multiple images so that
		 * it would choose the right one for the OS. However, if I only add one
		 * image, it works on my windows 10 regardless if it is the 16, 32, or
		 * 64 pixel version. When I add all three it chooses the 32 pixel
		 * version so apparently fx still defaults to the appropriate pixel
		 * version..
		 */
		this.getIcons().addAll(new Image("/resources/images/calculator64.png"),
				new Image("/resources/images/calculator32.png"), new Image("/resources/images/calculator64.png"));

		gridpane = new GridPane();
		initLabels();
		initTextFields();
		initButtons();

		gridpane.setAlignment(Pos.CENTER);
		// gridpane.setHgap(5);
		gridpane.setVgap(2);
		Scene scene = new Scene(gridpane, 320, 220);
		scene.getStylesheets().add("/resources/css/convert.css");
		this.setScene(scene);
		// enableShowBtn();
		// showBtn.requestFocus();
		this.setResizable(false);
		this.show();
	}

	private void initLabels()
	{
		Label decLbl = new Label("Decimal");
		gridpane.add(decLbl, 0, 0);
		Label binLbl = new Label("Binary");
		gridpane.add(binLbl, 0, 1);
		Label hexLbl = new Label("Hexadecimal");
		gridpane.add(hexLbl, 0, 2);
		Label octLbl = new Label("Octal");
		gridpane.add(octLbl, 0, 3);

		errorLbl = new Label();
		errorLbl.getStyleClass().add("error-label");
		GridPane.setHalignment(errorLbl, HPos.CENTER);
		gridpane.add(errorLbl, 0, 4, 2, 1);
	}

	/**
	 * Initialize the text fields and their action listeners. 6/4/2017 note: Dynamically
	 * updating the conversions when something was entered led to issues with the position
	 * caret. Manually changing caret position led to weird highlighting due to default
	 * anchor at the beginning of the textfield.
	 */
	private void initTextFields()
	{
		// constants for all textfields

		// Decimal TextField
		decField = new TextField();
		decField.setTooltip(new Tooltip("Decimal Input"));
		decField.setOnKeyPressed(e ->
		{
			lastInput = InputType.Decimal;
		});
		decField.textProperty().addListener((observable, oldText, newText) ->
		{
			if (lastInput == InputType.Decimal)
			{
				decClearBtn.setVisible(true);
				binClearBtn.setVisible(false);
				hexClearBtn.setVisible(false);
				octClearBtn.setVisible(false);
				clearFields();
				convertInput();
			}
		});
		gridpane.add(decField, 1, 0);

		// Binary TextField
		binField = new TextField();
		binField.setTooltip(new Tooltip("Binary Input"));
		binField.setOnKeyPressed(e ->
		{
			lastInput = InputType.Binary;
		});
		binField.textProperty().addListener((observable, oldText, newText) ->
		{
			if (lastInput == InputType.Binary)
			{
				decClearBtn.setVisible(false);
				binClearBtn.setVisible(true);
				hexClearBtn.setVisible(false);
				octClearBtn.setVisible(false);
				clearFields();
				convertInput();
			}
		});
		gridpane.add(binField, 1, 1);

		// Hexadecimal TextField
		hexField = new TextField();
		hexField.setTooltip(new Tooltip("Hexadecimal Input"));
		hexField.setOnKeyPressed(e ->
		{
			lastInput = InputType.Hexadecimal;
		});
		hexField.textProperty().addListener((observable, oldText, newText) ->
		{
			if (lastInput == InputType.Hexadecimal)
			{
				decClearBtn.setVisible(false);
				binClearBtn.setVisible(false);
				hexClearBtn.setVisible(true);
				octClearBtn.setVisible(false);
				clearFields();
				convertInput();
			}
		});
		gridpane.add(hexField, 1, 2);

		// Octal TextField
		octField = new TextField();
		octField.setTooltip(new Tooltip("Octal Input"));
		octField.setOnKeyPressed(e ->
		{
			lastInput = InputType.Octal;
		});
		octField.textProperty().addListener((observable, oldText, newText) ->
		{
			if (lastInput == InputType.Octal)
			{
				decClearBtn.setVisible(false);
				binClearBtn.setVisible(false);
				hexClearBtn.setVisible(false);
				octClearBtn.setVisible(true);

				clearFields();

				convertInput();
			}
		});
		gridpane.add(octField, 1, 3);

	}

	/**
	 * Initialize buttons, their action listeners, and CSS.
	 */
	private void initButtons()
	{
		showBtn = new Button("Show Me!");
		showBtn.setPrefWidth(105);
		showBtn.getStyleClass().add("showBtn-enable");
		disableShowBtn();
		GridPane.setHalignment(showBtn, HPos.CENTER);
		gridpane.add(showBtn, 0, 5, 3, 1);

		decClearBtn = getClearButton();
		decClearBtn.setOnMouseClicked(e -> clearAll());
		decClearBtn.setVisible(false);

		binClearBtn = getClearButton();
		binClearBtn.setOnMouseClicked(e -> clearAll());
		binClearBtn.setVisible(false);

		hexClearBtn = getClearButton();
		hexClearBtn.setOnMouseClicked(e -> clearAll());
		hexClearBtn.setVisible(false);

		octClearBtn = getClearButton();
		octClearBtn.setOnMouseClicked(e -> clearAll());
		octClearBtn.setVisible(false);

		GridPane.setHalignment(decClearBtn, HPos.RIGHT);
		gridpane.add(decClearBtn, 1, 0);
		// have a small gap between btn and the border of textfield
		GridPane.setMargin(decClearBtn, new Insets(0, 5, 0, 0));

		GridPane.setHalignment(binClearBtn, HPos.RIGHT);
		gridpane.add(binClearBtn, 1, 1);
		// have a small gap between btn and the border of textfield
		GridPane.setMargin(binClearBtn, new Insets(0, 5, 0, 0));

		GridPane.setHalignment(hexClearBtn, HPos.RIGHT);
		gridpane.add(hexClearBtn, 1, 2);
		// have a small gap between btn and the border of textfield
		GridPane.setMargin(hexClearBtn, new Insets(0, 5, 0, 0));

		GridPane.setHalignment(octClearBtn, HPos.RIGHT);
		gridpane.add(octClearBtn, 1, 3);
		// have a small gap between btn and the border of textfield
		GridPane.setMargin(octClearBtn, new Insets(0, 5, 0, 0));
	}

	/**
	 * Create the clear text field buttons. The button is just a group with a mouseover
	 * and onclick event.
	 * 
	 * @return
	 */
	private Group getClearButton()
	{
		Group grp = new Group();
		final Circle circle = new Circle();
		circle.setRadius(7.0);
		final Rectangle r1 = getCard();
		r1.setRotate(-45);
		final Rectangle r2 = getCard();
		r2.setRotate(45);
		grp.setOnMouseEntered(e ->
		{
			circle.setFill(Color.web("#097dda"));
			r1.setFill(Color.WHITE);
			r2.setFill(Color.WHITE);
		});
		grp.setOnMouseExited(e ->
		{
			circle.setFill(Color.web("#4c4c4c"));
			r1.setFill(Color.web("#868686"));
			r2.setFill(Color.web("#868686"));
		});
		grp.setVisible(true);
		circle.setFill(Color.web("#4c4c4c"));
		grp.getChildren().addAll(circle, r1, r2);
		return grp;
	}

	/**
	 * Creates the rectangles that form the clear button groups
	 * 
	 * @return
	 */
	private Rectangle getCard()
	{
		Rectangle rectangle = new Rectangle(8, 2);
		rectangle.setX(-4);
		rectangle.setY(-1);
		rectangle.setStrokeWidth(2.0);
		rectangle.setFill(Color.web("#868686"));
		return rectangle;
	}

	/**
	 * Disables the button and changes the css style of the button to match. This is
	 * similar to showBtn.setDisable(true) but allows for tooltips and custom css.
	 * 
	 * @param btn
	 */
	private void disableShowBtn()
	{
		showBtn.setDisable(true);
		Image disabledIcon = new Image("/resources/images/No_Cross.png");
		ImageView iView = new ImageView(disabledIcon);
		Tooltip tooltip = new Tooltip("Enter a Valid Input");
		tooltip.setGraphic(iView);
		showBtn.setTooltip(tooltip);
		showBtn.setOnMouseClicked(null);
	}

	/**
	 * Changes the css style, tooltip and onClick. Acts as showBtn.setDisable(false); but
	 * allows for css and tooltip change.
	 * 
	 * @param btn
	 */
	private void enableShowBtn()
	{
		showBtn.setDisable(false); // gotta love a double negative
		showBtn.setTooltip(new Tooltip("See how to do each conversion."));
		showBtn.setOnMouseClicked(e ->
		{
			ShowMeWindow showMeStage = new ShowMeWindow();
			showMeStage.start(input, result);
		});
	}

	/**
	 * Clears every text field EXCEPT the text field that was last typed in. This prevents
	 * old conversions from showing while a new conversion is being entered. This also
	 * clears the error label when the character giving the error was deleted.
	 */
	private void clearFields()
	{
		errorLbl.setVisible(false);
		switch (lastInput)
		{
			case Binary:
				decField.clear();
				hexField.clear();
				octField.clear();
				break;
			case Hexadecimal:
				decField.clear();
				binField.clear();
				octField.clear();
				break;
			case Octal:
				decField.clear();
				binField.clear();
				hexField.clear();
				break;
			default: // decimal
				binField.clear();
				hexField.clear();
				octField.clear();
				break;
		}
	}

	/**
	 * Clear all text fields
	 * 
	 * @return
	 */
	private void clearAll()
	{
		decField.clear();
		binField.clear();
		hexField.clear();
		octField.clear();

		decClearBtn.setVisible(false);
		binClearBtn.setVisible(false);
		hexClearBtn.setVisible(false);
		octClearBtn.setVisible(false);
		disableShowBtn();
		errorLbl.setVisible(false);
	}

	/**
	 * Changes the text fields to the converted form of the user-input
	 */
	private void convertInput()
	{
		try
		{
			Numerical numerical = null;

			switch (lastInput)
			{
				case Decimal:
					input = decField.getText();
					numerical = new Decimal(input);
					break;
				case Binary:
					input = binField.getText();
					numerical = new Binary(input);
					break;
				case Hexadecimal:
					input = hexField.getText();
					numerical = new Hexadecimal(input);
					break;
				case Octal:
					input = octField.getText();
					numerical = new Octal(input);
					break;
			}

			if (input.equals(""))
			{
				clearAll();
			}
			else
			{
				if (numerical.isValid())
				{
					result = numerical.convert();
					setTextFields(result);
					enableShowBtn();
				}
				else
				{
					invalid("Invalid " + numerical.getClass().getSimpleName() + " Input.", false);
				}
			}
		}
		catch (NumberFormatException e)
		{
			// e.printStackTrace();
			if (lastInput == InputType.Decimal)
			{
				invalid("max decimal value is 2147483647.", true);
			}
			else
			{
				invalid("Invalid " + lastInput.toString() + " Input.", false);
			}

		}
		catch (NullPointerException e)
		{
			System.out.println("NullPointerException in ConvertWindow");
			// e.printStackTrace();
			// invalid();
		}
	}

	/**
	 * Sets all textfields except the one that is the input. Changing the text of the
	 * input textfield causes caret position issues and manually altering the position
	 * caret causes highlighting issues.
	 * 
	 * @param result
	 */
	private void setTextFields(Result result)
	{
		switch (lastInput)
		{
			case Decimal:
				binField.setText(result.getBinary());
				hexField.setText(result.getHexadecimal());
				octField.setText(result.getOctal());
				break;
			case Binary:
				decField.setText(result.getDecimal());
				hexField.setText(result.getHexadecimal());
				octField.setText(result.getOctal());
				break;
			case Hexadecimal:
				decField.setText(result.getDecimal());
				binField.setText(result.getBinary());
				octField.setText(result.getOctal());
				break;
			case Octal:
				decField.setText(result.getDecimal());
				binField.setText(result.getBinary());
				hexField.setText(result.getHexadecimal());
				break;
		}
	}

	/**
	 * Creates error message for invalid inputs when attempting to convert.
	 * 
	 * @param errorText
	 *            The error label text.
	 * @param concatenate
	 *            True if preceded by 'Invalid input: '. False if
	 */
	private void invalid(String errorText, boolean concatenate)
	{
		if (concatenate)
		{
			errorLbl.setText("Invalid input: " + errorText);
		}
		else // custom invalid
		{
			errorLbl.setText(errorText);
		}
		errorLbl.setVisible(true);
		disableShowBtn();
	}
}
