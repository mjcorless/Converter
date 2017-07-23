package main.gui;

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
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import main.conversions.Binary;
import main.conversions.Decimal;
import main.conversions.Hexadecimal;
import main.conversions.Numerical;
import main.conversions.Octal;
import main.conversions.Result;

/**
 * ConvertWindow creates the scene and action events for the convert window.
 * 
 * @author Matthew Corless
 */
public class ConvertWindow extends Stage
{
	private GridPane gridpane;
	private TextField decField, binField, octField, hexField;
	private Button showBtn;
	private Result result = null;
	private Label errorLbl;
	private Group decClearBtn, binClearBtn, hexClearBtn, octClearBtn;

	// the possible types of enums
	public enum InputType
	{
		Decimal, Binary, Hexadecimal, Octal;
	}

	// the type of input to convert
	public static InputType getLastInputType()
	{
		return lastInput;
	}

	private static InputType lastInput;

	private String input;

	/**
	 * Initialize the Converter Stage.
	 */
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
		this.getIcons().addAll(new Image("/main/resources/images/calculator64.png"),
				new Image("/main/resources/images/calculator32.png"),
				new Image("/main/resources/images/calculator64.png"));

		gridpane = new GridPane();
		initLabels();
		initTextFields();
		initButtons();

		gridpane.setAlignment(Pos.CENTER);
		gridpane.setVgap(3);
		Scene scene = new Scene(gridpane, 320, 225);
		scene.getStylesheets().add("/main/resources/css/convert.css");
		this.setScene(scene);
		this.setResizable(false);
		this.show();
	}

	/**
	 * Create Labels and add them to the GridPane
	 */
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
	 * Initialize the text fields and their action listeners.
	 */
	private void initTextFields()
	{
		InputType[] enumArray = InputType.values();
		TextField[] textArray = new TextField[enumArray.length];
		// Create a button for every possible input type
		for (int row = 0; row < enumArray.length; row++)
		{
			// create textfields based on possible conversion types
			textArray[row] = createTextField(enumArray[row]);

			// add button and center it inside its column
			gridpane.add(textArray[row], 1, row);
		}

		// be able to call each textfield directly without having to use the array index
		decField = textArray[0];
		binField = textArray[1];
		hexField = textArray[2];
		octField = textArray[3];
	}

	/**
	 * Creates the textfield for the given InputType. Sets the textfield's OnKeyPress and
	 * TextProperty Change Events
	 * 
	 * @param inputType
	 * @return
	 */
	private TextField createTextField(InputType inputType)
	{
		TextField textField = new TextField();
		textField.setTooltip(new Tooltip(String.format("%s Input", inputType.toString())));
		// when key is pressed, before the key is entered into textfield
		textField.setOnKeyPressed(e ->
		{
			lastInput = inputType;
		});
		// when textfield's text changes
		textField.textProperty().addListener((observable, oldText, newText) ->
		{
			// prevent other textfields getting their text set from causing a stackoverflow
			// only textfield user is typing into should call convertInput
			if (lastInput == inputType)
			{
				clearFields();
				convertInput();
			}
		});

		return textField;
	}

	/**
	 * Initialize buttons, their action listeners, and CSS.
	 */
	private void initButtons()
	{
		// --------------SHOW BUTTON---------------
		showBtn = new Button("Show Me!");
		showBtn.setPrefWidth(105);
		showBtn.getStyleClass().add("showBtn-enable");
		showBtn.setDisable(true);
		showBtn.setTooltip(new Tooltip("See how to do each conversion."));
		showBtn.setOnMouseClicked(e ->
		{
			ShowMeWindow showMeStage = new ShowMeWindow();
			showMeStage.start(input, result);
		});

		GridPane.setHalignment(showBtn, HPos.CENTER);
		gridpane.add(showBtn, 0, 5, 3, 1);

		// --------------Groups (clear buttons)--------------
		// use array to easily iterate through the for loop
		Group[] tempGrpArray = new Group[InputType.values().length];

		for (int row = 0; row < tempGrpArray.length; row++)
		{
			tempGrpArray[row] = getClearButton();
			tempGrpArray[row].setOnMouseClicked(e -> clearAll());
			tempGrpArray[row].setVisible(false);

			GridPane.setHalignment(tempGrpArray[row], HPos.RIGHT);
			gridpane.add(tempGrpArray[row], 1, row);
			// have a small gap between btn and the border of textfield
			GridPane.setMargin(tempGrpArray[row], new Insets(0, 5, 0, 0));
		}

		// be able to call these throughout the class without using an array index
		decClearBtn = tempGrpArray[0];
		binClearBtn = tempGrpArray[1];
		hexClearBtn = tempGrpArray[2];
		octClearBtn = tempGrpArray[3];
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
		// Change color on mouse over
		grp.setOnMouseEntered(e ->
		{
			circle.setFill(Color.web("#366E9B"));
			r1.setFill(Color.WHITE);
			r2.setFill(Color.WHITE);
		});
		// Reset change on mouse exit
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
	 * Clears every text field EXCEPT the text field that was last typed in. This prevents
	 * old conversions from showing while a new conversion is being entered. This also
	 * clears the error label when the character giving the error was deleted.
	 */
	private void clearFields()
	{
		errorLbl.setVisible(false);
		decClearBtn.setVisible(false);
		binClearBtn.setVisible(false);
		hexClearBtn.setVisible(false);
		octClearBtn.setVisible(false);

		// don't clear the textflield the user has typed in
		switch (lastInput)
		{
			case Binary:
				binClearBtn.setVisible(true);
				decField.clear();
				hexField.clear();
				octField.clear();
				break;
			case Hexadecimal:
				hexClearBtn.setVisible(true);
				decField.clear();
				binField.clear();
				octField.clear();
				break;
			case Octal:
				octClearBtn.setVisible(true);
				decField.clear();
				binField.clear();
				hexField.clear();
				break;
			default: // decimal
				decClearBtn.setVisible(true);
				binField.clear();
				hexField.clear();
				octField.clear();
				break;
		}
	}

	/**
	 * Clear all text fields, clear buttons, and error label.
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
		showBtn.setDisable(true);
		errorLbl.setVisible(false);
	}

	/**
	 * Changes the text fields to the converted form of the user-input
	 */
	private void convertInput()
	{
		try
		{
			// dont know which type numerical will be yet, have to initialize it so eclipse doesn't yell at me
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

			// if input was deleting or non alphanumeric
			if (input.equals(""))
			{
				clearAll();
			}
			else // actual input
			{
				if (numerical.isValid())
				{
					// valid input so get results and show show them
					result = new Result(numerical);
					setTextFields(result);
					showBtn.setDisable(false); // enable
				}
				else // create & show invalid error label
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
	 * Sets all textfields except the one that is the input.
	 * 
	 * @param result
	 *            The result of the conversions to display in the textfields
	 */
	private void setTextFields(Result result)
	{
		// Changing the text of the input textfield causes caret position issues. 
		// Manually altering the position caret causes highlighting issues.
		// workaround: only set the text of the textfields the user isn't typing into
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
	 *            True: prefix with 'Invalid input: '.
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
		showBtn.setDisable(true);
	}
}
