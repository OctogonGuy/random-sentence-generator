package tech.octopusdragon.randomsentencegenerator.components.phrases;

import java.util.ArrayList;

import tech.octopusdragon.randomsentencegenerator.components.partsofspeech.Adverb;
import tech.octopusdragon.randomsentencegenerator.enums.AdverbType;

/**
   The AdverbPhrase class constructs an adverb phrase using Adverb.
   @author AJ Gill
*/

public class AdverbPhrase {

   private ArrayList<Object> adverbPhrase = new ArrayList<>();
   
   
   
   /**
      No-arg constructor
   */
   
   public AdverbPhrase() {
      adverbPhrase.add(new Adverb(AdverbType.DEGREE));
      adverbPhrase.add(new Adverb());
   }
   
   /**
      The toString method.
      @return The contents in the form of a String
   */
   
   public String toString() {
      String str = "";
      
      int i;
      for (i = 0; i < adverbPhrase.size() - 1; i++)
         str = str + adverbPhrase.get(i) + " ";
      str = str + adverbPhrase.get(i);
      
      return str;
   }
}