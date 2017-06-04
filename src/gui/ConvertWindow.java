package gui;


import conversions.*;
import generic.Tuple;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * ConvertWindow creates the scene and action events for the convert window.
 * @author Matthew Corless
 *
 */
public class ConvertWindow extends Application
{
	protected Stage convertWindow;
	private Scene scene;
	private GridPane gridpane;
	private TextField decField, binField, octField, hexField;
	private Button convertBtn, showBtn;
	private Label errorLbl;
	private Convert convert;
	private Tuple<String, String, String, String> result;
	
	enum Input 
	{
		decimal,
		binary,
		hexadecimal,
		octal;
	}
	private Input lastInput;
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		convertWindow = primaryStage;
		convertWindow.setTitle("Converter");
		/*
		 * I still don't understand how the icons in javafx work. This is from the swing implementation
		 * where I read to use multiple images so that it would choose the right one for the OS.
		 * However, if I only add one image, it works on my windows 10 regardless if it is the 16, 32, 
		 * or 64 pixel version. When I add all three it chooses the 32 pixel version so apparently this
		 * still defaults to the appropriate pixel version..
		 */
		convertWindow.getIcons().addAll(new Image("/resources/images/calculator64.png"),
				new Image("/resources/images/calculator32.png"), new Image("/resources/images/calculator64.png"));
		
		
		
	     gridpane = new GridPane();
	     /*
	     for (int i = 0; i < 2; i++) {
	         ColumnConstraints column = new ColumnConstraints(100);
	         gridpane.getColumnConstraints().add(column);
	     }
	     */
		
		
		initLabels();
		initTextFields();
		
		errorLbl = new Label();
		GridPane.setHalignment(errorLbl, HPos.CENTER);
		gridpane.add(errorLbl, 0, 4, 2, 1);
		
		convertBtn = new Button("Convert");
		GridPane.setHalignment(convertBtn, HPos.CENTER);
		convertBtn.setOnAction(e -> convertInput());
		gridpane.add(convertBtn, 0, 5);
		
		
		showBtn = new Button("Show Me!");
		showBtn.setDisable(true);
		GridPane.setHalignment(showBtn, HPos.CENTER);
		gridpane.add(showBtn, 1, 5);
		
		
		gridpane.setAlignment(Pos.CENTER);
		scene = new Scene(gridpane, 250, 150);
		//convertBtn.requestFocus();
		convertWindow.setScene(scene);
		convertWindow.setResizable(false);
		convertWindow.show();
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
	}
	
	/**
	 * Initialize the text fields and their action listeners.
	 * 
	 * 6/4/2017 note: Dynamically updating the conversions when something was entered led to issues
	 * with the position caret. Manually changing caret position led to weird highlighting due to
	 * default anchor at the beginning of the textfield.
	 */
	private void initTextFields()
	{
		// constants for all textfields
		
		// Decimal TextField
		decField = new TextField();
		decField.setTooltip(new Tooltip("Decimal Input"));
		decField.setOnKeyPressed(e -> 
		{
			lastInput = Input.decimal;
			clearFields();
		});
		gridpane.add(decField, 1, 0);
		
		// Binary TextField
		binField = new TextField();
		binField.setTooltip(new Tooltip("Binary Input"));
		binField.setOnKeyPressed(e -> 
		{
			lastInput = Input.binary;
			clearFields();
		});
		gridpane.add(binField, 1, 1);
		
		// Hexadecimal TextField
		hexField = new TextField();
		hexField.setTooltip(new Tooltip("Hexadecimal Input"));
		hexField.setOnKeyPressed(e -> 
			{
				lastInput = Input.hexadecimal;
				clearFields();
			});
		gridpane.add(hexField, 1, 2);
		
		// Octal TextField
		octField = new TextField();
		octField.setTooltip(new Tooltip("octal input"));
		octField.setOnKeyPressed(e -> 
		{
			lastInput = Input.octal;
			clearFields();
		});
		gridpane.add(octField, 1, 3);
	}
	
	/**
	 * Clears every text field EXCEPT the text field
	 * that was last typed in. This prevents old
	 * conversions from showing while a new conversion
	 * is being entered.
	 */
	private void clearFields()
	{
		switch (lastInput)
		{
			case binary:
				decField.clear();
				hexField.clear();
				octField.clear();
				break;
			case hexadecimal:
				decField.clear();
				binField.clear();
				octField.clear();
				break;
			case octal:
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
	 * Changes the text fields to the converted form of the user-input
	 */
	private void convertInput()
	{
		String input;
		Convert.type type;
		
		switch (lastInput)
		{
			case binary:
				input = binField.getText();
				type = Convert.type.Binary;
				break;
			case hexadecimal:
				input = hexField.getText();
				type = Convert.type.Hexadecimal;
				break;
			case octal:
				input = octField.getText();
				type = Convert.type.Octal;
				break;
			default:
				input = decField.getText();
				type = Convert.type.Decimal;
				break;
		}
		
		try
		{
			convert = Convert.getInstance();
			
			if (convert.convertMe(type, input))
			{
				decField.setText(convert.decimal);
				binField.setText(convert.binary);
				hexField.setText(convert.hexadecimal);
				octField.setText(convert.octal);
				showBtn.setDisable(false); // gotta love a double negative
				errorLbl.setVisible(false);
			}
			else	
			{
				invalid();
			}
		}
		catch (NumberFormatException e)
		{		
				invalid("max decimal value is 2147483647.");
		}
		catch (NullPointerException e)
		{
			invalid();
		}
	}
	
	/**
	 * Creates error message for invalid inputs when attempting to convert
	 * @param s string that the user attempted to convert
	 */
	private void invalid()
	{
		errorLbl.setVisible(true);
		errorLbl.setText("Invalid input");
	}
	
	/**
	 * Creates error message for invalid inputs when attempting to convert
	 * @param s string that the user attempted to convert
	 */
	private void invalid(String s)
	{
		errorLbl.setVisible(true);
		errorLbl.setText("Invalid input: " + s);
	}
	
	
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
}
