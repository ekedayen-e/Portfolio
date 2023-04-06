package cs3500.marblesolitaire.controller;

/**
 * Represents the controller for the MarbleSolitaire game.
 */
public interface MarbleSolitaireController {
  /**
   * Plays a new game of Marble Solitaire.
   *
   * @throws IllegalStateException if controller is unable to read input or transmit output.
   */
  void playGame() throws IllegalStateException;
}
