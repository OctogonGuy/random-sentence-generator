package tech.octopusdragon.randomsentencegenerator.demo;

import tech.octopusdragon.randomsentencegenerator.components.Sentence;

/**
 * This program generates a random sentence to the console.
 * @author A.J. Gill
 */

public class RandomSentenceGeneratorDemo {

	public static void main(String[] args) {
		for (int i = 0; i < 999999; i++)
			System.out.println(new Sentence().toString());
	}

}
