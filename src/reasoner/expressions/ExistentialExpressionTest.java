package reasoner.expressions;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;


public class ExistentialExpressionTest {

  @Test
  public void testEquals() throws Exception {
    ExistentialExpression goToBar = new ExistentialExpression("goTo", new ConceptExpression("bar"));
    ExistentialExpression goToBar2 = new ExistentialExpression("goTo", new ConceptExpression("bar"));

    assertEquals(goToBar, goToBar2);
  }

  @Test
  public void testContradiction() throws Exception {
    ExistentialExpression goToBar = new ExistentialExpression("goTo", new ConceptExpression("bar"));
    ExistentialExpression goToNotBar = new ExistentialExpression("goTo", new NotExpression(new ConceptExpression("bar")));

    assertFalse(goToBar.contradicts(goToBar));
    assertTrue(goToBar.contradicts(goToNotBar));
    assertTrue(goToNotBar.contradicts(goToBar));

    goToBar.addConcept(new NotExpression(new ConceptExpression("barz")));
    goToNotBar.addConcept(new ConceptExpression("bar"));

    assertFalse(goToBar.contradicts(goToBar));
    assertTrue(goToBar.contradicts(goToNotBar));
    assertTrue(goToNotBar.contradicts(goToBar));
  }
}