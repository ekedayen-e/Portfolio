import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.SolitaireTextView;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;

/**
 * Tests for MarbleSolitaireTextView class.
 */
public class MarbleSolitaireTextViewTest {
  MarbleSolitaireView v2;
  MarbleSolitaireModelState e1;
  MarbleSolitaireModelState e2;
  MarbleSolitaireView v1;
  MarbleSolitaireView tv1;
  MarbleSolitaireModelState t1;

  @Before
  public void setUp() {
    e1 = new EuropeanSolitaireModel();
    t1 = new TriangleSolitaireModel();
    e2 = new EnglishSolitaireModel();
    v1 = new MarbleSolitaireTextView(e2);
    v2 = new MarbleSolitaireTextView(e1);
    tv1 = new TriangleSolitaireTextView(t1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    StringBuilder in = new StringBuilder();
    MarbleSolitaireTextView ex = new MarbleSolitaireTextView(null, in);
    MarbleSolitaireTextView ex2 = new MarbleSolitaireTextView(e2, null);
    MarbleSolitaireTextView ex3 = new MarbleSolitaireTextView(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    StringBuilder in = new StringBuilder();
    SolitaireTextView ex = new TriangleSolitaireTextView(null, in);
    SolitaireTextView ex2 = new TriangleSolitaireTextView(e2, null);
    SolitaireTextView ex3 = new TriangleSolitaireTextView(null, null);
  }

  @Test
  public void testToString() {
    assertEquals("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O", v1.toString());
  }

  @Test
  public void testToString2() {
    assertEquals("    _\n   O O\n  O O O\n O O O O\n" +
            "O O O O O", tv1.toString());
  }

  @Test
  public void testToString3() {
    assertEquals("    O O O\n  O O O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n  O O O O O\n    O O O", v2.toString());
  }


}