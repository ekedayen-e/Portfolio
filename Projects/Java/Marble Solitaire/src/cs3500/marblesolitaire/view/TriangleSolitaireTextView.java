package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.SolitaireTextView;

/**
 * Represents a TriangleSolitaireTextView.
 */
public class TriangleSolitaireTextView extends SolitaireTextView {

  public TriangleSolitaireTextView(MarbleSolitaireModelState state) {
    super(state);
  }

  public TriangleSolitaireTextView(MarbleSolitaireModelState state, Appendable destination) {
    super(state, destination);
  }

  @Override
  public String toString() {
    boolean seen = false;
    String result = "";
    int count;
    for (int i = 0; i < this.state.getBoardSize(); i++) {
      count = (this.state.getBoardSize() - 1) - i;
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
          while (count > 0) {
            result += " ";
            count--;
          }
          if (j == this.state.getBoardSize() - 1) {
            result += "O";
          } else {
            result += "O ";
          }
        }
        if (this.state.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty) {
          seen = true;
          while (count > 0) {
            result += " ";
            count--;
          }
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
