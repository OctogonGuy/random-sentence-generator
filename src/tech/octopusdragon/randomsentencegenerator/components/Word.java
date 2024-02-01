package tech.octopusdragon.randomsentencegenerator.components;

/**
   The PartOfSpeech class is an abstract class that holds general data about a
   part of speech. Classes representing the parts of speech should inherit from
   this class.
*/

public abstract class Word {
   private String word; // The word
   
   /**
      The setWord method sets the word this PartOfSpeech represents.
      @param w The word.
   */
   
   public void setWord(String w) {
      word = w;
   }
   
   /**
      toString method
      @return The word.
   */
   
   public String toString() {
      return word;
   }
   
   /**
      The equals method compares this object to a test object.
      @return true if they contain the same word.
   */
   
   public boolean equals(Word object2) {
      return word.equals(object2.word);
   }
}