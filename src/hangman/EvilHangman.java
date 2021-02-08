package hangman;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class EvilHangman {

    public static void main(String[] args) {
        String dictionaryFileName = args[0];
        int wordLength = Integer.parseInt(args[1]);
        int numGuesses = Integer.parseInt(args[2]);

        String error = null;

        try {
            if (numGuesses <= 0) {
                error = "Invalid number of guesses";
                throw new IOException();
            }
            if (wordLength < 2) {
                error = "Invalid length of word";
                throw new IOException();
            }
        } catch(IOException e)  {
            System.out.println(error);
        }

        File dictionaryFile = new File(dictionaryFileName);
        EvilHangmanGame newGame = new EvilHangmanGame();
        try {
            newGame.startGame(dictionaryFile, wordLength);
        } catch (IOException | EmptyDictionaryException e) {
            e.printStackTrace();
        }

        Scanner scan = new Scanner(System.in);

        // user input loop
        for (int i = 0; i < numGuesses; i++) {
            System.out.printf("You have %d guesses left\n", numGuesses);
            System.out.printf("Used letters: %s\n", newGame.getGuessedLetters().toString());
            System.out.print("Word: ");
            // print key
            System.out.println("Enter guess: ");
            char guess = scan.next().charAt(0);
            try {
                newGame.makeGuess(guess);
            } catch(GuessAlreadyMadeException g) {
                System.out.printf("You have already guessed %c!\n", guess);
                i--;
            }
        }
    }

}
