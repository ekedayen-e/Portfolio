package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Represents an abstract SolitaireModel.
 */
public abstract class SolitaireModel implements MarbleSolitaireModel {
  protected ArrayList<ArrayList<SlotState>> board;
  protected int length;
  protected int score;

  /**
   * Default constructor for SolitaireModel.
   */
  protected SolitaireModel() {
    this.length = 3;
    this.board = makeNewBoard(this.getBoardSize() / 2, this.getBoardSize() / 2);
    this.score = this.initializeScore();
  }

  /**
   * Construct that takes in the arm thickness of the board.
   *
   * @param length represents the arm thickness of the board.
   * @throws IllegalArgumentException if arm thickness is not a positive odd integer.
   */
  protected SolitaireModel(int length) {
    if (length <= 0) {
      throw new IllegalArgumentException();
    }
    if (length % 2 == 0) {
      throw new IllegalArgumentException();
    }
    this.length = length;
    this.board = this.makeNewBoard(this.getBoardSize() / 2, this.getBoardSize() / 2);
    this.score = this.initializeScore();
  }

  /**
   * Constructor that takes in the center slot's position.
   *
   * @param sRow represents the row of the center slot.
   * @param sCol represents the column of the center slot.
   */
  protected SolitaireModel(int sRow, int sCol) {
    this.length = 3;
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
  protected SolitaireModel(int length, int sRow, int sCol) {
    if (length <= 0) {
      throw new IllegalArgumentException();
    }
    if (length % 2 == 0) {
      throw new IllegalArgumentException();
    }
    this.length = length;
    this.board = makeNewBoard(sRow, sCol);
    this.score = this.initializeScore();

  }

  protected ArrayList<ArrayList<SlotState>> makeNewBoard(int sRow, int sCol) {
    ArrayList<ArrayList<SlotState>> board = new ArrayList<>();
    for (int i = 0; i < this.getBoardSize(); i++) {
      board.add(new ArrayList<SlotState>());
      for (int j = 0; j < this.getBoardSize(); j++) {
        board.get(i).add(SlotState.Invalid);
      }
    }
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (!checkInvalid(i, j)) {
          board.get(i).set(j, SlotState.Marble);
        }
      }
    }
    if (checkInvalid(sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }
    board.get(sRow).set(sCol, SlotState.Empty);
    return board;
  }

  protected boolean checkInvalid(int row, int col) {
    return row < 0 || row >= this.getBoardSize() || col < 0 || col >= this.getBoardSize()
            || checkInvalidTl(row, col) || checkInvalidTr(row, col)
            || checkInvalidBl(row, col) || checkInvalidBr(row, col);
  }

  /**
   * Determines if a slot is in the top-left invalid area of the board.
   *
   * @param row represents the row of the slot.
   * @param col represents the column of the slot.
   * @return whether the slot is in an invalid top-left position.
   */
  protected abstract boolean checkInvalidTl(int row, int col);

  /**
   * Determines if a slot is in the top-right invalid area of the board.
   *
   * @param row represents the row of the slot.
   * @param col represents the column of the slot.
   * @return whether the slot is in an invalid top-right position.
   */
  protected abstract boolean checkInvalidTr(int row, int col);

  /**
   * Determines if a slot is in the bottom-left invalid area of the board.
   *
   * @param row represents the row of the slot.
   * @param col represents the column of the slot.
   * @return whether the slot is in an invalid bottom-left position.
   */
  protected abstract boolean checkInvalidBl(int row, int col);

  /**
   * Determines if a slot is in the bottom-right invalid area of the board.
   *
   * @param row represents the row of the slot.
   * @param col represents the column of the slot.
   * @return whether the slot is in an invalid bottom-right position.
   */
  protected abstract boolean checkInvalidBr(int row, int col);

  /**
   * Move a single marble from a given position to another given position.
   * A move is valid only if the from and to positions are valid. Specific
   * implementations may place additional constraints on the validity of a move.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */

  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (isMoveValid(fromRow, fromCol, toRow, toCol)) {
      this.board.get(toRow).set(toCol, SlotState.Marble);
      this.board.get(fromRow).set(fromCol, SlotState.Empty);
      this.board.get((fromRow + toRow) / 2).set((fromCol + toCol) / 2, SlotState.Empty);
      this.score -= 1;
    } else {
      throw new IllegalArgumentException("Invalid move");
    }
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
  protected boolean isMoveValid(int fromRow, int fromCol, int toRow, int toCol) {
    return !checkInvalid(fromRow, fromCol)
            && !checkInvalid(toRow, toCol)
            && this.getSlotAt(fromRow, fromCol) == SlotState.Marble
            && this.getSlotAt(toRow, toCol) == SlotState.Empty
            && ((Math.abs(toRow - fromRow) == 2 && fromCol == toCol)
            || (Math.abs(toCol - fromCol) == 2 && fromRow == toRow))
            && this.getSlotAt((fromRow + toRow) / 2, (fromCol + toCol) / 2)
            == SlotState.Marble;
  }

  /**
   * Determine and return if the game is over or not. A game is over if no
   * more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */

  public boolean isGameOver() {
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if ((this.isMoveValid(i, j, i - 2, j)
                || this.isMoveValid(i, j, i + 2, j)
                || this.isMoveValid(i, j, i + 2, j + 2)
                || this.isMoveValid(i, j, i - 2, j + 2)
                || this.isMoveValid(i, j, i + 2, j - 2)
                || this.isMoveValid(i, j, i - 2, j - 2)
                || this.isMoveValid(i, j, i, j - 2)
                || this.isMoveValid(i, j, i, j + 2))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Return the size of this board. The size is roughly the longest dimension of a board
   *
   * @return the size as an integer
   */

  public int getBoardSize() {
    return (this.length - 1) * 2 + this.length;
  }

  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the slot at the given row and column
   * @throws IllegalArgumentException if the row or the column are beyond
   *                                  the dimensions of the board
   */

  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row > this.getBoardSize() || col < 0 || col > this.getBoardSize()) {
      throw new IllegalArgumentException("Invalid position");
    }
    return this.board.get(row).get(col);
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  public int getScore() {
    return this.score;
  }

  /**
   * Initializes the score of a solitaire game.
   *
   * @return the number of marbles the solitaire game started with.
   */
  protected int initializeScore() {
    int res = 0;
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.getSlotAt(i, j) == SlotState.Marble) {
          res += 1;
        }
      }
    }
    return res;
  }
}
