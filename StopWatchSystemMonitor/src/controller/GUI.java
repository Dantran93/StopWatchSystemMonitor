package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.StructureBuilder;
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
	
	private static final String WINDOW_TITLE  = "Stop Watch System Monitor";
	private static final String ICON_FILEPATH = "icons/active_icon.png";
	
	private static final String NO_SELECTED_FILE_TEXT   = "No File Selected";
	private static final String SELECT_CSV_BUTTON_TEXT  = "Select CSV File";
	private static final String FILE_CHOOSER_TITLE      = "Load CSV File";
	private static final String ITEM_NUMBER_COLUMN_TEXT = "Item Number Column";
	private static final String START_DATE_COLUMN_TEXT  = "Start Date Column";
	private static final String END_DATE_COLUMN_TEXT    = "End Date Column";
	private static final String CLEAR_BUTTON_TEXT       = "Clear";
	private static final String LAUNCH_BUTTON_TEXT      = "Launch";
	
	private static final int EXIT_WITH_NO_ERRORS = 0;
	
	
	
	private File selectedFile        = null;
	private BufferedReader csvReader = null;
	
	
	
	private Scene scene;
	
	private Pane root;
	private Pane graphPane;
	private Pane controlPane;
	
	private GraphView graphView;
	
	private Button selectCSVFileButton;
	private Text selectedFileText;
	
	private Text itemNumberText;
	private Text startDateColumnText;
	private Text endDateColumnText;
	
	private ComboBox<String> itemNumberColumnOptions;
	private ComboBox<String> startDateColumnOptions;
	private ComboBox<String> endDateColumnOptions;
	
	private Button launchButton;
	private Button clearButton;
	
	
	
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
		this.root.setPrefWidth(WINDOW_WIDTH);
		this.root.setPrefHeight(WINDOW_HEIGHT);
		this.root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		this.scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		primaryStage.setScene(scene);
		primaryStage.setWidth(WINDOW_WIDTH);
		primaryStage.setHeight(WINDOW_HEIGHT);
		primaryStage.setTitle(WINDOW_TITLE);
		primaryStage.setResizable(true);
		primaryStage.getIcons().add(new Image(new FileInputStream(ICON_FILEPATH)));
		primaryStage.setResizable(false);
		
		setCloseEvents(primaryStage);
		
		//addGraphPane();
		addControlPane();
		
		addGraphView();
		addItemNumberColumnSelection();
		addStartDateColumnSelection();
		addEndDateColumnSelection();
		addCSVFileSelection();
		addClearButton();
		addLaunchButton();
	} // initWindow()
	
	
	
	/**
	 * addControlPane()
	 * 
	 * Purpose:
	 */
	
	private void addControlPane ()
	{
		this.controlPane = new Pane();
		this.controlPane.setTranslateX(830);
		this.controlPane.setTranslateY(15);
		this.controlPane.setPrefWidth(250);
		this.controlPane.setPrefHeight(600);
		this.controlPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		this.root.getChildren().add(this.controlPane);
	} // addControlPane()
	
	
	
	/**
	 * addGraphView()
	 * 
	 * Purpose:
	 */
	
	private void addGraphView ()
	{
		GraphView canvas = new GraphView(800, 600);
		ScrollPane graphPane = new ScrollPane();
		graphPane.setTranslateX(15);
		graphPane.setTranslateY(15);
		graphPane.setPrefWidth(800);
		graphPane.setPrefHeight(600);
		graphPane.setContent(canvas);
		this.root.getChildren().add(graphPane);
	} // addGraphView()
	
	
	
	/**
	 * addCSVFileSelection()
	 * 
	 * Purpose:
	 */
	
	private void addCSVFileSelection ()
	{
		this.selectedFileText = new Text(NO_SELECTED_FILE_TEXT);
		this.selectedFileText.setTextAlignment(TextAlignment.CENTER);
		this.selectedFileText.setFont(Font.font(null, FontWeight.BOLD, 12));
		this.selectedFileText.setTranslateX(70);
		this.selectedFileText.setTranslateY(70);
		this.controlPane.getChildren().add(this.selectedFileText);
		
		this.selectCSVFileButton = new Button();
		this.selectCSVFileButton.setText(SELECT_CSV_BUTTON_TEXT);
		this.selectCSVFileButton.setPrefWidth(150);
		this.selectCSVFileButton.setPrefHeight(35);
		this.selectCSVFileButton.setTranslateX(50);
		this.selectCSVFileButton.setTranslateY(15);
		this.selectCSVFileButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle (ActionEvent event)
			{
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle(FILE_CHOOSER_TITLE);
				fileChooser.getExtensionFilters().add(new ExtensionFilter("CSV Files (.csv)", "*.csv"));
				File file = fileChooser.showOpenDialog(root.getScene().getWindow());
				if (file != null)
					selectedFile = file;
		    	 
				if (selectedFile != null)
				{
					selectedFileText.setText(selectedFile.getName());
					try {
						csvReader = new BufferedReader(new FileReader(selectedFile));
						int numColumns = StructureBuilder.readFileGetNumColumns(csvReader);
						String[] options = StructureBuilder.createOptions(numColumns);
						getItemNumberColumnOptions().getItems().setAll(options);
						getStartDateColumnOptions().getItems().setAll(options);
						getEndDateColumnOptions().getItems().setAll(options);
						getItemNumberColumnOptions().getSelectionModel().selectFirst();
						getStartDateColumnOptions().getSelectionModel().selectFirst();
						getEndDateColumnOptions().getSelectionModel().selectFirst();
					} catch (Exception e) { e.printStackTrace(); }
				}
			}
		});
		this.controlPane.getChildren().add(this.selectCSVFileButton);
	} // addCSVFileSelection()
	
	
	
	/**
	 * addItemNumberColumnSelection()
	 * 
	 * Purpose:
	 */
	
	private void addItemNumberColumnSelection ()
	{
		this.itemNumberText = new Text(ITEM_NUMBER_COLUMN_TEXT);
		this.itemNumberText.setTextAlignment(TextAlignment.CENTER);
		this.itemNumberText.setTranslateX(70);
		this.itemNumberText.setTranslateY(130);
		this.controlPane.getChildren().add(this.itemNumberText);
		
		ObservableList<String> options = FXCollections.observableArrayList();
		itemNumberColumnOptions = new ComboBox<String>(options);
		itemNumberColumnOptions.setPrefWidth(220);
		itemNumberColumnOptions.setTranslateX(15);
		itemNumberColumnOptions.setTranslateY(140);
		this.controlPane.getChildren().add(itemNumberColumnOptions);
	} // addItemNumberColumnSelection()
	
	
	
	/**
	 * addStartDateColumnSelection()
	 * 
	 * Purpose:
	 */
	
	private void addStartDateColumnSelection ()
	{
		this.startDateColumnText = new Text(START_DATE_COLUMN_TEXT);
		this.startDateColumnText.setTextAlignment(TextAlignment.CENTER);
		this.startDateColumnText.setTranslateX(75);
		this.startDateColumnText.setTranslateY(230);
		this.controlPane.getChildren().add(this.startDateColumnText);
		
		ObservableList<String> options = FXCollections.observableArrayList();
		startDateColumnOptions = new ComboBox<String>(options);
		startDateColumnOptions.setPrefWidth(220);
		startDateColumnOptions.setTranslateX(15);
		startDateColumnOptions.setTranslateY(240);
		this.controlPane.getChildren().add(startDateColumnOptions);
	} // addStartDateColumnSelection()
	
	
	
	/**
	 * addEndDateColumnSelection()
	 * 
	 * Purpose:
	 */
	
	private void addEndDateColumnSelection ()
	{
		this.endDateColumnText = new Text(END_DATE_COLUMN_TEXT);
		this.endDateColumnText.setTextAlignment(TextAlignment.CENTER);
		this.endDateColumnText.setTranslateX(80);
		this.endDateColumnText.setTranslateY(330);
		this.controlPane.getChildren().add(this.endDateColumnText);
		
		ObservableList<String> options = FXCollections.observableArrayList();
		endDateColumnOptions = new ComboBox<String>(options);
		endDateColumnOptions.setPrefWidth(220);
		endDateColumnOptions.setTranslateX(15);
		endDateColumnOptions.setTranslateY(340);
		this.controlPane.getChildren().add(endDateColumnOptions);
	} // addEndDateColumnSelection()
	
	
	
	/**
	 * getItemNumberColumnOptions()
	 * 
	 * Purpose:
	 */
	
	public ComboBox<String> getItemNumberColumnOptions ()
	{
		return itemNumberColumnOptions;
	} // getItemNumberColumnOptions()
	
	
	
	/**
	 * getStartDateColumnOptions()
	 * 
	 * Purpose:
	 */
	
	public ComboBox<String> getStartDateColumnOptions ()
	{
		return startDateColumnOptions;
	} // getStartDateColumnOptions()
	
	
	
	/**
	 * getEndDateColumnOptions()
	 * 
	 * Purpose:
	 */
	
	public ComboBox<String> getEndDateColumnOptions ()
	{
		return endDateColumnOptions;
	} // getEndDateColumnOptions()
	
	
	
	/**
	 * addLaunchButton()
	 * 
	 * Purpose:
	 */
	
	private void addLaunchButton ()
	{
		this.launchButton = new Button();
		this.launchButton.setText(LAUNCH_BUTTON_TEXT);
		this.launchButton.setPrefWidth(150);
		this.launchButton.setPrefHeight(35);
		this.launchButton.setTranslateX(50);
		this.launchButton.setTranslateY(450);
		this.launchButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle (ActionEvent event)
			{
				
			}
		});
		this.controlPane.getChildren().add(this.launchButton);
	} // addLaunchButton()
	
	
	
	/**
	 * addClearButton()
	 * 
	 * Purpose:
	 */
	
	private void addClearButton ()
	{
		this.clearButton = new Button();
		this.clearButton.setText(CLEAR_BUTTON_TEXT);
		this.clearButton.setPrefWidth(150);
		this.clearButton.setPrefHeight(35);
		this.clearButton.setTranslateX(50);
		this.clearButton.setTranslateY(550);
		this.clearButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle (ActionEvent event)
			{
				if (selectedFile != null)
				{
					selectedFile = null;
					selectedFileText.setText(NO_SELECTED_FILE_TEXT);
					itemNumberColumnOptions.getItems().clear();
					startDateColumnOptions.getItems().clear();
					endDateColumnOptions.getItems().clear();
				}
			}
		});
		this.controlPane.getChildren().add(this.clearButton);
	} // addClearButton()
	
	
	
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
				System.exit(EXIT_WITH_NO_ERRORS);
			}
		});
	} // setCloseEvents()
	
} // class GUI
