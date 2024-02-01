package tech.octopusdragon.randomsentencegenerator.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import tech.octopusdragon.randomsentencegenerator.util.Util;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Opens up a window that allows the user to edit properties.
 * @author Alex Gill
 *
 */
public class PreferencesWindow extends Stage {
	
	// --- Constants ---
	private static final double WIDTH = 500.0;	// Width of the window
	private static final double HEIGHT = 500.0;	// Height of the window
	private static final double PADDING = 10.0;	// Padding
	private static final double SPACING = 10.0;	// Spacing
	
	
	// --- GUI Components ---
	GridPane entryBox;			// Box with all entries
	List<EntryGraphic> entries;	// List of entries
	HBox buttonBox;				// Box with buttons
	Button applyButton;			// Applies changes
	Button applyCloseButton;	// Applies changes and closes window
	Button defaultButton;		// Restores settings to defaults
	Button closeButton;			// Closes window

	
	
	/**
	 * Instantiates a new preferences window
	 */
	public PreferencesWindow() {
		super();
		
		
		// Create the root node
		BorderPane root = new BorderPane();
		
		
		// Create a VBox with entries for the central node
		entryBox = new GridPane();
		entryBox.setPadding(new Insets(PADDING));
		entryBox.setHgap(SPACING);
		entryBox.setVgap(SPACING);
		entries = new ArrayList<EntryGraphic>();
		int entryIndex = 0;
		for (Object key: Util.getDefaultProperties().keySet()) {
			String skey = (String)key;
			
			EntryGraphic newEntryGraphic = new EntryGraphic(skey);
			entries.add(newEntryGraphic);
			
			entryBox.add(newEntryGraphic.getKeyLabel(), 0, entryIndex);
			entryBox.add(newEntryGraphic.getValueTextField(), 1, entryIndex);
			
			entryIndex++;
		}
		
		
		// Put the VBox in a scroll pane so it can be scrolled
		ScrollPane entryPane = new ScrollPane(entryBox);
		root.setCenter(entryPane);
		
		
		// Create an HBox with buttons for the bottom node
		applyButton = new Button("Apply");
		applyButton.setOnAction(event -> {
			apply();
		});
		applyCloseButton = new Button("Apply and Close");
		applyCloseButton.setOnAction(event -> {
			apply();
			this.close();
		});
		defaultButton = new Button("Restore Defaults");
		defaultButton.setOnAction(event -> {
			defaults();
		});
		closeButton = new Button("Close");
		closeButton.setOnAction(event -> {
			this.close();
		});
		HBox buttonBox = new HBox(
				applyButton, applyCloseButton, defaultButton, closeButton);
		buttonBox.setAlignment(Pos.CENTER_RIGHT);
		buttonBox.setPadding(new Insets(PADDING));
		buttonBox.setSpacing(SPACING);
		root.setBottom(buttonBox);
		
		
		// Set the scene and show the stage
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		this.setScene(scene);
		this.setTitle("Preferences");
		this.show();
	}
	
	
	
	/**
	 * Sets the property values and saves them to the application property file.
	 */
	protected void apply() {
		
		// Set the property values.
		Properties properties = Util.getProperties();
		for (EntryGraphic entry: entries) {
			properties.setProperty(entry.getKey(), entry.getValue());
		}
	}
	
	
	
	/**
	 * Restores the property values to the values in the default property file.
	 * Saves these values to the application property file.
	 */
	protected void defaults() {
		
		// Set the text of text fields.
		Properties defaultProps = Util.getDefaultProperties();
		for (EntryGraphic entry: entries) {
			String defaultValue = defaultProps.getProperty(entry.getKey());
			entry.getValueTextField().setText(defaultValue);
		}
	}
	
	
	
	/**
	 * Contains an entry, a label showing the key, and a text field for the
	 * value.
	 * @author Alex Gill
	 *
	 */
	private class EntryGraphic {
		
		private String key;						// The key
		private Label keyLabel;					// The key label
		private TextField valueTextField;		// The value text field
		
		
		/**
		 * Instantiates a new entry graphic
		 * @param entry An entry with a key and value
		 */
		public EntryGraphic(String key) {
			super();
			this.key = key;
			
			// Create the label
			this.keyLabel = new Label(formattedKey());
			
			// Create the text field
			this.valueTextField = new TextField(
					Util.getProperties().getProperty(key));
		}
		
		
		/**
		 * Returns a formatted string representation of the key.
		 * @return A formatted string representation of the key
		 */
		public String formattedKey() {
			StringBuilder sb = new StringBuilder();
			
			// Capitalize the first letter
			sb.append(key.substring(0, 1).toUpperCase());
			
			// Keep adding letters. If a capital is encountered, insert space
			for (int i = 1; i < key.length(); i++) {
				char ch = key.charAt(i);
				if (Character.isUpperCase(ch)) {
					sb.append(" ");
				}
				sb.append(ch);
			}
			
			return sb.toString();
		}
		
		
		/**
		 * Returns the key of the entry
		 * @return The key of the entry
		 */
		public String getKey() {
			return key.toString();
		}
		
		
		/**
		 * Returns the value in the text field
		 * @return The value in the text field
		 */
		public String getValue() {
			return valueTextField.getText();
		}
		
		
		/**
		 * Returns the key label
		 * @return The key label
		 */
		public Label getKeyLabel() {
			return keyLabel;
		}
		
		
		/**
		 * Returns the value text field
		 * @return The value text field
		 */
		public TextField getValueTextField() {
			return valueTextField;
		}
	}
}
