package cs3500.marblesolitaire.model.hw04;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Represents an abstract SolitareTextView.
 */
public abstract class SolitaireTextView implements MarbleSolitaireView {
  protected MarbleSolitaireModelState state;
  protected Appendable destination;

  /**
   * Default constructor for a SolitaireTextView.
   *
   * @param state represents the state of the game.
   * @throws IllegalArgumentException if the state is null.
   */
  protected SolitaireTextView(MarbleSolitaireModelState state) {
    if (state == null) {
      throw new IllegalArgumentException("Null state");
    }
    this.state = state;
    this.destination = System.out;
  }

  /**
   * Second constructor for SolitaireTextView.
   *
   * @param state       represents the model being displayed.
   * @param destination where the elements to be rendered are stored.
   */
  public SolitaireTextView(MarbleSolitaireModelState state, Appendable destination) {
    if (state == null || destination == null) {
      throw new IllegalArgumentException("Null state/destination");
    }
    this.state = state;
    this.destination = destination;

  }


  /**
   * Converts the current state of a Marble Solitaire game to a string.
   *
   * @return the state of the board as a string.
   */
  public abstract String toString();

  /**
   * Render the board to the provided data destination. The board should be rendered exactly
   * in the format produced by the toString method above
   *
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderBoard() throws IOException {
    this.destination.append(this.toString());
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    this.destination.append(message);
  }
}
