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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
	private Button showBtn, decClearBtn, binClearBtn, hexClearBtn, octClearBtn;
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
		 * or 64 pixel version. When I add all three it chooses the 32 pixel version so apparently fx
		 * still defaults to the appropriate pixel version..
		 */
		convertWindow.getIcons().addAll(new Image("/resources/images/calculator64.png"),
				new Image("/resources/images/calculator32.png"), new Image("/resources/images/calculator64.png"));
				
	    gridpane = new GridPane();	
		initLabels();
		initTextFields();		
		initButtons();
		
		
		gridpane.setAlignment(Pos.CENTER);
		scene = new Scene(gridpane, 320, 200);
		scene.getStylesheets().add("/resources/css/convert.css");
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
		
		errorLbl = new Label();
		errorLbl.getStyleClass().add("error-label");
		GridPane.setHalignment(errorLbl, HPos.CENTER);
		gridpane.add(errorLbl, 0, 4, 2, 1);
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
		decField.setOnKeyReleased(e -> 
		{
			lastInput = Input.decimal;
			
			enableClearBtn(decClearBtn);
			disableBtn(binClearBtn);
			disableBtn(hexClearBtn);
			disableBtn(octClearBtn);
			
			convertInput();
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
		binField.setOnKeyReleased(e -> 
		{
			lastInput = Input.binary;
			
			enableClearBtn(binClearBtn);
			disableBtn(decClearBtn);
			disableBtn(hexClearBtn);
			disableBtn(octClearBtn);
			
			convertInput();
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
		hexField.setOnKeyReleased(e -> 
		{
			lastInput = Input.hexadecimal;
			
			enableClearBtn(hexClearBtn);
			disableBtn(decClearBtn);
			disableBtn(binClearBtn);
			disableBtn(octClearBtn);
			
			convertInput();
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
		octField.setOnKeyReleased(e -> 
		{
			lastInput = Input.octal;
			
			enableClearBtn(octClearBtn);
			disableBtn(decClearBtn);
			disableBtn(binClearBtn);
			disableBtn(hexClearBtn);
			
			convertInput();
		});
		gridpane.add(octField, 1, 3);
	}
	
	private void initButtons()
	{
		showBtn = new Button("Show Me!");
		showBtn.setPrefWidth(105);
		disableBtn(showBtn);
		GridPane.setHalignment(showBtn, HPos.CENTER);
		gridpane.add(showBtn, 0, 5, 3, 1);
		
		//VBox vbox = new VBox();
		
		decClearBtn = new Button("X");
		decClearBtn.setOnAction(e -> clearAll());
		disableBtn(decClearBtn);
		binClearBtn = new Button("X");
		binClearBtn.setOnAction(e -> clearAll());
		disableBtn(binClearBtn);
		hexClearBtn = new Button("X");
		hexClearBtn.setOnAction(e -> clearAll());
		disableBtn(hexClearBtn);
		octClearBtn = new Button("X");
		octClearBtn.setOnAction(e -> clearAll());
		disableBtn(octClearBtn);
		
		//vbox.getChildren().addAll(decClearBtn, binClearBtn, hexClearBtn, octClearBtn);
		//gridpane.add(vbox, 2, 0, 1, 4);
		gridpane.setHalignment(decClearBtn, HPos.LEFT);
		gridpane.add(decClearBtn, 2, 0);
		
		gridpane.setHalignment(binClearBtn, HPos.LEFT);
		gridpane.add(binClearBtn, 2, 1);
		
		gridpane.setHalignment(hexClearBtn, HPos.LEFT);
		gridpane.add(hexClearBtn, 2, 2);
		
		gridpane.setHalignment(octClearBtn, HPos.LEFT);
		gridpane.add(octClearBtn, 2, 3);
	}

	/**
	 * Disables the button and changes the css
	 * style of the button to match
	 * @param btn
	 */
	private void disableBtn(Button btn)
	{
		btn.getStyleClass().removeAll("showBtn-enable"); // remove method doesnt work (╯°□°）╯︵ 
		btn.getStyleClass().removeAll("clearBtn-enable");
		btn.getStyleClass().add("btn-disabled"); 
		btn.setDisable(true);
	}
	
	/**
	 * Enables the button and changes the css
	 * style of the button to match
	 * @param btn
	 */
	private void enableShowBtn()
	{
		showBtn.setDisable(false); // gotta love a double negative
		showBtn.getStyleClass().removeAll("btn-disabled"); // remove method doesnt work (╯°□°）╯︵ 
		showBtn.getStyleClass().add("showBtn-enable");
	}
	
	/**
	 * Enables the button and changes the css
	 * style of the button to match
	 * @param btn
	 */
	private void enableClearBtn(Button btn)
	{
		btn.setDisable(false); // gotta love a double negative
		btn.getStyleClass().removeAll("btn-disabled"); // remove method doesnt work (╯°□°）╯︵ 
		btn.getStyleClass().add("clearBtn-enable");
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
	 * Clear all text fields
	 * @return
	 */
	private void clearAll()
	{
		decField.clear();
		binField.clear();
		hexField.clear();
		octField.clear();
		
		disableBtn(decClearBtn);
		disableBtn(binClearBtn);
		disableBtn(hexClearBtn);
		disableBtn(octClearBtn);
		disableBtn(showBtn);
		errorLbl.setVisible(false);
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
		
		// don't try to convert if the field is empty (if the keypressed was the delete key)
		if (!input.equals(""))
		{			
			// **TODO** 6/12/2017
			// clean this up, cannot set the text field of the one the user types in or else
			// the cursor moves to the front of the textfield, if you manually move the cursor/caret,
			// the text gets highlighted and I have not found a way to avoid that
			try
			{
				convert = Convert.getInstance();
				
				if (convert.convertMe(type, input))
				{
					switch (lastInput)
					{
						case binary:
							decField.setText(convert.getDecimal());
							hexField.setText(convert.getHexadecimal());
							octField.setText(convert.getOctal());
							break;
						case hexadecimal:
							decField.setText(convert.getDecimal());
							binField.setText(convert.getBinary());
							octField.setText(convert.getOctal());
							break;
						case octal:
							decField.setText(convert.getDecimal());
							binField.setText(convert.getBinary());
							hexField.setText(convert.getHexadecimal());
							break;
						default:
							binField.setText(convert.getBinary());
							hexField.setText(convert.getHexadecimal());
							octField.setText(convert.getOctal());
							break;
					}
					enableShowBtn();
					errorLbl.setVisible(false);
				}
				else	
				{
					invalid(convert.getError(), false);
				}
			}
			catch (NumberFormatException e)
			{		
				System.out.println("NumberFormatException in ConvertWindow");
				//e.printStackTrace();
				if (lastInput == Input.decimal)
				{
					invalid("max decimal value is 2147483647.", true);
				}
			}
			catch (NullPointerException e)
			{
				System.out.println("NullPointerException in ConvertWindow");
				//e.printStackTrace();
				//invalid();
			}
		}
		else // input == ""
		{
			clearAll();
		}
		

	}
	
	/**
	 * Creates error message for invalid inputs when attempting to convert.
	 * 
	 * @param errorText The error label text.
	 * @param concatenate True if preceded by 'Invalid input: '. False if
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
		disableBtn(showBtn);
	}
	
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
}
