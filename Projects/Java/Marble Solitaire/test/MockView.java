import java.io.IOException;
import java.util.Objects;

import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Represents a mock of the MarbleSolitaireView.
 */
public class MockView implements MarbleSolitaireView {
  StringBuilder log;

  /**
   * Default constructor for MockView.
   *
   * @param log for storing inputs.
   */
  public MockView(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  /**
   * Doesn't take in input.
   *
   * @throws IOException if transmission fails.
   */
  @Override
  public void renderBoard() throws IOException {
    return;
  }

  /**
   * Records input of renderMessage.
   *
   * @param message the message to be transmitted.
   * @throws IOException if transmission fails.
   */
  @Override
  public void renderMessage(String message) throws IOException {
    this.log.append("Message: " + message + "\n");
  }
}
