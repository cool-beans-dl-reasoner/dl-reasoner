package reasoner.expressions;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;


public class UniversalExpressionTest {

  @Test
  public void testEquals() throws Exception {
    UniversalExpression goToBar = new UniversalExpression("goTo", new ConceptExpression("bar"));
    UniversalExpression goToBar2 = new UniversalExpression("goTo", new ConceptExpression("bar"));

    assertEquals(goToBar, goToBar2);
  }

  @Test
  public void testContradiction() throws Exception {
    UniversalExpression goToBar = new UniversalExpression("goTo", new ConceptExpression("bar"));
    UniversalExpression goToNotBar = new UniversalExpression("goTo", new NotExpression(new ConceptExpression("bar")));

    assertFalse(goToBar.contradicts(goToBar));
    assertTrue(goToBar.contradicts(goToNotBar));
    assertTrue(goToNotBar.contradicts(goToBar));

    goToBar.addConcept(new NotExpression(new ConceptExpression("barz")));
    goToNotBar.addConcept(new ConceptExpression("bar"));

    assertFalse(goToBar.contradicts(goToBar));
    assertTrue(goToBar.contradicts(goToNotBar));
    assertTrue(goToNotBar.contradicts(goToBar));
  }

  @Test
  public void testContradictsExistentialExpression() {
    UniversalExpression goToBar = new UniversalExpression("goTo", new ConceptExpression("bar"));
    ExistentialExpression goToNotBar = new ExistentialExpression("goTo", new NotExpression(new ConceptExpression("bar")));

    assertFalse(goToBar.contradicts(goToBar));
    assertTrue(goToBar.contradicts(goToNotBar));
    assertTrue(goToNotBar.contradicts(goToBar));
  }
}