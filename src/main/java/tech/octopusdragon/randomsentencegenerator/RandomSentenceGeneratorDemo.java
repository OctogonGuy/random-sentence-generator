package tech.octopusdragon.randomsentencegenerator;

import tech.octopusdragon.randomsentencegenerator.components.Sentence;

/**
 * This program generates a random sentence to the console.
 * @author A.J. Gill
 */

public class RandomSentenceGeneratorDemo {

	private static final int NUM_SENTENCES = 1;

	public static void main(String[] args) {
		for (int i = 0; i < NUM_SENTENCES; i++)
			System.out.println(new Sentence().toString());
	}

}
