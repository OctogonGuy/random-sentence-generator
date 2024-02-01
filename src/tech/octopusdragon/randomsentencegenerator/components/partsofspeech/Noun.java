package tech.octopusdragon.randomsentencegenerator.components.partsofspeech;

import tech.octopusdragon.randomsentencegenerator.components.Word;
import tech.octopusdragon.randomsentencegenerator.enums.Number;
import tech.octopusdragon.randomsentencegenerator.util.Util;

/**
   The Noun class represents a noun.
   @author AJ Gill
*/

public class Noun extends Word {

   // --- Fields ---------------------------------------------------------------
   
   private Number number;              // The noun's number
   
   // --- Constructors ---------------------------------------------------------
   
   /**
      The constructor selects a random noun whose form depends on the
      parameters given.
      @param number Whether the noun is singular or plural.
   */
   
   public Noun(Number number) {
      
      // Set the nounIndex to a random index.
      String[] nounGroup = Util.randElement(Util.nouns);
      
      // Set other indexes to the corresponding index.
      int numberIndex;
      if (number == Number.SINGULAR)
         numberIndex = 0;
      else
         numberIndex = 1;
      
      // Set the noun.
      setWord(nounGroup[numberIndex]);
   }
   
   // --- Methods --------------------------------------------------------------
   
   /**
      The getNumber method returns whether the noun is singular or plural.
      @return The value stored in number.
   */
   
   public Number getNumber() {
      return number;
   }
}