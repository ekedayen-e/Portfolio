package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * Represents a MarbleSolitaire game.
 */
public final class MarbleSolitaire {
  /**
   * Represents the main function that runs the MarbleSolitaire game.
   *
   * @param args represents the user input given to the game.
   */
  public static void main(String[] args) {
    String type = "";
    int size = -1;
    int row = -1;
    int col = -1;
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("english")) {
        type = "en";
      }
      if (args[i].equals("european")) {
        type = "eu";
      }
      if (args[i].equals("triangular")) {
        type = "tri";
      }
      if (args[i].equals("-size") && (i + 1 < args.length)) {
        size = Integer.parseInt(args[i + 1]);
      }
      if (args[i].equals("-hole") && (i + 2 < args.length)) {
        row = Integer.parseInt(args[i + 1]);
        col = Integer.parseInt(args[i + 2]);
      }
    }
    MarbleSolitaireModel model = createModel(type, size, row, col);
    InputStreamReader in = new InputStreamReader(System.in);
    MarbleSolitaireView v;
    if (type.equals("tri")) {
      v = new TriangleSolitaireTextView(model, System.out);
    } else {
      v = new MarbleSolitaireTextView(model, System.out);
    }
    MarbleSolitaireController con = new MarbleSolitaireControllerImpl(model, v, in);
    con.playGame();

  }

  /**
   * Creates a MarbleSolitaireModel with the given parameters.
   *
   * @param type represents the type of model as a string.
   * @param size represents the size of the model.
   * @param row  represents the y-position of the empty slot.
   * @param col  represents the x-position of the empty slot.
   * @return a new MarbleSolitaire model with the given configurations.
   */
  private static MarbleSolitaireModel createModel(String type, int size, int row, int col) {
    if (type.equals("en") && size == -1) {
      return new EnglishSolitaireModel();
    } else if (type.equals("en") && size != -1 && (row == -1 || col == -1)) {
      return new EnglishSolitaireModel(size);
    } else if (type.equals("en") && size != -1 && (row != -1 || col != -1)) {
      return new EnglishSolitaireModel(size, row, col);
    } else if (type.equals("eu") && size == -1) {
      return new EuropeanSolitaireModel();
    } else if (type.equals("eu") && size != -1 && (row == -1 || col == -1)) {
      return new EuropeanSolitaireModel(size);
    } else if (type.equals("eu") && size != -1 && (row != -1 || col != -1)) {
      return new EuropeanSolitaireModel(size, row, col);
    } else if (type.equals("tri") && size == -1) {
      return new TriangleSolitaireModel();
    } else if (type.equals("tri") && size != -1 && (row == -1 || col == -1)) {
      return new TriangleSolitaireModel(size);
    } else {
      return new TriangleSolitaireModel(size, row, col);
    }
  }
}
