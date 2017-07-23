package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.gui.ConvertWindow;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		new ConvertWindow();
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}
