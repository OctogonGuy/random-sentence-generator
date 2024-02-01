package tech.octopusdragon.randomsentencegenerator.gui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import tech.octopusdragon.randomsentencegenerator.components.Sentence;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Shows a GUI that allows the user to generate sentences.
 * @author Alex Gill
 *
 */
public class RandomSentenceGeneratorApplication extends Application {
	
	// Global constants
	private final double WIDTH = 720.0;			// Width of the window
	private final double HEIGHT = 200.0;		// Height of the window
	private final double PADDING = 20.0;		// Padding around the button
	private final double BUTTON_SIZE = 16.0;	// Generate button font size
	// Path to icon file
	private final static String ICON_PATH = "resources/images/icon.png";
	
	// GUI text
	private final String TITLE = "Random Sentence Generator";
	private final String PROMPT = "Click the button to generate a sentence.";
	private final String GENERATE_BUTTON_TEXT = "Generate";
	private final String FONT_TEXT = "Font";
	private final String RANDOM_FONT_TEXT = "Random";
	private final String SIZE_TEXT = "Size";
	private final String COLOR_TEXT = "Color";
	private final String RANDOM_COLOR_TEXT = "Random";
	private final String SETTINGS_TEXT = "Settings";
	private final String PREFERENCES_TEXT = "Preferences";
	
	// Fonts {display name, font}
	protected LinkedHashMap<String, String> fonts;
	private final String DEFAULT_FONT = "Arial";
	private final String GUI_FONT = "Arial";
	
	// Font sizes {display name, size}
	private LinkedHashMap<String, Double> sizes;
	private double SMALL_SIZE = 16.0;
	private double MEDIUM_SIZE = 28.0;
	private double LARGE_SIZE = 54.0;
	private final double DEFAULT_SIZE = SMALL_SIZE;
	
	// Colors {display name, color}
	private LinkedHashMap<String, String> colors;
	
	// GUI elements
	private Label sentenceLabel;	// Label to show the sentence
	private Button generateButton;	// Button to generate the sentence
	private MenuBar menuBar;		// Menu bar
	
	// Properties
	private BooleanProperty randomFont;		// Indicates random font
	private BooleanProperty randomColor;	// Indicates random color
	
	// Variables
	private String curFont;		// The current font
	private double curSize;	// The current font size
	
	@Override
	public void init() {
		
		// Initialize properties
		randomFont = new SimpleBooleanProperty();
		randomColor = new SimpleBooleanProperty();
		
		// Build fonts
		buildFonts();
		
		// Build sizes
		buildSizes();
		
		// Initialize colors
		buildColors();
		
		// Initialize current font and size
		curFont = DEFAULT_FONT;
		curSize = DEFAULT_SIZE;
	}
	
