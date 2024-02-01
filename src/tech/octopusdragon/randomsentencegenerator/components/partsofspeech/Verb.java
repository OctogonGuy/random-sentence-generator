package tech.octopusdragon.randomsentencegenerator.components.partsofspeech;

import tech.octopusdragon.randomsentencegenerator.components.Word;
import tech.octopusdragon.randomsentencegenerator.enums.Aspect;
import tech.octopusdragon.randomsentencegenerator.enums.Number;
import tech.octopusdragon.randomsentencegenerator.enums.Person;
import tech.octopusdragon.randomsentencegenerator.enums.Tense;
import tech.octopusdragon.randomsentencegenerator.enums.VerbType;
import tech.octopusdragon.randomsentencegenerator.util.Util;

/**
   The Verb class simulates a verb.
   @author AJ Gill
*/

public class Verb extends Word {
   
   // --- Fields ---------------------------------------------------------------
   
   private VerbType type;
   
   // --- Constructors ---------------------------------------------------------
   
   /**
      The default constructor selects a random verb based on the parameters
      given.
      @param type The type of verb
      @param number Whether the subject is singular or plural
      @param person Whether the subject is first, second, or third person
      @param tense The tense of the verb
      @param aspect The aspect of the verb
   */
   
   public Verb(VerbType type, Number number, Person person, Tense tense, Aspect aspect) {
      
      // Set fields to parameter values.
      this.type = type;
      
      // Determine the inflection
      int inflectionIndex;
      if (!type.equals(VerbType.BE)) { // Non-be verbs
         // Standard
         if (aspect.equals(Aspect.SIMPLE) && tense.equals(Tense.PRESENT) && person.equals(Person.FIRST))
            inflectionIndex = 1;
         // -s
         else if (aspect.equals(Aspect.SIMPLE) && tense.equals(Tense.PRESENT) && number.equals(Number.SINGULAR))
            inflectionIndex = 0;
         // Standard
         else if (aspect.equals(Aspect.SIMPLE) && (tense.equals(Tense.PRESENT) || tense.equals(Tense.FUTURE)))
            inflectionIndex = 1;
         // -ed
         else if (aspect.equals(Aspect.SIMPLE) && tense.equals(Tense.PAST))
            inflectionIndex = 2;
         // -ed or exception
         else if (aspect.equals(Aspect.PERFECT))
            inflectionIndex = 3;
         // -ing
         else
            inflectionIndex = 4;
      } else {                         // Be verbs
         // am
         if (aspect.equals(Aspect.SIMPLE) && tense.equals(Tense.PRESENT) && number.equals(Number.SINGULAR) && person.equals(Person.FIRST))
            inflectionIndex = 0;
         // is
         else if (aspect.equals(Aspect.SIMPLE) && tense.equals(Tense.PRESENT) && number.equals(Number.SINGULAR))
            inflectionIndex = 1;
         // are
         else if (aspect.equals(Aspect.SIMPLE) && tense.equals(Tense.PRESENT))
            inflectionIndex = 2;
         // was
         else if (aspect.equals(Aspect.SIMPLE) && tense.equals(Tense.PAST) && number.equals(Number.SINGULAR))
            inflectionIndex = 3;
         // were
         else if (aspect.equals(Aspect.SIMPLE) && tense.equals(Tense.PAST))
            inflectionIndex = 4;
         // be
         else if (aspect.equals(Aspect.SIMPLE) && tense.equals(Tense.FUTURE))
            inflectionIndex = 5;
         // been
         else if (aspect.equals(Aspect.PERFECT))
            inflectionIndex = 7;
         // being
         else
            inflectionIndex = 6;
      }
      
      // Set the verb.
      setWord(Util.randElement(Util.verbs.get(type))[inflectionIndex]);
   }
   
   // --- Methods --------------------------------------------------------------
   
   /**
      The getType method returns the type of this verb.
      @return The value stored in type.
   */
   
   public VerbType getType() {
      return type;
   }
}