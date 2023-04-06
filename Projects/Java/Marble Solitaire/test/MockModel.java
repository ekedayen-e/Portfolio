import java.util.Objects;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Represents a mock of MarbleSolitaireModel.
 */
public class MockModel implements MarbleSolitaireModel {
  private final StringBuilder log;

  /**
   * Default constructor for MockModel.
   *
   * @param log is the log that stores input.
   */
  public MockModel(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }


  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    log.append(String.format("fromRow = %d, fromCol = %d, toRow = %d, toCol = %d\n",
            fromRow, fromCol, toRow, toCol));
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public int getBoardSize() {
    return 0;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    log.append(String.format("Row = %d, Colum = %d\n", row, col));
    return SlotState.Marble;
  }

  @Override
  public int getScore() {
    return 0;
  }
}
