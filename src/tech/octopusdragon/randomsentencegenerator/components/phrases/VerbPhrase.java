package tech.octopusdragon.randomsentencegenerator.components.phrases;

import java.util.Random;

import tech.octopusdragon.randomsentencegenerator.components.partsofspeech.Adjective;
import tech.octopusdragon.randomsentencegenerator.components.partsofspeech.Adverb;
import tech.octopusdragon.randomsentencegenerator.components.partsofspeech.Verb;
import tech.octopusdragon.randomsentencegenerator.enums.Aspect;
import tech.octopusdragon.randomsentencegenerator.enums.Case;
import tech.octopusdragon.randomsentencegenerator.enums.Number;
import tech.octopusdragon.randomsentencegenerator.enums.Person;
import tech.octopusdragon.randomsentencegenerator.enums.Tense;
import tech.octopusdragon.randomsentencegenerator.enums.VerbType;
import tech.octopusdragon.randomsentencegenerator.util.Util;

import java.util.ArrayList;

/**
   The VerbPhrase class constructs a verb phrase using Verb, Adverb, Adjective,
   and NounPhrase.
   @author AJ Gill
*/

public class VerbPhrase {
   
   // --- Fields ---------------------------------------------------------------
   
   private ArrayList<Object> verbPhrase = new ArrayList<Object>();
   
   
   
   private int mainVerbIndex;
   
   private int finiteVerbIndex;
   
   
   
   private Tense tense;
   
   private Aspect aspect;
   
   private int pattern;
   
   private Number number;
   
   private Person person;
   
   
   
   // --- Constructors ---------------------------------------------------------
   
   /**
      This method constructs a verb phrase.
      @param pattern The pattern of the sentence
      @param number Whether the accompanying subject is singular or plural
      @param person Whether the accompanying subject is first, second, or third person
      @param tense The tense of the verb
      @param aspect The aspect of the verb
   */
   
   public void constructVerbPhrase(int pattern, Number number, Person person, Tense tense, Aspect aspect) {
      
      // Set modifier fields to parameter values.
      this.tense = tense;
      this.aspect = aspect;
      this.pattern = pattern;
      this.number = number;
      this.person = person;
      
      // Set index fields to default values.
      mainVerbIndex = -1;
      finiteVerbIndex = -1;
      
      // Build the verb phrase with the current pattern.
      generateSentencePattern();
      mainVerbIndex = 0;
      
      // Construct the verb tense and aspect
      conjugateVerb();
      
      // Add a random number of adverbs and prepositional phrases.
      addAdverbialPhrase();
   }
   
   
   
   /**
      The overloaded constructor uses constructVerbPhrase to construct a verb
      phrase.
      @param pattern The pattern of the sentence
      @param number Whether the accompanying subject is singular or plural
      @param person Whether the accompanying subject is first, second, or third person
      @param tense The tense of the verb
      @param aspect The aspect of the verb
   */
   
   public VerbPhrase(int pattern, Number number, Person person, Tense tense, Aspect aspect) {
      constructVerbPhrase(pattern, number, person, tense, aspect);
   }
   
   
   
   /**
      The two-arg constructor constructs a verb phrase using the pattern and
      number given as parameters and randomly generates the tense and aspect.
      @param pattern The pattern of the sentence.
      @param number Whether the subject is singular or plural.
      @param person Whether the accompanying subject is first, second, or third person
   */
   
   public VerbPhrase(int pattern, Number number, Person person) {
      Tense tense;   // The tense of the verb
      Aspect aspect; // The aspect of the verb
      
      // Determine the tense.
      if (Util.randChance("pastTense"))
    	  tense = Tense.PAST;
      else if (Util.randChanceContinued("futureTense"))
    	  tense = Tense.FUTURE;
      else
    	  tense = Tense.PRESENT;
      
      // Determine the aspect.
      if (Util.randChance("perfectAspect"))
    	  aspect = Aspect.PERFECT;
      else if (Util.randChanceContinued("continuousAspect"))
    	  aspect = Aspect.CONTINUOUS;
      else if (Util.randChanceContinued("perfectContinuousAspect"))
    	  aspect = Aspect.PERFECT_CONTINUOUS;
      else
    	  aspect = Aspect.SIMPLE;
      
      constructVerbPhrase(pattern, number, person, tense, aspect);
   }
   
   
   
   // --- Public Methods -------------------------------------------------------
   
   /**
      The getVerbPhrase method returns the verb phrase ArrayList.
      @return verbPhrase.
   */
   
   public ArrayList<Object> getVerbPhrase() {
      return verbPhrase;
   }
   
   
   
