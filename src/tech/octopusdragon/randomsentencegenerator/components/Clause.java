package tech.octopusdragon.randomsentencegenerator.components;

import java.util.Random;

import tech.octopusdragon.randomsentencegenerator.components.partsofspeech.Conjunction;
import tech.octopusdragon.randomsentencegenerator.components.phrases.NounPhrase;
import tech.octopusdragon.randomsentencegenerator.components.phrases.VerbPhrase;
import tech.octopusdragon.randomsentencegenerator.enums.Case;
import tech.octopusdragon.randomsentencegenerator.enums.ConjunctionType;
import tech.octopusdragon.randomsentencegenerator.util.Util;

import java.util.ArrayList;

/**
   The Clause class builds a clause from words and phrases.
   @author AJ Gill
*/

public class Clause {

   private int subjectIndex;
   
   ArrayList<Object> clause = new ArrayList<>();
   
   
   
   /**
      This method constructs a clause with the given parameters.
      @param pattern The pattern of the clause
   */
   
   public void constructClause(int pattern) {
      Random rand = new Random();
      
      // Build the clause
      NounPhrase subject = new NounPhrase(Case.SUBJECT);
      VerbPhrase predicate = new VerbPhrase(pattern, subject.getNumber(), subject.getPerson());
      for (Object object : predicate.getVerbPhrase())
         clause.add(object);
      
      clause.add(predicate.getFiniteVerbIndex(), subject);
      subjectIndex = predicate.getFiniteVerbIndex();
      
      if (Util.randChance("compoundVerb")) {
         clause.add(new Conjunction(ConjunctionType.COORDINATING));
         clause.add(new VerbPhrase(rand.nextInt(4) + 1, subject.getNumber(), subject.getPerson()));
      }
      
      for (int i = 0; i < clause.size(); i++) {
         if (i < subjectIndex) {
            clause.add(i + 1, ",");
            i++;
            subjectIndex++;
         }// End if
      }// End for
   }
   
   
   
   /**
      The overloaded constructor constructs a clause with the given
      parameters.
      @param pattern The pattern of the clause.
   */
   
   public Clause(int pattern) {
      constructClause(pattern);
   }
   
   
   
   /**
      The no-arg constructor constructs a clause with random parameters.
   */
   
   public Clause() {
      
      int pattern;   // The pattern of the sentence
      
      // Determine the sentence pattern.
      if (Util.randChance("pattern1"))
         pattern = 1;
      else if (Util.randChanceContinued("pattern2"))
         pattern = 2;
      else if (Util.randChanceContinued("pattern3"))
         pattern = 3;
      else if (Util.randChanceContinued("pattern4"))
         pattern = 4;
      else
         pattern = 5;
      
      constructClause(pattern);
   
   }
   
   /**
      The toString method.
      @return The contents in the form of a String
   */
   
   public String toString() {
      String str = "";
      
      int i;
      for (i = 0; i < clause.size() - 1; i++) {
         if (clause.get(i + 1).toString().equals(","))
            str = str + clause.get(i);
         else
            str = str + clause.get(i) + " ";
      }
      str = str + clause.get(i);
      
      return str;
   }
      
}