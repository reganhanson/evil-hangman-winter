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
            if (numGuesses < 1) {
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
        EvilHangmanGame myGame = new EvilHangmanGame();
        try {
            myGame.startGame(dictionaryFile, wordLength);
        } catch (IOException | EmptyDictionaryException e) {
            e.printStackTrace();
        }

        Scanner scan = new Scanner(System.in);

        // user input loop
        for (int i = numGuesses; i > 0; i--) {
            System.out.printf("You have %d guesses left\n", i);
            System.out.printf("Used letters: %s\n", myGame.getGuessedLetters().toString());
            System.out.print("Word: ");
            // print key
            System.out.println("Enter guess: ");
            try {
                char guess = scan.next().charAt(0);
                if (!Character.isLetter(guess)) {
                    throw new IOException();
                }
                myGame.makeGuess(guess);
            } catch (GuessAlreadyMadeException e) {
                System.out.println("You have already guessed this letter");
                i++;
            } catch (IOException e) {
                System.out.println("Invalid input");
                i++;
            }
        }
        System.out.printf("The word was: %s", myGame.dictionary.iterator().next());
    }

}
