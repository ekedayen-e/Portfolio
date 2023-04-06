import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.SolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Tests for Homework 4.
 */
public class HomeworkFourTests {

  @Test
  public void testConstructor() {
    SolitaireModel esm = new EuropeanSolitaireModel();
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, esm.getSlotAt(0, 0));
    SolitaireModel esm2 = new EuropeanSolitaireModel(5);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm2.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm2.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, esm2.getSlotAt(0, 0));
    SolitaireModel esm3 = new EuropeanSolitaireModel(3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm3.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm3.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, esm3.getSlotAt(0, 0));
    SolitaireModel esm4 = new EuropeanSolitaireModel(5, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm4.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm4.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, esm4.getSlotAt(0, 0));
    SolitaireModel tsm = new TriangleSolitaireModel();
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, tsm.getSlotAt(0, 1));
    SolitaireModel tsm2 = new TriangleSolitaireModel(5);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm2.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm2.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, tsm2.getSlotAt(0, 1));
    SolitaireModel tsm3 = new TriangleSolitaireModel(3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm3.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm3.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, tsm3.getSlotAt(0, 1));
    SolitaireModel tsm4 = new TriangleSolitaireModel(5, 2, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm4.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm4.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, tsm4.getSlotAt(0, 1));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorI() {
    SolitaireModel esm = new EuropeanSolitaireModel(-5);
    SolitaireModel esm2 = new EuropeanSolitaireModel(2);
    SolitaireModel esm3 = new EuropeanSolitaireModel(-3, 3);
    SolitaireModel esm4 = new EuropeanSolitaireModel(3, -3);
    SolitaireModel esm5 = new EuropeanSolitaireModel(0, 0);
    SolitaireModel esm6 = new EuropeanSolitaireModel(-2, 3, 3);
    SolitaireModel esm7 = new EuropeanSolitaireModel(5, -3, 3);
    SolitaireModel esm8 = new EuropeanSolitaireModel(5, 3, -3);
    SolitaireModel esm9 = new EuropeanSolitaireModel(5, 0, 0);
    SolitaireModel esm10 = new EuropeanSolitaireModel(-2, -10, -15);
    SolitaireModel tsm = new TriangleSolitaireModel(-5);
    SolitaireModel tsm2 = new TriangleSolitaireModel(0);
    SolitaireModel tsm3 = new TriangleSolitaireModel(-3, 3);
    SolitaireModel tsm4 = new TriangleSolitaireModel(3, -3);
    SolitaireModel tsm5 = new TriangleSolitaireModel(0, 1);
    SolitaireModel tsm6 = new TriangleSolitaireModel(-2, 3, 3);
    SolitaireModel tsm7 = new TriangleSolitaireModel(2, 3, 5);
    SolitaireModel tsm8 = new TriangleSolitaireModel(2, 0, 2);
    SolitaireModel tsm9 = new EuropeanSolitaireModel(2, -1, -1);
    SolitaireModel tsm10 = new EuropeanSolitaireModel(-2, -10, -15);

  }

  @Test
  public void move() {
    SolitaireModel sm = new TriangleSolitaireModel(6, 3, 1);
    sm.move(5, 3, 3, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, sm.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, sm.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, sm.getSlotAt(3, 1));

    sm = new TriangleSolitaireModel(6, 3, 1);
    sm.move(5, 1, 3, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, sm.getSlotAt(5, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, sm.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, sm.getSlotAt(3, 1));

    sm = new TriangleSolitaireModel(6, 3, 1);
    sm.move(3, 3, 3, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, sm.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, sm.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, sm.getSlotAt(3, 1));

    SolitaireModel sm2 = new TriangleSolitaireModel(6, 4, 2);
    sm2.move(2, 0, 4, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, sm2.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, sm2.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, sm2.getSlotAt(4, 2));

    sm2 = new TriangleSolitaireModel(6, 4, 2);
    sm2.move(2, 2, 4, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, sm2.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, sm2.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, sm2.getSlotAt(4, 2));

    sm2 = new TriangleSolitaireModel(6, 4, 2);
    sm2.move(4, 0, 4, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, sm2.getSlotAt(4, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, sm2.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, sm2.getSlotAt(4, 2));

    SolitaireModel sm3 = new TriangleSolitaireModel(6, 4, 2);
    sm3.move(4, 4, 4, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, sm3.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, sm3.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, sm3.getSlotAt(4, 2));

    SolitaireModel b1 = new EuropeanSolitaireModel();
    b1.move(5, 3, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, b1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b1.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b1.getSlotAt(4, 3));

    b1 = new EuropeanSolitaireModel();
    b1.move(1, 3, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, b1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b1.getSlotAt(1, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b1.getSlotAt(2, 3));

    b1 = new EuropeanSolitaireModel();
    b1.move(3, 1, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, b1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b1.getSlotAt(3, 2));

    b1 = new EuropeanSolitaireModel();
    b1.move(3, 5, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, b1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b1.getSlotAt(3, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b1.getSlotAt(3, 4));
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveI() {
    SolitaireModel b1 = new EuropeanSolitaireModel();
    b1.move(3, 5, 2, 4);
    SolitaireModel t1 = new TriangleSolitaireModel();
    b1.move(1, 0, 0, 0);
  }

  @Test
  public void testGetSlotAt() {
    SolitaireModel sm = new TriangleSolitaireModel(6, 4, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, sm.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, sm.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, sm.getSlotAt(0, 2));

    SolitaireModel tsm = new TriangleSolitaireModel();
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, tsm.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, tsm.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, tsm.getSlotAt(0, 2));

    SolitaireModel esm4 = new EuropeanSolitaireModel(5, 4, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm4.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm4.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, esm4.getSlotAt(0, 0));

    SolitaireModel esm = new EuropeanSolitaireModel();
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, esm.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, esm.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, esm.getSlotAt(0, 0));

  }

  @Test
  public void testGetBoardSize() {
    SolitaireModel tsm = new TriangleSolitaireModel(6, 4, 2);
    assertEquals(6, tsm.getBoardSize());

    SolitaireModel tsm2 = new TriangleSolitaireModel();
    assertEquals(5, tsm2.getBoardSize());

    SolitaireModel esm = new EuropeanSolitaireModel(5, 3, 2);
    assertEquals(13, esm.getBoardSize());

    SolitaireModel esm2 = new EuropeanSolitaireModel();
    assertEquals(7, esm2.getBoardSize());
  }

  @Test
  public void testIsGameOver() {
    SolitaireModel tsm = new TriangleSolitaireModel(6, 4, 2);
    assertFalse(tsm.isGameOver());

    SolitaireModel esm = new EuropeanSolitaireModel(5, 3, 2);
    assertFalse(esm.isGameOver());
  }

  @Test
  public void testGetScore() {
    SolitaireModel tsm = new TriangleSolitaireModel();
    assertEquals(14, tsm.getScore());

    SolitaireModel tsm2 = new TriangleSolitaireModel(6);
    assertEquals(20, tsm2.getScore());

    SolitaireModel esm = new EuropeanSolitaireModel();
    assertEquals(36, esm.getScore());

    SolitaireModel esm2 = new EuropeanSolitaireModel(5);
    assertEquals(128, esm2.getScore());
  }

}
