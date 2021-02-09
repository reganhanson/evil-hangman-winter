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
    public Set<String> dictionary;
    private SortedSet<Character> guessedLetters;

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
        guess = Character.toLowerCase(guess);
        if (this.guessedLetters.contains(guess)) {
            throw new GuessAlreadyMadeException();
        }
        this.guessedLetters.add(guess);
        return null;
    }

    @Override
    public SortedSet<Character> getGuessedLetters() {
        return guessedLetters;
    }
}
