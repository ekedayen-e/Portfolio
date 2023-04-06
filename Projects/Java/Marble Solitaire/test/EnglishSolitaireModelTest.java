import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


/**
 * Tests for EnglishSolitaireModel class.
 */
public class EnglishSolitaireModelTest {
  EnglishSolitaireModel b0;
  EnglishSolitaireModel b1;
  EnglishSolitaireModel b2;
  EnglishSolitaireModel b3;
  EnglishSolitaireModel b4;


  @Before
  public void setUp() {

    b1 = new EnglishSolitaireModel();
    b2 = new EnglishSolitaireModel(3, 4);
    b3 = new EnglishSolitaireModel(5);
    b4 = new EnglishSolitaireModel(5, 4, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    Assert.assertEquals(MarbleSolitaireModelState.SlotState.Empty, b1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b3.getSlotAt(6, 6));
    MarbleSolitaireModel f1 = new EnglishSolitaireModel(4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    MarbleSolitaireModel f2 = new EnglishSolitaireModel(-1);

  }

  @Test
  public void testConstructor3() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            new EnglishSolitaireModel(5).getSlotAt(6, 6));
  }

  @org.junit.Test
  public void testMove() {
    b1.move(5, 3, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, b1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b1.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b1.getSlotAt(4, 3));
    b3.move(8, 6, 6, 6);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, b3.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b3.getSlotAt(8, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b3.getSlotAt(7, 6));

  }

  @org.junit.Test
  public void testMove2() {
    b1.move(5, 3, 3, 3);
    b1.move(4, 1, 4, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, b1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b1.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b1.getSlotAt(4, 2));
  }

  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testMoveI() {
    b1.move(0, 0, 1, 1);
    b1.move(0, 0, -1, -5);
  }

  @org.junit.Test
  public void testIsGameOver() {
    assertFalse(b1.isGameOver());
  }

  @org.junit.Test
  public void testGetBoardSize() {
    assertEquals(7, b1.getBoardSize());
    assertEquals(13, b3.getBoardSize());
  }

  @org.junit.Test
  public void testGetSlotAt() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, b1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, b1.getSlotAt(1, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, b1.getSlotAt(0, 1));
  }

  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtI() {
    b1.getSlotAt(-500, 3);
    b1.getSlotAt(0, 500);
    b1.getSlotAt(-5, -10);
  }

  @org.junit.Test
  public void testGetScore() {
    assertEquals(32, b1.getScore());
    b1.move(5, 3, 3, 3);
    assertEquals(31, b1.getScore());
    assertEquals(104, b3.getScore());
  }
}