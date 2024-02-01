package tech.octopusdragon.randomsentencegenerator.components.partsofspeech;

import java.util.Random;

import tech.octopusdragon.randomsentencegenerator.components.Word;
import tech.octopusdragon.randomsentencegenerator.enums.AdverbType;
import tech.octopusdragon.randomsentencegenerator.util.Util;

/**
   The Adverb class simulates an adverb.
   @author AJ Gill
*/

public class Adverb extends Word {
   
   // --- Fields ---------------------------------------------------------------
   
   private AdverbType type;
   
   // --- Constructors ---------------------------------------------------------
   
   /**
      Constuctor
      @param The type of adverb.
   */
   
   public Adverb(AdverbType type) {
      
      // Set the fields to parameter values.
      this.type = type;
      
      // Set the adverb.
      setWord(Util.randElement(Util.adverbs.get(type)));
   }
   
   /**
      The no-arg constructor generates an adverb of a random type (excluding
      degree).
   */
   
   public Adverb() {
      
      // Determine the type index
      int tempIndex, totalSize;
      totalSize = 0;
      for (String[] arr : Util.adverbs.values())
         totalSize += arr.length;
      tempIndex = new Random().nextInt(totalSize);
      if (tempIndex < Util.adverbs.get(AdverbType.MANNER).length) {
         type = AdverbType.MANNER;
      } else if (tempIndex < Util.adverbs.get(AdverbType.MANNER).length +
                             Util.adverbs.get(AdverbType.TIME).length) {
         type = AdverbType.TIME;
      } else if (tempIndex < Util.adverbs.get(AdverbType.MANNER).length +
    		  				 Util.adverbs.get(AdverbType.TIME).length +
    		  				 Util.adverbs.get(AdverbType.PLACE).length) {
         type = AdverbType.PLACE;
      } else {
         type = AdverbType.FREQUENCY;
      }//end else-if
      
      // Set the adverb.
      setWord(Util.randElement(Util.adverbs.get(type)));
   }
   
   // --- Methods --------------------------------------------------------------
   
   /**
      The getType method returns the type of this adverb.
      @return The value stored in type.
   */
   
   public AdverbType getType() {
      return type;
   }
}