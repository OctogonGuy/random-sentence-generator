package tech.octopusdragon.randomsentencegenerator.components.phrases;

import java.util.ArrayList;

import tech.octopusdragon.randomsentencegenerator.components.partsofspeech.Preposition;
import tech.octopusdragon.randomsentencegenerator.enums.Case;

/**
   The PrepositionalPhrase class constructs a prepositional phrase.
   @author AJ Gill
*/

public class PrepositionalPhrase {
   
   private ArrayList<Object> prepositionalPhrase = new ArrayList<>();
   
   
   
   /**
      No-arg constructor
   */
   
   public PrepositionalPhrase() {
      prepositionalPhrase.add(new Preposition());
      prepositionalPhrase.add(new NounPhrase(Case.OBJECT));
   }
   
   /**
      The toString method.
      @return The contents in the form of a String
   */
   
   public String toString() {
      String str = "";
      
      int i;
      for (i = 0; i < prepositionalPhrase.size() - 1; i++)
         str = str + prepositionalPhrase.get(i) + " ";
      str = str + prepositionalPhrase.get(i);
      
      return str;
   }
   
}