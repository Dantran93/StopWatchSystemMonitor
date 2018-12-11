package controller;

import java.io.File;
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import view.GraphView;

/**
 * GUI.java
 * Author: Daniel Tranfaglia
 * 
 * Purpose:
 */

public class GUI extends Application
{
	private static final double WINDOW_WIDTH  = 1100.0;
	private static final double WINDOW_HEIGHT = 660.0;
	
	private static final String WINDOW_TITLE = "Stop Watch System Monitor";
	private static final String ICON_FILEPATH = "icons/active_icon.png";
	
	private static final int NO_ERRORS = 0;
	
	
	
	private File selectedFile = null;
	
	
	private Pane root;
	private Scene scene;
	
	private GraphView graphView;
	
	private Button selectCSVFileButton;
	private Text selectedFileText;
	
	private Text itemNumberText;
	private ComboBox<String> itemNumberColumnOptions;
	
	private Text startDateColumnText;
	private ComboBox<String> startDateColumnOptions;
	
	private Text endDateColumnText;
	private ComboBox<String> endDateColumnOptions;
	
	private Button launchButton;
	
	
	
	
	/**
	 * main()
	 * 
	 * Purpose: Launches the application.
	 */
	
	public static void main (String[] args)
	{
		launch(args);
	} // main()
	
	
	
	/**
	 * start()
	 * 
	 * Purpose: Starts the game at the initial state.
	 */
	
	@Override
	public void start (Stage primaryStage) throws Exception
	{
		initWindow(primaryStage);
		
		
		
		primaryStage.show();
	} // start()
	
	
	
	/**
	 * initWindow()
	 * 
	 * Purpose: Initializes the game window.
	 */
	
	private void initWindow (Stage primaryStage) throws Exception
	{
		this.root = new Pane();
		this.scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		primaryStage.setScene(scene);
		primaryStage.setWidth(WINDOW_WIDTH);
		primaryStage.setHeight(WINDOW_HEIGHT);
		primaryStage.setTitle(WINDOW_TITLE);
		primaryStage.setResizable(true);
		primaryStage.getIcons().add(new Image(new FileInputStream(ICON_FILEPATH)));
		primaryStage.setResizable(false);
		primaryStage.getScene().setFill(Color.CORNFLOWERBLUE);
		
		setCloseEvents(primaryStage);
		Pane frame = new Pane();
		this.scene.setRoot(frame);
		
		addGraphView(frame);
		addItemNumberColumnSelection(frame);
		addStartDateColumnSelection(frame);
		addEndDateColumnSelection(frame);
		addCSVFileSelection(frame);
		addLaunchButton(frame);
	} // initWindow()
	
	
	
	/*
	 * 
	 */
	
	private void addGraphView (Pane frame)
	{
		this.graphView = new GraphView();
		this.graphView.setPrefWidth(800);
		this.graphView.setPrefHeight(600);
		this.graphView.setTranslateX(15);
		this.graphView.setTranslateY(15);
		frame.getChildren().add(this.graphView);
		
	}
	
	
	
	private void addCSVFileSelection (Pane frame)
	{
		this.selectedFileText = new Text("*** No File Selected ***");
		this.selectedFileText.setTextAlignment(TextAlignment.CENTER);
		this.selectedFileText.setTranslateX(896);
		this.selectedFileText.setTranslateY(95);
		frame.getChildren().add(this.selectedFileText);
		
		this.selectCSVFileButton = new Button();
		this.selectCSVFileButton.setText("Select CSV File");
		this.selectCSVFileButton.setPrefWidth(150);
		this.selectCSVFileButton.setPrefHeight(35);
		this.selectCSVFileButton.setTranslateX(880);
		this.selectCSVFileButton.setTranslateY(40);
		this.selectCSVFileButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle (ActionEvent event)
			{
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Data File");
				fileChooser.getExtensionFilters().add(new ExtensionFilter("CSV Files (.csv)", "*.csv"));
				selectedFile = fileChooser.showOpenDialog(frame.getScene().getWindow());
		    	 
				if (selectedFile != null)
					selectedFileText.setText(selectedFile.getName());
				else
					selectedFileText.setText("*** No File Selected ***");
			}
		});
		frame.getChildren().add(this.selectCSVFileButton);
	}
	
	
	
	private void addItemNumberColumnSelection (Pane frame)
	{
		this.itemNumberText = new Text("Item Number Column");
		this.itemNumberText.setTextAlignment(TextAlignment.CENTER);
		this.itemNumberText.setTranslateX(897);
		this.itemNumberText.setTranslateY(160);
		frame.getChildren().add(this.itemNumberText);
		
		ObservableList<String> options = FXCollections.observableArrayList();
		itemNumberColumnOptions = new ComboBox<String>(options);
		itemNumberColumnOptions.setPrefWidth(220);
		itemNumberColumnOptions.setTranslateX(850);
		itemNumberColumnOptions.setTranslateY(170);
		frame.getChildren().add(itemNumberColumnOptions);
	}
	
	
	
	
	private void addStartDateColumnSelection (Pane frame)
	{
		this.startDateColumnText = new Text("Start Date Column");
		this.startDateColumnText.setTextAlignment(TextAlignment.CENTER);
		this.startDateColumnText.setTranslateX(897);
		this.startDateColumnText.setTranslateY(240);
		frame.getChildren().add(this.startDateColumnText);
		
		ObservableList<String> options = FXCollections.observableArrayList();
		startDateColumnOptions = new ComboBox<String>(options);
		startDateColumnOptions.setPrefWidth(220);
		startDateColumnOptions.setTranslateX(850);
		startDateColumnOptions.setTranslateY(250);
		frame.getChildren().add(startDateColumnOptions);
	}
	
	
	
	private void addEndDateColumnSelection (Pane frame)
	{
		this.endDateColumnText = new Text("End Date Column");
		this.endDateColumnText.setTextAlignment(TextAlignment.CENTER);
		this.endDateColumnText.setTranslateX(897);
		this.endDateColumnText.setTranslateY(320);
		frame.getChildren().add(this.endDateColumnText);
		
		ObservableList<String> options = FXCollections.observableArrayList();
		endDateColumnOptions = new ComboBox<String>(options);
		endDateColumnOptions.setPrefWidth(220);
		endDateColumnOptions.setTranslateX(850);
		endDateColumnOptions.setTranslateY(330);
		frame.getChildren().add(endDateColumnOptions);
	}
	
	
	
	
	
	
	
	private void addLaunchButton (Pane frame)
	{
		this.launchButton = new Button();
		this.launchButton.setText("Launch");
		this.launchButton.setPrefWidth(150);
		this.launchButton.setPrefHeight(35);
		this.launchButton.setTranslateX(880);
		this.launchButton.setTranslateY(530);
		this.launchButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle (ActionEvent event)
			{
				// Nothing yet
			}
		});
		frame.getChildren().add(this.launchButton);
	}
	
	
	
	
	
	
	
	
	
	/**
	 * setCloseEvents()
	 * 
	 * Purpose: Sets the events that happen when the game is closed.
	 */
	
	private void setCloseEvents (Stage primaryStage)
	{
		primaryStage.setOnHiding(new EventHandler<WindowEvent>()
		{
			@Override
			public void handle (WindowEvent e)
			{
				System.exit(NO_ERRORS);
			}
		});
	} // setCloseEvents()
	
} // class GUI
