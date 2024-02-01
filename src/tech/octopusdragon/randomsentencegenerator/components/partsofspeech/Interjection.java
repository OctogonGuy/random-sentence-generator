package tech.octopusdragon.randomsentencegenerator.components.partsofspeech;

import tech.octopusdragon.randomsentencegenerator.components.Word;
import tech.octopusdragon.randomsentencegenerator.util.Util;

/**
   The Interjection class contains fields and methods pretaining to interjections.
   @author AJ Gill
*/

public class Interjection extends Word {
   /**
      Constructor
   */
   
   public Interjection(){
      
      // Set the interjection.
      setWord(Util.randElement(Util.interjections));
      
      // If there is no punctuation specified, add a comma
      if (!(toString().charAt(toString().length() - 1) == '.' ||
    		  toString().charAt(toString().length() - 1) == '!' ||
    		  toString().charAt(toString().length() - 1) == '?')) {
    	  setWord(toString() + ",");
      }
   }
}