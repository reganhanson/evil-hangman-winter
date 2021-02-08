package hangman;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class EvilHangmanGame implements IEvilHangmanGame{
    // static variables
    // variables
    private int wordSize;
    Set<String> dictionary;
    SortedSet<Character> guessedLetters;

    // constructors
    public EvilHangmanGame() {
        this.wordSize = 0;
        this.dictionary = new TreeSet<>();
        this.guessedLetters = new TreeSet<>();
    }

    // methods
    @Override
    public void startGame(File dictionary, int wordLength) throws IOException, EmptyDictionaryException {
        Scanner scanFile = new Scanner(dictionary);
        this.wordSize = wordLength;

        if (this.wordSize < 2) {
            throw new IOException();
        }

        // create dictionary with given text file
        while (scanFile.hasNext()) {
            String nextWord = scanFile.next();
            if (nextWord.length() == this.wordSize) {
                this.dictionary.add(nextWord);
            }
        }
        if (this.dictionary.isEmpty()) {
            throw new EmptyDictionaryException();
        }
    }

    @Override
    public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException {
        return null;
    }

    @Override
    public SortedSet<Character> getGuessedLetters() {
        return guessedLetters;
    }
}
