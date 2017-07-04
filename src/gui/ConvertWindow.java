package gui;




import conversions.*;
import generic.Tuple;
import javafx.application.Application;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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
	private Button showBtn; //, decClearBtn, binClearBtn, hexClearBtn, octClearBtn;
	private Label errorLbl;
	private Convert convert;
	private Tuple<String, String, String, String> result;
	private Group decClearBtn, binClearBtn, hexClearBtn, octClearBtn;
	
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
		//gridpane.setHgap(5);
		gridpane.setVgap(2);
		scene = new Scene(gridpane, 320, 220);
		scene.getStylesheets().add("/resources/css/convert.css");
		convertWindow.setScene(scene);
		//enableShowBtn();
		showBtn.requestFocus();
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
			
			decClearBtn.setVisible(true);
			binClearBtn.setVisible(false);
			hexClearBtn.setVisible(false);
			octClearBtn.setVisible(false);
			
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
			
			decClearBtn.setVisible(false);
			binClearBtn.setVisible(true);
			hexClearBtn.setVisible(false);
			octClearBtn.setVisible(false);
			
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
			
			decClearBtn.setVisible(false);
			binClearBtn.setVisible(false);
			hexClearBtn.setVisible(true);
			octClearBtn.setVisible(false);
			
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
			
			decClearBtn.setVisible(false);
			binClearBtn.setVisible(false);
			hexClearBtn.setVisible(false);
			octClearBtn.setVisible(true);
			
			convertInput();
		});
		gridpane.add(octField, 1, 3);
	}
	
	private void initButtons()
	{
		showBtn = new Button("Show Me!");
		showBtn.setPrefWidth(105);
		showBtn.getStyleClass().add("showBtn-enable");
		disableBtn(showBtn);
		GridPane.setHalignment(showBtn, HPos.CENTER);
		gridpane.add(showBtn, 0, 5, 3, 1);
		
		
		decClearBtn = getCloseButton();
		decClearBtn.setOnMouseClicked(e -> clearAll());
		decClearBtn.setVisible(false);
		
		binClearBtn = getCloseButton();
		binClearBtn.setOnMouseClicked(e -> clearAll());
		binClearBtn.setVisible(false);
		
		hexClearBtn = getCloseButton();
		hexClearBtn.setOnMouseClicked(e -> clearAll());
		hexClearBtn.setVisible(false);
		
		octClearBtn = getCloseButton();
		octClearBtn.setOnMouseClicked(e -> clearAll());
		octClearBtn.setVisible(false);
		
		gridpane.setHalignment(decClearBtn, HPos.RIGHT);
		gridpane.add(decClearBtn, 1, 0);
		GridPane.setMargin(decClearBtn, new Insets(0, 5, 0, 0)); // have a small gap between btn and the border of textfield
			
		gridpane.setHalignment(binClearBtn, HPos.RIGHT);
		gridpane.add(binClearBtn, 1, 1);
		GridPane.setMargin(binClearBtn, new Insets(0, 5, 0, 0)); // have a small gap between btn and the border of textfield
		
		gridpane.setHalignment(hexClearBtn, HPos.RIGHT);
		gridpane.add(hexClearBtn, 1, 2);
		GridPane.setMargin(hexClearBtn, new Insets(0, 5, 0, 0)); // have a small gap between btn and the border of textfield
		
		gridpane.setHalignment(octClearBtn, HPos.RIGHT);
		gridpane.add(octClearBtn, 1, 3);
		GridPane.setMargin(octClearBtn, new Insets(0, 5, 0, 0)); // have a small gap between btn and the border of textfield
	}
	
    private Group getCloseButton() 
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
 
    private Rectangle getCard() 
    {
        Rectangle card = new Rectangle(8, 2);
        card.setX(-4);
        card.setY(-1);
        card.setStrokeWidth(2.0);
        card.setFill(Color.web("#868686"));   
        return card;
    }

	/**
	 * Disables the button and changes the css
	 * style of the button to match
	 * @param btn
	 */
	private void disableBtn(Button btn)
	{
		btn.getStyleClass().add("btn-disabled"); 
		
		Image disabledIcon = new Image("/resources/images/No_Cross.png");
		ImageView iView = new ImageView(disabledIcon);
		Tooltip tooltip = new Tooltip("Enter a Valid Input");
		tooltip.setGraphic(iView);
		showBtn.setTooltip(tooltip);
		showBtn.setOnMouseClicked(null);
	}
	
	/**
	 * Enables the button and changes the css
	 * style of the button to match
	 * @param btn
	 */
	private void enableShowBtn()
	{
		//showBtn.setDisable(false); // gotta love a double negative
		showBtn.getStyleClass().removeAll("btn-disabled"); // remove method doesnt work (╯°□°）╯︵ 
		showBtn.getStyleClass().add("showBtn-enable");
		showBtn.setTooltip(new Tooltip("See how to do each conversion."));
		showBtn.setOnMouseClicked(e ->
		{
			
		});
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
		
		decClearBtn.setVisible(false);
		binClearBtn.setVisible(false);
		hexClearBtn.setVisible(false);
		octClearBtn.setVisible(false);
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
