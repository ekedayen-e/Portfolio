package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Represents an Implementation of the MarbleSolitaire Controller.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private final MarbleSolitaireModel model;
  private final MarbleSolitaireView view;
  private final Readable input;

  /**
   * Default construct for the controller.
   *
   * @param model represents the model that the controller communicates with.
   * @param view  represents the view that the controller communicates with.
   * @param input represents the user input to the controller.
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable input) {
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException();
    }
    this.model = model;
    this.view = view;
    this.input = input;
  }

  /**
   * Helper method for renderBoard that throws error if transmission fails.
   */
  private void renderBoardHelp() {
    try {
      this.view.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException("Error");
    }
  }

  /**
   * Helper method for renderMessage that throws error if transmission fails.
   */
  private void renderMessageHelp(String message) {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("Error");
    }
  }

  /**
   * Plays a new game of Marble Solitaire.
   *
   * @throws IllegalStateException if controller is unable to read input or transmit output.
   */
  @Override
  public void playGame() throws IllegalStateException {
    this.renderBoardHelp();
    this.renderMessageHelp("\nScore: " + (this.model.getScore()) + "\n");
    Scanner scan = new Scanner(this.input);
    ArrayList<Integer> input = new ArrayList<Integer>();
    int a;
    int b;
    int c;
    int d;
    while (!this.model.isGameOver()) {
      String inp = "";
      if (input.size() == 4) {
        try {
          a = input.remove(0);
          b = input.remove(0);
          c = input.remove(0);
          d = input.remove(0);
          this.model.move(a, b, c, d);
          this.renderBoardHelp();
          this.renderMessageHelp("\nScore: " + (this.model.getScore()) + "\n");
        } catch (IllegalArgumentException e) {
          this.renderMessageHelp("Invalid move. Play again.\n");
        }
      } else {
        try {
          inp = scan.next();
          input.add(Integer.parseInt(inp) - 1);
        } catch (NoSuchElementException ex) {
          throw new IllegalStateException();
        } catch (NumberFormatException e) {
          if (inp.equals("q") || inp.equals("Q")) {
            this.renderMessageHelp("Game quit!\n");
            this.renderMessageHelp("State of game when quit:\n");
            this.renderBoardHelp();
            this.renderMessageHelp("\nScore: " + (this.model.getScore()));
            return;
          } else {
            this.renderMessageHelp("Renter Input: ");
          }
        }
      }
    }
    this.renderMessageHelp("Game over!\n");
    this.renderBoardHelp();
    this.renderMessageHelp("\nScore: " + (this.model.getScore()));
  }
}

