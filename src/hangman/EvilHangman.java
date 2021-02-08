package hangman;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class EvilHangman {

    public static void main(String[] args) {
        String dictionaryFileName = args[0];
        int wordLength = Integer.parseInt(args[1]);
        int numGuesses = Integer.parseInt(args[2]);

        try {
            if (numGuesses <= 0) {
                System.out.println("Invalid number of guesses");
                throw new IOException();
            }
        } catch(IOException e)  {
            e.printStackTrace();
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
            System.out.printf("You have %d guesses left", numGuesses);
            System.out.print("Used letters: ");
            System.out.print("Word: ");
            System.out.println("Enter guess: ");
            char guess = scan.next().charAt(0);
            try {
                newGame.makeGuess(guess);
            } catch(GuessAlreadyMadeException g) {
                g.printStackTrace();
            }
        }
    }

}
