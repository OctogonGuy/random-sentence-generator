package tech.octopusdragon.randomsentencegenerator.components;

import java.util.Random;

import tech.octopusdragon.randomsentencegenerator.components.partsofspeech.Conjunction;
import tech.octopusdragon.randomsentencegenerator.components.partsofspeech.Interjection;
import tech.octopusdragon.randomsentencegenerator.enums.ConjunctionType;
import tech.octopusdragon.randomsentencegenerator.util.Util;

import java.util.ArrayList;

/**
   The Sentence class uses the parts of speech classes to create a string that
   holds a randomly generated sentence.
   @author AJ Gill
*/

public class Sentence {

   ArrayList<Object> sentence = new ArrayList<>();
   
   
   
   /**
      This method constructs a sentence.
   */
   
   public void constructSentence() {
	   
      // Build the sentence
      sentence.add(new Clause());
      if (Util.randChance("compoundSentence")) {
         sentence.add(",");
         sentence.add(new Conjunction(ConjunctionType.COORDINATING));
         sentence.add(new Clause());
      }
      else if (Util.randChanceContinued("complexSentence")) {
         if (new Random().nextBoolean()) {
            sentence.add(new Conjunction(ConjunctionType.SUBORDINATING));
            sentence.add(new Clause());
         } else {
            sentence.add(0, ",");
            sentence.add(0, new Clause());
            sentence.add(0, new Conjunction(ConjunctionType.SUBORDINATING));
         }
      }
      if (Util.randChance("interjection")) {
         sentence.add(0, new Interjection());
      }
   }
   
   
   
   /**
      The overloaded constructor constructs a sentence.
   */
   
   public Sentence() {
      constructSentence();
   }
   
   
   
   /**
      The toString method.
      @return The contents in the form of a String
   */
   
   public String toString() {
      StringBuilder str = new StringBuilder();
      boolean capitalizeNext = true;   // Flag
      
      for (int i = 0; i < sentence.size(); i++) {
         if (capitalizeNext) {
            str.append(sentence.get(i).toString().substring(0, 1).toUpperCase());
            str.append(sentence.get(i).toString().substring(1));
            capitalizeNext = false;
         } else
            str.append(sentence.get(i).toString());
         if (sentence.get(i).toString().charAt(sentence.get(i).toString().length() - 1) == '.' ||
             sentence.get(i).toString().charAt(sentence.get(i).toString().length() - 1) == '!' ||
             sentence.get(i).toString().charAt(sentence.get(i).toString().length() - 1) == '?')
            capitalizeNext = true;
         if (i == sentence.size() - 1)
            str.append(".");
         else if (!(sentence.get(i + 1).toString().charAt(sentence.get(i + 1).toString().length() - 1) == ','))
            str.append(" ");
      }
      
      return str.toString();
   }
   
}