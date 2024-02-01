package tech.octopusdragon.randomsentencegenerator.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import tech.octopusdragon.randomsentencegenerator.enums.AdverbType;
import tech.octopusdragon.randomsentencegenerator.enums.ConjunctionType;
import tech.octopusdragon.randomsentencegenerator.enums.VerbType;

public class Util {
	
	// --- Constants ---
	public static final String WORD_DIR = "resources/words";
	public static final String PROPERTIES_PATH = "resources/defaultProperties.properties";

	
	
	// --- Variables ---
	private static Properties defaultProperties;
	private static Properties properties;
	private static Random rand;
	private static double randNum;
	
	
	// Arrays to contain parts of speech
	public static String[][] nouns;
	public static String[][] pronouns;
	public static Map<VerbType, String[][]> verbs;
	public static String[] adjectives;
	public static Map<AdverbType, String[]> adverbs;
	public static String[] prepositions;
	public static Map<ConjunctionType, String[]> conjunctions;
	public static String[] interjections;
	
	
	
	/**
	 * Initializes all static variables
	 */
	static {
		
		// Instantiate Random object for generating random numbers.
		rand = new Random();
		
		
		// Instantiate Property objects for obtaining properties
		// Create and load default properties
		defaultProperties = new OrderedProperties();
		loadDefaultProperties();
		properties = new OrderedProperties(defaultProperties);
		
		
		// Fill word arrays with words from data files.
		readWords();
	}
	

	
	/**
	 * Reads words from files into arrays.
	 */
	private static void readWords() {
		nouns = readCSV(WORD_DIR + "/" + "nouns.txt");
		
		pronouns = new String[][] {
				{ "I", "we", "he", "she", "it", "they", "you" },
				{ "me", "us", "him", "her", "it", "them", "you" },
				{ "my", "our", "his", "her", "its", "their", "your" } };
				
		verbs = new HashMap<VerbType, String[][]>();
		verbs.put(VerbType.INTRANSITIVE, readCSV(WORD_DIR + "/" + "verbs-intransitive.txt"));
		verbs.put(VerbType.TRANSITIVE, readCSV(WORD_DIR + "/" + "verbs-transitive.txt"));
		verbs.put(VerbType.DITRANSITIVE, readCSV(WORD_DIR + "/" + "verbs-ditransitive.txt"));
		verbs.put(VerbType.NOUN_LINKING, readCSV(WORD_DIR + "/" + "verbs-pnLinking.txt"));
		verbs.put(VerbType.ADJECTIVE_LINKING, readCSV(WORD_DIR + "/" + "verbs-paLinking.txt"));
		verbs.put(VerbType.BE, new String[][] { {"am", "is", "are", "was", "were", "be", "being", "been" } });
				
		adjectives = readFile(WORD_DIR + "/" + "adjectives.txt");
		
		adverbs = new HashMap<AdverbType, String[]>();
		adverbs.put(AdverbType.MANNER, readFile(WORD_DIR + "/" + "adverbs-manner.txt"));
		adverbs.put(AdverbType.TIME, readFile(WORD_DIR + "/" + "adverbs-time.txt"));
		adverbs.put(AdverbType.PLACE, readFile(WORD_DIR + "/" + "adverbs-place.txt"));
		adverbs.put(AdverbType.FREQUENCY, readFile(WORD_DIR + "/" + "adverbs-frequency.txt"));
		adverbs.put(AdverbType.DEGREE, readFile(WORD_DIR + "/" + "adverbs-degree.txt"));
		
		prepositions = new String[] {
				"aboard", "about", "above", "across", "after",
				"against", "along", "among", "around", "as",
				"at", "before", "behind", "below", "beneath",
				"beside", "between", "beyond", "but", "by",
				"despite", "down", "during", "except", "for",
				"from", "in", "inside", "into", "like",
				"near", "of", "off", "on", "out",
				"outside", "over", "past", "since", "through",
				"throughout", "to", "toward", "under", "underneath",
				"until", "up", "upon", "with", "within",
            	"without" };
		
		conjunctions = new HashMap<ConjunctionType, String[]>();
		conjunctions.put(ConjunctionType.COORDINATING, new String[] { "and", "or", "but" });
		conjunctions.put(ConjunctionType.SUBORDINATING, readFile(WORD_DIR + "/" + "conjunctions-subordinating.txt"));
		
		interjections = readFile(WORD_DIR + "/" + "interjections.txt");
	}
	
	
	
