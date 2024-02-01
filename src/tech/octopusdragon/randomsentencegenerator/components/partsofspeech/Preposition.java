package tech.octopusdragon.randomsentencegenerator.components.partsofspeech;

import java.util.Random;

import tech.octopusdragon.randomsentencegenerator.components.Word;

/**
   The Preposition class simulates a preposition.
   @author AJ Gill
*/

public class Preposition extends Word {
   
   // --- Constructors ---------------------------------------------------------
   
   /**
      Constructor
   */
   
   public Preposition() {
      Random rand = new Random();
      
      // The list of prepositions
      String[] prepositions = {
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
                     "without"};
      
      // Randomly generate the preposition index.
      int prepositionIndex = rand.nextInt(prepositions.length);
      
      // Set the preposition.
      setWord(prepositions[prepositionIndex]);
   }
}