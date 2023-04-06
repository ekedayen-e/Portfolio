import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;

/**
 * Tests for MarbleSolitaireController.
 */
public class MarbleSolitaireControllerTest {

  @Test
  public void testInputs() throws IOException {
    Reader in = new StringReader("2 4 4 4 q");
    StringBuilder log = new StringBuilder();
    StringBuilder log2 = new StringBuilder();
    MarbleSolitaireModel m1 = new MockModel(log);
    MarbleSolitaireView v1 = new MockView(log2);
    MarbleSolitaireController con = new MarbleSolitaireControllerImpl(m1, v1, in);
    con.playGame();
    String result = log.toString();
    String result3 = log2.toString();
    String[] outputs = result3.split("\n");
    assertEquals("fromRow = 1, fromCol = 3, toRow = 3, toCol = 3\n", result);
    assertEquals("Message: Game quit!", outputs[5]);
    assertEquals("Message: State of game when quit:", outputs[5]);
  }

  @Test
  public void testInputs2() throws IOException {
    Reader in = new StringReader("2 4 4 l q");
    StringBuilder log = new StringBuilder();
    StringBuilder log2 = new StringBuilder();
    MarbleSolitaireModel m1 = new MockModel(log);
    MarbleSolitaireView v1 = new MockView(log2);
    MarbleSolitaireController con = new MarbleSolitaireControllerImpl(m1, v1, in);
    con.playGame();
    String result = log.toString();
    String result3 = log2.toString();
    String[] outputs = result3.split("\n");
    assertEquals("Message: Renter Input: ", outputs[3]);
  }
}
