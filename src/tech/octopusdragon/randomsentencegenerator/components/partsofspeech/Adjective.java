package tech.octopusdragon.randomsentencegenerator.components.partsofspeech;

import tech.octopusdragon.randomsentencegenerator.components.Word;
import tech.octopusdragon.randomsentencegenerator.util.Util;

/**
   The Adjective class simulates an adjective.
   @author AJ Gill
*/

public class Adjective extends Word {
   /**
      Constructor
   */
   
   public Adjective() {
      
      // Set the adjective.
      setWord(Util.randElement(Util.adjectives));
   }
}