package tech.octopusdragon.randomsentencegenerator.components.partsofspeech;

import java.util.Random;

import tech.octopusdragon.randomsentencegenerator.components.Word;
import tech.octopusdragon.randomsentencegenerator.enums.Case;
import tech.octopusdragon.randomsentencegenerator.enums.Number;
import tech.octopusdragon.randomsentencegenerator.enums.Person;
import tech.octopusdragon.randomsentencegenerator.util.Util;

/**
   The Pronoun class simulates a pronoun.
   @author AJ Gill
*/

public class Pronoun extends Word {

   // --- Fields ---------------------------------------------------------------

   private Number number;
   
   private Person person;
   
   // --- Constructors ---------------------------------------------------------
   
   /**
      The default constructor chooses a pronoun with the given parameters.
      @param number Whether the pronoun is singular or plural
      @param pronounCase Whether the pronoun is a subject or object
   */
   
   public Pronoun(Case pronounCase) {
      Random rand = new Random();
      
      // Assign the case index to the value passed as an argument (row)
      int caseIndex;
      if (pronounCase.equals(Case.SUBJECT))
         caseIndex = 0;
      else if (pronounCase.equals(Case.OBJECT))
         caseIndex = 1;
      else
         caseIndex = 2;
      
      // Assign a random pronoun (column)
      int pronounIndex = rand.nextInt(7);
      
      // Determine the number.
      if (pronounIndex <= 0)
         number = Number.SINGULAR;
      else if (pronounIndex <= 1)
         number = Number.PLURAL;
      else if (pronounIndex <= 4)
         number = Number.SINGULAR;
      else
         number = Number.PLURAL;
      
      // Determine the person.
      if (pronounIndex <= 1)
         person = Person.FIRST;
      else if (pronounIndex <= 5)
         person = Person.THIRD;
      else
         person = Person.SECOND;
      
      // Set the pronoun.
      setWord(Util.pronouns[caseIndex][pronounIndex]);
   }
   
   // --- Methods --------------------------------------------------------------
   
   /**
      The getNumber method returns whether the pronoun is singular or plural.
      @return The value of number
   */
   
   public Number getNumber() {
      return number;
   }
   
   /**
      The getPerson method returns whether the pronoun is first, second, or
      third person.
      @return The value of person
   */
   
   public Person getPerson() {
      return person;
   }
}