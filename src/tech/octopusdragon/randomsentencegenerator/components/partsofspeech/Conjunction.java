package tech.octopusdragon.randomsentencegenerator.components.partsofspeech;

import tech.octopusdragon.randomsentencegenerator.components.Word;
import tech.octopusdragon.randomsentencegenerator.enums.ConjunctionType;
import tech.octopusdragon.randomsentencegenerator.util.Util;

/**
   The Conjunction class simulates a conjunction.
   @author AJ Gill
*/

public class Conjunction extends Word {
   /**
      Constructor
   */
   
   public Conjunction(ConjunctionType type) {
      
      // Set the conjunction
      setWord(Util.randElement(Util.conjunctions.get(type)));
   }
}