   /**
      The getTense method returns the tense of the verb phrase.
      @return The value in tense
   */
   
   public Tense getTense() {
      return tense;
   }
   
   
   
   /**
      The getAspect method returns the aspect of the verb phrase.
      @return The value in aspect
   */
   
   public Aspect getAspect() {
      return aspect;
   }
   
   
   
   /**
      The getFiniteVerbIndex method returns the index of the finite verb.
      @return The value stored in finiteVerbIndex.
   */
   
   public int getFiniteVerbIndex() {
      if (finiteVerbIndex == -1)
         return mainVerbIndex;
      else
         return finiteVerbIndex;
   }
   
   
   
   /**
      The toString method.
      @return The contents in the form of a String
   */
   
   public String toString() {
      String str = "";
      
      int i;
      for (i = 0; i < verbPhrase.size() - 1; i++)
         str = str + verbPhrase.get(i) + " ";
      str = str + verbPhrase.get(i);
      
      return str;
   }
   
   
   
   // --- Private Methods ------------------------------------------------------
   
   /**
      The generateSentencePattern method generates and builds a sentence with
      the given pattern.
   */
   
   public void generateSentencePattern() {
      switch (pattern) {
         case 1:
            verbPhrase.add(new Verb(VerbType.INTRANSITIVE, number, person, tense, aspect));
            break;
         case 2:
            verbPhrase.add(new Verb(VerbType.TRANSITIVE, number, person, tense, aspect));
            verbPhrase.add(new NounPhrase(Case.OBJECT));
            break;
         case 3:
            verbPhrase.add(new Verb(VerbType.DITRANSITIVE, number, person, tense, aspect));
            verbPhrase.add(new NounPhrase(Case.OBJECT));
            verbPhrase.add(new NounPhrase(Case.OBJECT));
            break;
         case 4:
            if (Util.randChance("pnBeVerb"))
                verbPhrase.add(new Verb(VerbType.BE, number, person, tense, aspect));
            else
            	verbPhrase.add(new Verb(VerbType.NOUN_LINKING, number, person, tense, aspect));
            verbPhrase.add(new NounPhrase(Case.OBJECT));
            break;
         default:
            if (Util.randChance("paBeVerb"))
                verbPhrase.add(new Verb(VerbType.BE, number, person, tense, aspect));
            else
            	verbPhrase.add(new Verb(VerbType.ADJECTIVE_LINKING, number, person, tense, aspect));
            if (Util.randChance("adjectivePhrase"))
               verbPhrase.add(new AdjectivePhrase());
            else
               verbPhrase.add(new Adjective());
            if (Util.randChance("compoundAdjective")) {
               verbPhrase.add("and");
               if (Util.randChance("adjectivePhrase"))
                  verbPhrase.add(new AdjectivePhrase());
               else
                  verbPhrase.add(new Adjective());
            }
      }
   }
   
   
   
   /**
      The conjugateVerb method conjugates the verb to the current tense and
      aspect.
   */
   
   public void conjugateVerb() {
      if (aspect.equals(Aspect.SIMPLE) && tense.equals(Tense.FUTURE)) {
         verbPhrase.add(0, "will");
         finiteVerbIndex = 0;
         mainVerbIndex++;
         
      } else if (aspect.equals(Aspect.PERFECT)) {
         if (tense.equals(Tense.PRESENT)) {
            if (number.equals(Number.SINGULAR) && !person.equals(Person.FIRST)) {
               verbPhrase.add(0, "has");
               finiteVerbIndex = 0;
               mainVerbIndex++;
            } else {
               verbPhrase.add(0, "have");
               finiteVerbIndex = 0;
               mainVerbIndex++;
            }
         } else if (tense.equals(Tense.PAST)) {
            verbPhrase.add(0, "had");
            finiteVerbIndex = 0;
            mainVerbIndex++;
         } else {
            verbPhrase.add(0, "have");
            verbPhrase.add(0, "will");
            finiteVerbIndex = 0;
            mainVerbIndex += 2;
         }
      } else if (aspect.equals(Aspect.CONTINUOUS)) {
         if (tense.equals(Tense.PRESENT)) {
            if (number.equals(Number.SINGULAR) && person.equals(Person.FIRST)) {
               verbPhrase.add(0, "am");
               finiteVerbIndex = 0;
               mainVerbIndex++;
            } else if (number.equals(Number.SINGULAR)) {
               verbPhrase.add(0, "is");
               finiteVerbIndex = 0;
               mainVerbIndex++;
            } else {
               verbPhrase.add(0, "are");
               finiteVerbIndex = 0;
               mainVerbIndex++;
            }
         } else if (tense.equals(Tense.PAST)) {
            if (number.equals(Number.SINGULAR)) {
               verbPhrase.add(0, "was");
               finiteVerbIndex = 0;
               mainVerbIndex++;
            } else {
               verbPhrase.add(0, "were");
               finiteVerbIndex = 0;
               mainVerbIndex++;
            }
         } else {
            verbPhrase.add(0, "be");
            verbPhrase.add(0, "will");
            finiteVerbIndex = 0;
            mainVerbIndex += 2;
         }
      } else if (aspect.equals(Aspect.PERFECT_CONTINUOUS)) {
         if (tense.equals(Tense.PRESENT)) {
            if (number.equals(Number.SINGULAR) && !person.equals(Person.FIRST)) {
               verbPhrase.add(0, "been");
               verbPhrase.add(0, "has");
               finiteVerbIndex = 0;
               mainVerbIndex += 2;
            } else {
               verbPhrase.add(0, "been");
               verbPhrase.add(0, "have");
               finiteVerbIndex = 0;
               mainVerbIndex += 2;
            }
         } else if (tense.equals(Tense.PAST)) {
            verbPhrase.add(0, "been");
            verbPhrase.add(0, "had");
            finiteVerbIndex = 0;
            mainVerbIndex += 2;
         } else {
            verbPhrase.add(0, "been");
            verbPhrase.add(0, "will have");
            finiteVerbIndex = 0;
            mainVerbIndex += 2;
         }
      }
   }
   
   
   
