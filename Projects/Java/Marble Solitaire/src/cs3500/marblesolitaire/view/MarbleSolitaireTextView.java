package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.SolitaireTextView;

/**
 * Represents the view of the state of a game.
 */
public class MarbleSolitaireTextView extends SolitaireTextView {

  /**
   * Default constructor for a MarbleSolitaireTextView.
   *
   * @param state represents the state of the game.
   * @throws IllegalArgumentException if the state is null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState state) {
    super(state);
  }

  public MarbleSolitaireTextView(MarbleSolitaireModelState state, Appendable destination) {
    super(state, destination);
  }


  /**
   * Converts the current state of a Marble Solitaire game to a string.
   *
   * @return the state of the board as a string.
   */
  public String toString() {
    boolean seen = false;
    String result = "";
    for (int i = 0; i < this.state.getBoardSize(); i++) {
      for (int j = 0; j < this.state.getBoardSize(); j++) {
        if (this.state.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid) {
          if (seen) {
            result = result.substring(0, result.length() - 1);
            break;
          }
          result += "  ";
        }
        if (this.state.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Marble) {
          seen = true;
          if (j == this.state.getBoardSize() - 1) {
            result += "O";
          } else {
            result += "O ";
          }
        }
        if (this.state.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty) {
          seen = true;
          if (j == this.state.getBoardSize() - 1) {
            result += "_";
          } else {
            result += "_ ";
          }
        }
      }
      seen = false;
      if (i < this.state.getBoardSize() - 1) {
        result += "\n";
      }
    }
    return result;
  }
}
