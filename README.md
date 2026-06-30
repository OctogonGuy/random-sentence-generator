# Random Sentence Generator

Generates random sentences.

## Overview

Randomly generate sentences based on the rules of English grammar.

Change the font family, size, and color from the menu. Modify the chance of different events happening from the preferences option.

Built with [JavaFX](https://openjfx.io/).

## Setup

1. Install the Java JDK (Version 25+)
2. Clone the repository.
3. Add execute permissions to `mvnw` (if necessary).
   ```
   chmod +x mvnw
   ```

## Usage

Run the following in the directory of the repository:

```
./mvnw javafx:run
```

## How it Works

The random sentence generator creates a Clause object with a subject and verb. Depending on the sentence pattern, that might be an action verb or a linking verb, and there may be an Object, indirect object, predicate noun, or predicate adjective. Depending on the aspect and, and tense, there may also be helping verb(s).

There is then a random chance additional verbs or nouns will be added to each respective phrase to make compound verbs and nouns.

Zero or more adjectives and adverbs will be added to noun and verb phrases. Zero or more prepositional phrases will be generated and placed in a randomly-selected grammatically-correct location.

If the sentence is a compound sentence, a second clause will be generated and a coordinating conjunction placed between. If  it is a complex sentence, it will likewise generate a second clause with a subordinating conjunction placed between.

Zero or one interjections will be placed at the beginning of the sentence.

Each time a word is added to the sentence, the actual word will be randomly selected from a list based on its part of speech.