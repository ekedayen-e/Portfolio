package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.SolitaireModel;

/**
 * Represents an English Solitaire Board Game.
 */
public class EnglishSolitaireModel extends SolitaireModel {

  /**
   * Default constructor for an English Solitaire Board.
   */
  public EnglishSolitaireModel() {
    super();
  }

  /**
   * Constructor that takes in the center slot's position.
   *
   * @param sRow represents the row of the center slot.
   * @param sCol represents the column of the center slot.
   */
  public EnglishSolitaireModel(int sRow, int sCol) {
    super(sRow, sCol);
  }

  /**
   * Construct that takes in the arm thickness of the board.
   *
   * @param length represents the arm thickness of the board.
   * @throws IllegalArgumentException if arm thickness is not a positive odd integer.
   */
  public EnglishSolitaireModel(int length) {
    super(length);
  }

  /**
   * Constructor that takes in arm thickness and the position of the center slot.
   *
   * @param length represents arm thickness of a board.
   * @param sRow   represents the row of the center slot.
   * @param sCol   represents the column of the center slot.
   * @throws IllegalArgumentException if arm thickness is not a positive odd integer.
   */
  public EnglishSolitaireModel(int length, int sRow, int sCol) {
    super(length, sRow, sCol);

  }

  /**
   * Determines if a slot is in the top-left invalid area of the board.
   *
   * @param row represents the row of the slot.
   * @param col represents the column of the slot.
   * @return whether the slot is in an invalid top-left position.
   */
  public boolean checkInvalidTl(int row, int col) {
    return row < this.length - 1 && col < this.length - 1;
  }

  /**
   * Determines if a slot is in the top-right invalid area of the board.
   *
   * @param row represents the row of the slot.
   * @param col represents the column of the slot.
   * @return whether the slot is in an invalid top-right position.
   */
  public boolean checkInvalidTr(int row, int col) {
    return row < this.length - 1 && col > this.length * 2 - 2;
  }

  /**
   * Determines if a slot is in the bottom-left invalid area of the board.
   *
   * @param row represents the row of the slot.
   * @param col represents the column of the slot.
   * @return whether the slot is in an invalid bottom-left position.
   */
  public boolean checkInvalidBl(int row, int col) {
    return row > this.length * 2 - 2 && col < this.length - 1;
  }

  /**
   * Determines if a slot is in the bottom-right invalid area of the board.
   *
   * @param row represents the row of the slot.
   * @param col represents the column of the slot.
   * @return whether the slot is in an invalid bottom-right position.
   */
  public boolean checkInvalidBr(int row, int col) {
    return row > this.length * 2 - 2 && col > this.length * 2 - 2;
  }
}