	@Override
	public void start(Stage primaryStage) {
		// Create the root
		BorderPane root = new BorderPane();
		
		// Create the menu
		menuBar = new MenuBar();
		
		Menu fontMenu = new Menu(FONT_TEXT);
		fontMenu.setStyle(String.format("-fx-font-family: \"%s\";", GUI_FONT));
		ToggleGroup fontToggleGroup = new ToggleGroup();
		for (Map.Entry<String, String> font : fonts.entrySet()) {
			RadioMenuItem item = new RadioMenuItem(font.getKey());
			item.setStyle(String.format("-fx-font-family: \"%s\";", font.getValue()));
			item.setOnAction(new ChangeFontHandler(font.getValue()));
			item.setToggleGroup(fontToggleGroup);
			fontMenu.getItems().add(item);
		}
		RadioMenuItem randomFontMenuItem = new RadioMenuItem(RANDOM_FONT_TEXT);
		randomFontMenuItem.setStyle(String.format("-fx-font-family: \"%s\";", GUI_FONT));
		randomFontMenuItem.setOnAction(new ChangeFontHandler());
		randomFontMenuItem.setToggleGroup(fontToggleGroup);
		randomFontMenuItem.setSelected(true);
		randomFont.bind(randomFontMenuItem.selectedProperty());
		fontMenu.getItems().addAll(new SeparatorMenuItem(), randomFontMenuItem);
		
		Menu sizeMenu = new Menu(SIZE_TEXT);
		sizeMenu.setStyle(String.format("-fx-font-family: \"%s\";", GUI_FONT));
		ToggleGroup sizeToggleGroup = new ToggleGroup();
		for (Map.Entry<String, Double> size : sizes.entrySet()) {
			RadioMenuItem item = new RadioMenuItem(size.getKey());
			item.setStyle(String.format("-fx-font-family: \"%s\";", GUI_FONT));
			item.setOnAction(new ChangeSizeHandler(size.getValue()));
			item.setToggleGroup(sizeToggleGroup);
			if (size.getValue() == DEFAULT_SIZE)
				item.setSelected(true);
			sizeMenu.getItems().add(item);
		}
		
		Menu colorMenu = new Menu(COLOR_TEXT);
		colorMenu.setStyle(String.format("-fx-font-family: \"%s\";", GUI_FONT));
		ToggleGroup colorToggleGroup = new ToggleGroup();
		for (Map.Entry<String, String> color : colors.entrySet()) {
			RadioMenuItem item = new RadioMenuItem(color.getKey());
			item.setStyle(String.format("-fx-font-family: \"%s\";", GUI_FONT) +
						  String.format("-fx-text-fill: %s;", color.getValue()));
			item.setOnAction(new ChangeColorHandler(color.getValue()));
			item.setToggleGroup(colorToggleGroup);
			colorMenu.getItems().add(item);
		}
		RadioMenuItem randomColorMenuItem = new RadioMenuItem(RANDOM_COLOR_TEXT);
		randomColorMenuItem.setStyle(String.format("-fx-font-family: \"%s\";", GUI_FONT));
		randomColorMenuItem.setOnAction(new ChangeColorHandler());
		randomColorMenuItem.setToggleGroup(colorToggleGroup);
		randomColorMenuItem.setSelected(true);
		randomColor.bind(randomColorMenuItem.selectedProperty());
		colorMenu.getItems().addAll(new SeparatorMenuItem(), randomColorMenuItem);
		
		Menu settingsMenu = new Menu(SETTINGS_TEXT);
		settingsMenu.setStyle(String.format("-fx-font-family: \"%s\";", GUI_FONT));
		MenuItem preferencesMenuItem = new MenuItem(PREFERENCES_TEXT);
		preferencesMenuItem.setStyle(String.format("-fx-font-family: \"%s\";", GUI_FONT));
		preferencesMenuItem.setOnAction(new PreferencesMenuHandler());
		settingsMenu.getItems().addAll(preferencesMenuItem);
		
		menuBar.getMenus().addAll(fontMenu, sizeMenu, colorMenu, settingsMenu);
		root.setTop(menuBar);
		
		// Create the sentence label
		sentenceLabel = new Label(PROMPT);
		sentenceLabel.setFont(new Font(DEFAULT_FONT, DEFAULT_SIZE));
		sentenceLabel.setStyle(String.format("-fx-text-fill: #000000;"));
		sentenceLabel.setWrapText(true);
		sentenceLabel.setTextAlignment(TextAlignment.CENTER);
		root.setCenter(sentenceLabel);
		
		// Create the generate button
		generateButton = new Button(GENERATE_BUTTON_TEXT);
		generateButton.setFont(new Font(GUI_FONT, BUTTON_SIZE));
		generateButton.setOnAction(new GenerateButtonHandler());
		BorderPane.setAlignment(generateButton, Pos.CENTER);
		BorderPane.setMargin(generateButton, new Insets(0.0, 0.0, PADDING, 0.0));
		root.setBottom(generateButton);
		
		// Create the scene and show it
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		primaryStage.setTitle(TITLE);
		primaryStage.getIcons().add(new Image(
				getClass().getClassLoader().getResourceAsStream(ICON_PATH)));
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * Changes the font of the sentence label.
	 * @param font The font to change to.
	 */
	public void changeFont(String font) {
		sentenceLabel.setFont(new Font(font, curSize));
		curFont = font;
	}
	
	/**
	 * Changes the font size of the sentence label.
	 * @param size The font size to change to.
	 */
	public void changeSize(double size) {
		sentenceLabel.setFont(new Font(curFont, size));
		curSize = size;
	}
	
	/**
	 * Changes the color of the sentence label.
	 * @param color The color to change to.
	 */
	public void changeColor(String color) {
		sentenceLabel.setStyle(String.format("-fx-text-fill: %s;", color));
	}
	
	
	public void buildFonts() {
		fonts = new LinkedHashMap<String, String>();
		fonts.put("Arial", "Arial");
		fonts.put("Calibri", "Calibri");
		fonts.put("Times New Roman", "Times New Roman");
		fonts.put("Helvetica", "Helvetica");
		fonts.put("Arial Rounded", "Arial Rounded MT Bold");
		fonts.put("Century Gothic", "Century Gothic");
		fonts.put("Gill Sans", "Gill Sans MT");
		fonts.put("Comic Sans", "Comic Sans MS");
		fonts.put("Lucida Sans", "Lucida Sans");
		fonts.put("Lucida Sans Typewriter", "Lucida Sans Typewriter");
		fonts.put("Lucida Handwriting", "Lucida Handwriting");
		fonts.put("Garamond", "Garamond");
		fonts.put("Tahoma", "Tahoma");
		fonts.put("Papyrus", "Papyrus");
	};
	
	public void buildColors() {
		colors = new LinkedHashMap<String, String>();
		colors.put("Black", "#000000");
		colors.put("Red", "#990000");
		colors.put("Orange", "#996600");
		colors.put("Yellow", "#999900");
		colors.put("Green", "#009900");
		colors.put("Blue", "#000099");
		colors.put("Violet", "#990099");
	}
	
	public void buildSizes() {
		sizes = new LinkedHashMap<String, Double>();
		sizes.put("Small", SMALL_SIZE);
		sizes.put("Medium", MEDIUM_SIZE);
		sizes.put("Large", LARGE_SIZE);
	}
	
	/**
	 * Changes the font of the sentence label when the user selects the option.
	 * @author Alex Gill
	 *
	 */
	public class ChangeFontHandler implements EventHandler<ActionEvent> {
		
		private String font;	// The font
		private boolean random;	// Whether to generate a random font
		
		/**
		 * This constructor applies a random font to the sentence label
		 */
		public ChangeFontHandler() {
			random = true;
		}
		
		/**
		 * This constructor applies a specific font to the sentence label
		 * @param font The font to apply to the sentence label
		 */
		public ChangeFontHandler(String font) {
			this.font = font;
		}
		
		@Override
		public void handle(ActionEvent event) {
			
			// Create Random object for generating random number
			Random rand = new Random();
			
			// If font is on random, change to a random font
			if (random) {
				List<String> fontList = new ArrayList<String>(fonts.values());
				changeFont(fontList.get(rand.nextInt(fontList.size())));
			}
			
			// Otherwise, change to the given font
			else
				changeFont(font);
		}
	}
	
	/**
	 * Changes the font size of the sentence label when the user selects the option.
	 * @author Alex Gill
	 *
	 */
	public class ChangeSizeHandler implements EventHandler<ActionEvent> {
		
		private double size;	// The size
		
		/**
		 * Constructor
		 * @param size The font size to apply to the sentence label
		 */
		public ChangeSizeHandler(double size) {
			this.size = size;
		}
		
		@Override
		public void handle(ActionEvent event) {
			changeSize(size);
		}
	}
	
	/**
	 * Changes the color of the sentence label when the user selects the option.
	 * @author Alex Gill
	 *
	 */
	public class ChangeColorHandler implements EventHandler<ActionEvent> {
		
		private String color;	// The color
		private boolean random;	// Whether to generate a random color
		
		/**
		 * This constructor applies a random color to the sentence label
		 */
		public ChangeColorHandler() {
			random = true;
		}
		
		/**
		 * This constructor applies a specific color to the sentence label
		 * @param color The color to apply to the sentence label
		 */
		public ChangeColorHandler(String color) {
			this.color = color;
		}
		
		@Override
		public void handle(ActionEvent event) {
			
			// Create Random object for generating random number
			Random rand = new Random();
			
			// If color is on random, change to a random color
			if (random) {
				List<String> colorList = new ArrayList<String>(colors.values());
				changeFont(colorList.get(rand.nextInt(colorList.size())));
			}
			
			// Otherwise, change to the indicated color
			else
				changeColor(color);
		}
	}
	
	/**
	 * Opens the preferences menu window to edit properties.
	 * @author Alex Gill
	 *
	 */
	public class PreferencesMenuHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			new PreferencesWindow().show();
		}
	}
	
	/**
	 * Generates a sentence when the user clicks on the generate button.
	 * @author Alex Gill
	 *
	 */
	public class GenerateButtonHandler implements EventHandler<ActionEvent> {
		
		@Override
		public void handle(ActionEvent event) {
			
			// Create Random object for generating random number
			Random rand = new Random();
			
			// If font is on random, change to random font
			if (randomFont.get()) {
				List<String> fontList = new ArrayList<String>(fonts.values());
				changeFont(fontList.get(rand.nextInt(fontList.size())));
			}
			
			// If color is on random, change to random color
			if (randomColor.get()) {
				List<String> colorList = new ArrayList<String>(colors.values());
				changeColor(colorList.get(rand.nextInt(colorList.size())));
			}
			
			// Generate sentence
			sentenceLabel.setText(generateSentence());
		}
		
	}
	
	
	/**
	 * Generates and returns a random sentence.
	 * @return A randomly generated sentence.
	 */
	public String generateSentence() {
		return new Sentence().toString();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
