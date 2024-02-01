package tech.octopusdragon.randomsentencegenerator.components.phrases;

import java.util.Random;

import tech.octopusdragon.randomsentencegenerator.components.partsofspeech.Adjective;
import tech.octopusdragon.randomsentencegenerator.components.partsofspeech.Noun;
import tech.octopusdragon.randomsentencegenerator.components.partsofspeech.Pronoun;
import tech.octopusdragon.randomsentencegenerator.enums.Case;
import tech.octopusdragon.randomsentencegenerator.enums.NounPhraseType;
import tech.octopusdragon.randomsentencegenerator.enums.Number;
import tech.octopusdragon.randomsentencegenerator.enums.Person;
import tech.octopusdragon.randomsentencegenerator.util.Util;

import java.util.ArrayList;

/**
   The NounPhrase class constructs a noun phrase using Noun and Adjective.
   @author AJ Gill
*/

public class NounPhrase {
   
   // --- Fields ---------------------------------------------------------------
   
   private ArrayList<Object> nounPhrase = new ArrayList<>();
   
   private Number number;
   
   private Person person;
   
   // --- Constructors ---------------------------------------------------------
   
   /**
      This method constructs the noun phrase.
      @param number Whether the noun phrase is to be singular or plural
      @param pronounCase Whether the noun phrase is a subject or object
                         (or neither)
   */
   
   public void constructNounPhrase(Number number) {
      Random rand = new Random();
      
      // Get the noun
      nounPhrase.add(new Noun(number));
      this.number = number;
      person = Person.THIRD;
      
      // Get a number of adjectives
      while (Util.randChance("adjective")) {
         if (Util.randChance("adjectivePhrase"))
            nounPhrase.add(0, new AdjectivePhrase());
         else
            nounPhrase.add(0, new Adjective());
      }// End while
      
      // Add a possessive
      if (Util.randChance("possessive")) {
         if (Util.randChance("possessivePronoun")) {
            nounPhrase.add(0, new Pronoun(Case.POSSESSIVE));
         } else {
             nounPhrase.add(0, new NounPhrase());
             if (nounPhrase.get(0).toString().charAt(nounPhrase.get(0).toString().length() - 1) != 's')
                nounPhrase.add(1, "'s");
             else
                nounPhrase.add(1, "'");
         }
      // Add an article adjective
      } else if (number.equals(Number.SINGULAR)) {
         if (rand.nextBoolean()) {
            switch (nounPhrase.get(0).toString().substring(0, 1)) {
               case "a":
               case "e":
               case "i":
               case "o":
               case "u":
                  nounPhrase.add(0, "an");
                  break;
               default:
                  nounPhrase.add(0, "a");
                  break;
            }
         } else
            nounPhrase.add(0, "the");
      } else if (rand.nextBoolean())
         nounPhrase.add(0, "the");
      // Add a prepositional phrase.
      if (Util.randChance("prepositionalPhrase"))
         nounPhrase.add(new PrepositionalPhrase());
      
      // Add a compound noun.
      if (Util.randChance("compoundNoun")) {
         nounPhrase.add("and" );
         nounPhrase.add(new NounPhrase());
         this.number = Number.PLURAL;
      }
   }
   
   /**
      The overloaded constructor constructs a noun phrase the given parameters.
      @param type Whether the noun phrase contains a noun or a pronoun
      @param pronounCase Whether the noun phrase is a subject or an object
      @param number Whether the noun phrase is singular or plural
   */
   
   public NounPhrase(Number number) {
      constructNounPhrase(number);
   }
   
   /**
      The one-arg constructor constructs a noun phrase with the given case.
      @param pronounCase Whether the noun phrase is a subject or an object
   */
   
   public NounPhrase(Case pronounCase) {
      NounPhraseType type;
      Number number;
      
      // Determine the type.
      if (Util.randChance("pronoun"))
          type = NounPhraseType.PRONOUN;
      else
          type = NounPhraseType.NOUN;
      
      // Determine the number.
      if (Util.randChance("pluralNumber"))
          number = Number.PLURAL;
      else
          number = Number.SINGULAR;
      
      // Get the noun or pronoun
      if (type.equals(NounPhraseType.NOUN)) {
         constructNounPhrase(number);
      } else {
         Pronoun p = new Pronoun(pronounCase);
         nounPhrase.add(p);
         this.number = p.getNumber();
         this.person = p.getPerson();
         return;
      }
   }
   
   /**
      The overloaded constructor constructs a noun phrase the given parameters.
      @param type Whether the noun phrase contains a noun or a pronoun
      @param pronounCase Whether the noun phrase is a subject or an object
      @param number Whether the noun phrase is singular or plural
   */
   
   public NounPhrase() {
      Number number;
      
      // Determine the number.
      if (Util.randChance("pluralNumber"))
          number = Number.PLURAL;
      else
          number = Number.SINGULAR;
      
      constructNounPhrase(number);
   }
   
   /**
      The getNumber method returns whether the noun phrase is singular or plural.
      @return The value in number
   */
   
   public Number getNumber() {
      return number;
   }
   
   /**
      The getPerson method returns whether the noun phrase is first, second, or
      third person.
      @return The value in person
   */
   
   public Person getPerson() {
      return person;
   }
   
   /**
      The toString method.
      @return The contents in the form of a String
   */
   
   public String toString() {
      String str = "";
      
      int i;
      for (i = 0; i < nounPhrase.size() - 1; i++)
         if (nounPhrase.get(i + 1).toString().charAt(0) != '\'')
            str = str + nounPhrase.get(i) + " ";
         else
            str = str + nounPhrase.get(i);
      str = str + nounPhrase.get(i);
      
      return str;
   }
   
}