	/**
	 * Reads a file with words and returns an array of those words.
	 * @param filename The file name.
	 * @return The array of words.
	 */
	private static String[] readFile(String filename) {
		String line;
		ArrayList<String> items = new ArrayList<>();

		// Open the file
		InputStream inputStream = Util.class.getClassLoader().getResourceAsStream(filename);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader in = new BufferedReader(inputStreamReader);
		Scanner inputFile = new Scanner(in);
		
		// Read the items from the file
		while (inputFile.hasNext()) {
			line = inputFile.nextLine().split(",")[0];
			if (!(line.isEmpty() || line.startsWith("//"))) items.add(line);
		}
		
		// Close the file
		inputFile.close();
		
		return items.toArray(new String[items.size()]);
	}
	
	
	
	/**
	 * Reads a file with groups of words and returns a two-dimensional array of
	 * those words.
	 * @param filename The file name.
	 * @return The array of words.
	 */
	private static String[][] readCSV(String filename) {
		String line;
		String[] tokens;
		ArrayList<String[]> items = new ArrayList<>();

		// Open the file
		InputStream inputStream = Util.class.getClassLoader().getResourceAsStream(filename);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader in = new BufferedReader(inputStreamReader);
		Scanner inputFile = new Scanner(in);
		
		try {
			// Read the items from the file
			while (inputFile.hasNext()) {
				line = inputFile.nextLine();
				if (!(line.isEmpty() || line.startsWith("//"))) {
					tokens = line.split(",");
					items.add(tokens);
					
					// If the number of tokens on this line are different than
					// before, throw an exception
					if (tokens.length != items.get(0).length)
						throw new IllegalArgumentException();
				}
			}
		}
		catch (IllegalArgumentException e) {
			System.out.println("Incorrect number of tokens on line "
					+ (items.size() + 1) + " of " + filename);
			e.printStackTrace();
			System.exit(1);
		}
		finally {
			
			// Close the file
			inputFile.close();
		}
		
		return items.toArray(new String[0][0]);
	}
	
	
	
	/**
	 * Loads default properties from the default properties file.
	 */
	private static void loadDefaultProperties() {
		try {
			InputStream inputStream = Util.class.getClassLoader().getResourceAsStream(PROPERTIES_PATH);
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader in = new BufferedReader(inputStreamReader);
			defaultProperties.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: Default properties file not found: " 
					+ PROPERTIES_PATH);
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			System.out.println("Error occured when reading default properties: "
					+ PROPERTIES_PATH);
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	
	/**
	 * Returns the default properties.
	 * @return The default properties
	 */
	public static Properties getDefaultProperties() {
		return defaultProperties;
	}
	
	
	
	/**
	 * Returns the properties.
	 * @return The properties
	 */
	public static Properties getProperties() {
		return properties;
	}
	
	
	
	/**
	 * Returns a random element in the given array.
	 * @param An array from which to select
	 * @return The randomly selected element
	 */
	public static <T> T randElement(T[] arr) {
		return arr[rand.nextInt(arr.length)];
	}
	
	
	
	/**
	 * Randomly generates a boolean value based on the probability of an event
	 * happening specified in the application properties. This method must be
	 * overridden in the subclass.
	 * @param propertyName The name of the chance property key
	 * @return Whether the event should occur
	 * @throws NumberFormatException if the property value string does not
	 * 		   contain a parsable double.
	 */
	public static boolean randChance(String propertyName) {
		String value = properties.getProperty(propertyName);
		double probability = Double.parseDouble(value);
		randNum = rand.nextDouble();
		boolean shouldOccur = randNum < probability;
		randNum -= probability;
		return shouldOccur;
	}
	
	
	
	/**
	 * Randomly generates a boolean value based on the probability of an event
	 * happening specified in the application properties. This method does not
	 * generate a random number and instead uses the last randomly generated
	 * number from the randChance method minus the probabilities that have
	 * already been used.
	 * @param propertyName The name of the chance property key
	 * @return Whether the event should occur
	 * @throws NumberFormatException if the property value string does not
	 * 		   contain a parsable double.
	 */
	public static boolean randChanceContinued(String propertyName) {
		String value = properties.getProperty(propertyName);
		double probability = Double.parseDouble(value);
		boolean shouldOccur = randNum < probability;
		randNum -= probability;
		return shouldOccur;
	}
}