   /**
      The addAdvAndPrep method adds adverbs and prepositional phrases to the
      verb phrase.
   */
   
   public void addAdverbialPhrase() {
      Random rand = new Random();
      
      // Flags
      boolean tryAnotherAdverb = true;
      boolean tryAnotherPrepPhrase = true;
      boolean addAdverb = false;
      
      // Keep trying to add an adverb or prepositional phrase until both
      // tryAnotherAdverb and tryAnotherPrepPhrase are false.
      while (tryAnotherAdverb || tryAnotherPrepPhrase) {
         // Figure out whether to add an adverb or prepositional phrase this
         // iteration.
         if (tryAnotherAdverb && tryAnotherPrepPhrase)
            if (rand.nextBoolean())
               addAdverb = true;
         else if (tryAnotherAdverb)
            addAdverb = true;
         
         // If addAdverb is activated, add an adverb.
         if (addAdverb) {
            if (Util.randChance("adverb")) {
               
               // Determine the position of the adverb
               int pos;
               // If there is no finite verb...
               if (finiteVerbIndex == -1) {
                  switch (rand.nextInt(3)) {
                     case 0:  // Beginning of the sentence
                        pos = 0;
                        mainVerbIndex++;
                        break;
                     case 1:  // End of the sentence
                        pos = verbPhrase.size();
                        break;
                     default: // Before the main verb
                        pos = mainVerbIndex;
                        mainVerbIndex++;
                        break;
                  }
               // If there is a finite verb...
               } else {
                  switch (rand.nextInt(4)) {
                     case 0:  // Beginning of the sentence
                        pos = 0;
                        mainVerbIndex++;
                        finiteVerbIndex++;
                        break;
                     case 1:  // End of the sentence
                        pos = verbPhrase.size();
                        break;
                     case 2:  // Before the main verb
                        pos = mainVerbIndex;
                        mainVerbIndex++;
                        break;
                     default: // After the finite verb
                        pos = finiteVerbIndex + 1;
                        mainVerbIndex++;
                        break;
                  }
               }
               // Add an adverb or adverb phrase at the indicated position.
               if (Util.randChance("adverbPhrase"))
                  verbPhrase.add(pos, new AdverbPhrase());
               else
                  verbPhrase.add(pos, new Adverb());
               
            } else
               tryAnotherAdverb = false;
         // If addAdverb is not activated, add a prepositional phrase.
         
         } else {
            if (Util.randChance("prepositionalPhrase")) {
               // Determine the position of the prepositional phrase.
               int pos;
               switch (rand.nextInt(2)) {
                  case 0:  // Beginning of the sentence
                     pos = 0;
                     mainVerbIndex++;
                     if (finiteVerbIndex != -1)
                        finiteVerbIndex++;
                     break;
                  default: // End of the sentence
                     pos = verbPhrase.size();
                     break;
               }
               
               // Add a prepositional phrase at the indicated position.
               verbPhrase.add(pos, new PrepositionalPhrase());
            } else
               tryAnotherPrepPhrase = false;
         }
         addAdverb = false;
      }// End while
   }
}