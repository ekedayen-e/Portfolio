package cs3500.marblesolitaire.model.hw04;

/**
 * Represents a TriangleSolitaireModel.
 */
public class TriangleSolitaireModel extends SolitaireModel {

  /**
   * Default constructor for EuropeanSolitaireModel.
   */
  public TriangleSolitaireModel() {
    this.length = 5;
    this.board = makeNewBoard(0, 0);
    this.score = this.initializeScore();
  }

  /**
   * Construct that takes in the arm thickness of the board.
   *
   * @param length represents the arm thickness of the board.
   * @throws IllegalArgumentException if arm thickness is not a positive odd integer.
   */
  public TriangleSolitaireModel(int length) {
    if (length <= 0) {
      throw new IllegalArgumentException();
    }
    this.length = length;
    this.board = this.makeNewBoard(0, 0);
    this.score = this.initializeScore();
  }

  /**
   * Constructor that takes in the center slot's position.
   *
   * @param sRow represents the row of the center slot.
   * @param sCol represents the column of the center slot.
   */
  public TriangleSolitaireModel(int sRow, int sCol) {
    this.length = 5;
    this.board = makeNewBoard(sRow, sCol);
    this.score = this.initializeScore();
  }

  /**
   * Constructor that takes in arm thickness and the position of the center slot.
   *
   * @param length represents arm thickness of a board.
   * @param sRow   represents the row of the center slot.
   * @param sCol   represents the column of the center slot.
   * @throws IllegalArgumentException if arm thickness is not a positive odd integer.
   */
  public TriangleSolitaireModel(int length, int sRow, int sCol) {
    if (length <= 0) {
      throw new IllegalArgumentException();
    }
    this.length = length;
    this.board = makeNewBoard(sRow, sCol);
    this.score = this.initializeScore();

  }

  @Override
  public boolean checkInvalid(int row, int col) {
    return row < 0 || row >= this.getBoardSize() || col < 0 || col >= this.getBoardSize() ||
            col > row;
  }

  /**
   * Determines if a slot is in the top-left invalid area of the board.
   *
   * @param row represents the row of the slot.
   * @param col represents the column of the slot.
   * @return whether the slot is in an invalid top-left position.
   */
  public boolean checkInvalidTl(int row, int col) {
    return false;
  }

  /**
   * Determines if a slot is in the top-right invalid area of the board.
   *
   * @param row represents the row of the slot.
   * @param col represents the column of the slot.
   * @return whether the slot is in an invalid top-right position.
   */
  public boolean checkInvalidTr(int row, int col) {
    return false;
  }

  /**
   * Determines if a slot is in the bottom-left invalid area of the board.
   *
   * @param row represents the row of the slot.
   * @param col represents the column of the slot.
   * @return whether the slot is in an invalid bottom-left position.
   */
  public boolean checkInvalidBl(int row, int col) {
    return false;
  }

  /**
   * Determines if a slot is in the bottom-right invalid area of the board.
   *
   * @param row represents the row of the slot.
   * @param col represents the column of the slot.
   * @return whether the slot is in an invalid bottom-right position.
   */
  public boolean checkInvalidBr(int row, int col) {
    return false;
  }

  /**
   * Determines whether a move from one position to another is allowed per game rules.
   *
   * @param fromRow represents the row of the slot being moved.
   * @param fromCol represents the column of the slot being moved.
   * @param toRow   represents the destination slot's row.
   * @param toCol   represents the destination slot's column.
   * @return true if the move is valid else false.
   */
  @Override
  protected boolean isMoveValid(int fromRow, int fromCol, int toRow, int toCol) {
    return !checkInvalid(fromRow, fromCol)
            && !checkInvalid(toRow, toCol)
            && this.getSlotAt(fromRow, fromCol) == SlotState.Marble
            && this.getSlotAt(toRow, toCol) == SlotState.Empty
            && ((Math.abs(toRow - fromRow) == 2 && fromCol == toCol)
            || (Math.abs(toCol - fromCol) == 2 && fromRow == toRow)
            || (Math.abs(toRow - fromRow) == 2 && Math.abs(toCol - fromCol) == 2))
            && this.getSlotAt((fromRow + toRow) / 2, (fromCol + toCol) / 2)
            == SlotState.Marble;
  }


  /**
   * Return the size of this board. The size is roughly the longest dimension of a board
   *
   * @return the size as an integer
   */
  @Override
  public int getBoardSize() {
    return this.length;
  }

}
