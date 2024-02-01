package tech.octopusdragon.randomsentencegenerator.components.phrases;

import java.util.ArrayList;

import tech.octopusdragon.randomsentencegenerator.components.partsofspeech.Adjective;
import tech.octopusdragon.randomsentencegenerator.components.partsofspeech.Adverb;
import tech.octopusdragon.randomsentencegenerator.enums.AdverbType;

/**
   The AdjectivePhrase class constructs an adjective phrase using Adjective and
   Adverb.
   @author AJ Gill
*/

public class AdjectivePhrase {

   private ArrayList<Object> adjectivePhrase = new ArrayList<>();
   
   
   
   /**
      The default constructor constructs an adjective phrase.
   */
   
   public AdjectivePhrase() {
      adjectivePhrase.add(new Adverb(AdverbType.DEGREE));
      adjectivePhrase.add(new Adjective());
   }
   
   /**
      The toString method.
      @return The contents in the form of a String
   */
   
   public String toString() {
      String str = "";
      
      int i;
      for (i = 0; i < adjectivePhrase.size() - 1; i++)
         str = str + adjectivePhrase.get(i) + " ";
      str = str + adjectivePhrase.get(i);
      
      return str;
   }
}